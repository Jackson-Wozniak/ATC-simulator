interface Coordinate {
  x: number;
  y: number;
}

interface Runway {
  start: Coordinate;
  end: Coordinate;
  width: number;
  name: string;
}

interface Taxiway {
    points: Coordinate[];
    name: string;
    width: number;
}

interface Plane {
  position: Coordinate;
  altitude: number;
  heading: number;
  callsign: string;
}
interface Position {
    timestamp: string,
    xposition: number,
    yposition: number,
    heading: number
}

function drawRunway(ctx: CanvasRenderingContext2D, runway: Runway) {
  const dx = runway.end.x - runway.start.x;
  const dy = runway.end.y - runway.start.y;
  const length = Math.sqrt(dx*dx + dy*dy);
  const angle = Math.atan2(dy, dx);

  ctx.save();
  ctx.translate(runway.start.x, runway.start.y);
  ctx.rotate(angle);

  ctx.fillStyle = "#555"; // dark gray runway
  ctx.fillRect(0, -runway.width/2, length, runway.width);

  // Draw runway name
  ctx.fillStyle = "white";
  ctx.font = "14px Arial";
  ctx.fillText(runway.name, length/2 - 10, 0);

  ctx.restore();
}

function drawTaxiway(ctx: CanvasRenderingContext2D, taxiway: Taxiway) {
    if (taxiway.points.length < 2) return;
    ctx.save();
    ctx.strokeStyle = "#888";
    ctx.lineWidth = taxiway.width;
    ctx.beginPath();
    ctx.moveTo(taxiway.points[0].x, taxiway.points[0].y);
    for (let i = 1; i < taxiway.points.length; i++) {
        ctx.lineTo(taxiway.points[i].x, taxiway.points[i].y);
    }
    ctx.stroke();

    // Draw name at midpoint
    const mid = taxiway.points[Math.floor(taxiway.points.length / 2)];
    ctx.fillStyle = "white";
    ctx.font = "12px Arial";
    ctx.fillText(taxiway.name, mid.x, mid.y);
    ctx.restore();
}

const planeSvg = `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 640 640"><!--!Font Awesome Free v7.0.1 by @fontawesome - https://fontawesome.com License - https://fontawesome.com/license/free Copyright 2025 Fonticons, Inc.--><path d="M552 264C582.9 264 608 289.1 608 320C608 350.9 582.9 376 552 376L424.7 376L265.5 549.6C259.4 556.2 250.9 560 241.9 560L198.2 560C187.3 560 179.6 549.3 183 538.9L237.3 376L137.6 376L84.8 442C81.8 445.8 77.2 448 72.3 448L52.5 448C42.1 448 34.5 438.2 37 428.1L64 320L37 211.9C34.4 201.8 42.1 192 52.5 192L72.3 192C77.2 192 81.8 194.2 84.8 198L137.6 264L237.3 264L183 101.1C179.6 90.7 187.3 80 198.2 80L241.9 80C250.9 80 259.4 83.8 265.5 90.4L424.7 264L552 264z"/></svg>`;

function drawPlane(ctx: CanvasRenderingContext2D, plane: Plane) {
    const planeImg = new Image();
planeImg.src = "data:image/svg+xml;base64," + btoa(planeSvg);

    const size = 24; // icon size in pixels
  const x = plane.position.x;
  const y = plane.position.y;

  // Optionally rotate according to heading
  ctx.save();
  ctx.translate(x, y);
  ctx.rotate((plane.heading * Math.PI) / 180);

  ctx.drawImage(planeImg, -size / 2, -size / 2, size, size);

  ctx.restore();
}

window.addEventListener("load", async () => {
    const airportCanvas = document.getElementById("airportCanvas") as HTMLCanvasElement;
    const planeCanvas = document.getElementById("planeCanvas") as HTMLCanvasElement;

    const planeCtx = planeCanvas.getContext("2d");
    const airportCtx = airportCanvas.getContext("2d");

    if(airportCtx == null || planeCtx == null) return;

    // Runways
    const runways: Runway[] = [
        { start: { x: 100, y: 100 }, end: { x: 100, y: 500 }, width: 20, name: "18R" },
        { start: { x: 200, y: 200 }, end: { x: 600, y: 200 }, width: 20, name: "36L" },
    ];

    // Taxiways
    const taxiways: Taxiway[] = [
        { points: [{ x: 100, y: 500 }, { x: 200, y: 500 }, { x: 200, y: 200 }], width: 10, name: "Taxiway A" },
        { points: [{ x: 600, y: 200 }, { x: 600, y: 300 }, { x: 500, y: 300 }], width: 10, name: "Taxiway B" },
    ];

    // Draw all
    airportCtx.clearRect(0, 0, airportCanvas.width, airportCanvas.height);
    runways.forEach(r => drawRunway(airportCtx, r));
    taxiways.forEach(t => drawTaxiway(airportCtx, t));

    const response = await fetch("http://localhost:8080/api/v1/flight-tracking");
    const positions: Position[] = await response.json();

    drawPlaneAt(planeCtx, planeCanvas, {x: 100, y: 100, h: 100});

    for(let i = 0; i < positions.length; i++){
        const p = positions[i];
        drawPlaneAt(planeCtx, planeCanvas, {x: p.xposition, y: p.yposition, h: p.heading});
        await timeout(10);
    }
});

function timeout(ms: number) {
    return new Promise(resolve => setTimeout(resolve, ms));
}

function drawPlaneAt(planeCtx: CanvasRenderingContext2D, planeCanvas: HTMLCanvasElement, 
    p: {x: number, y: number, h: number}) {
  planeCtx.clearRect(0, 0, planeCanvas.width, planeCanvas.height); // keep this
  const plane: Plane = {
    position: {x: p.x, y: p.y},
    heading: (p.h - 180), altitude: 0, callsign: ""
  };
  drawPlane(planeCtx, plane);
}


function drawAllPlanes(planeCtx: CanvasRenderingContext2D, plane: Plane, 
    planeCanvas: HTMLCanvasElement){
    planeCtx.clearRect(0, 0, planeCanvas.width, planeCanvas.height);
    drawPlane(planeCtx, plane);
}
