"use strict";
function drawRunway(ctx, runway) {
    const dx = runway.end.x - runway.start.x;
    const dy = runway.end.y - runway.start.y;
    const length = Math.sqrt(dx * dx + dy * dy);
    const angle = Math.atan2(dy, dx);
    ctx.save();
    ctx.translate(runway.start.x, runway.start.y);
    ctx.rotate(angle);
    ctx.fillStyle = "#555"; // dark gray runway
    ctx.fillRect(0, -runway.width / 2, length, runway.width);
    // Draw runway name
    ctx.fillStyle = "white";
    ctx.font = "14px Arial";
    ctx.fillText(runway.name, length / 2 - 10, 0);
    ctx.restore();
}
function drawTaxiway(ctx, taxiway) {
    if (taxiway.points.length < 2)
        return;
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
function drawPlane(ctx, plane) {
    ctx.beginPath();
    ctx.arc(plane.position.x, plane.position.y, 5, 0, 2 * Math.PI);
    ctx.fillStyle = plane.altitude > 0 ? "blue" : "green";
    ctx.fill();
}
window.addEventListener("load", () => {
    const canvas = document.getElementById("airportCanvas");
    if (!canvas)
        return console.error("Canvas not found!");
    const ctx = canvas.getContext("2d");
    if (!ctx)
        return console.error("Failed to get 2D context");
    // Runways
    const runways = [
        { start: { x: 100, y: 100 }, end: { x: 100, y: 500 }, width: 20, name: "18R" },
        { start: { x: 200, y: 200 }, end: { x: 600, y: 200 }, width: 20, name: "36L" },
    ];
    // Taxiways
    const taxiways = [
        { points: [{ x: 100, y: 500 }, { x: 200, y: 500 }, { x: 200, y: 200 }], width: 10, name: "Taxiway A" },
        { points: [{ x: 600, y: 200 }, { x: 600, y: 300 }, { x: 500, y: 300 }], width: 10, name: "Taxiway B" },
    ];
    // Planes
    let planes = [
        { position: { x: 100, y: 100 }, heading: 0, altitude: 0, callsign: "AAL123" },
        { position: { x: 210, y: 210 }, heading: 90, altitude: 0, callsign: "DAL456" },
        { position: { x: 400, y: 220 }, heading: 270, altitude: 3000, callsign: "UAL789" }, // airborne
    ];
    // Draw all
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    runways.forEach(r => drawRunway(ctx, r));
    taxiways.forEach(t => drawTaxiway(ctx, t));
    planes.forEach(p => drawPlane(ctx, p));
    setTimeout(() => {
        planes = [
            { position: { x: 125, y: 120 }, heading: 0, altitude: 0, callsign: "AAL123" },
            { position: { x: 210, y: 215 }, heading: 90, altitude: 0, callsign: "DAL456" },
            { position: { x: 405, y: 225 }, heading: 270, altitude: 3000, callsign: "UAL789" }, // airborne
        ];
        planes.forEach(p => drawPlane(ctx, p));
    }, 1000);
});
