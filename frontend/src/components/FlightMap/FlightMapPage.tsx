import { useEffect } from "react";
import CenteredFlexBox from "../Shared/Custom/CenteredFlexBox";
import ContentContainer from "../Shared/PageComponents/ContentContainter";

const FlightMapPage: React.FC = () => {
    
    useEffect(() => {
        // const canvas = document.getElementById("airportCanvas") as HTMLCanvasElement;
        // if (!canvas) return;

        // canvas.width = 1280;
        // canvas.height = 593;

        // const ctx = canvas.getContext("2d");
        // if (!ctx) return;

        // ctx.setTransform(1, 0, 0, 1, 0, 0);

        // const canvasCenterX = canvas.width / 2;
        // const canvasCenterY = canvas.height / 2;
        // ctx.translate(canvasCenterX, canvasCenterY);

        // ctx.save();

        // ctx.translate(0 + .5/2, 0 + 10/2);

        // ctx.rotate(45);

        // ctx.fillStyle = "red";
        // ctx.fillRect(-10/2 * .1, -1000/2 * .1, 100 * .1, 1000 * .1);

        // ctx.restore();

        // ctx.fillStyle = "#595959ff";
        // ctx.fillRect(-10/2 * .1, -1000/2 * .1, 100 * .1, 1000 * .1);

        const canvas = document.getElementById("airportCanvas") as HTMLCanvasElement;
const ctx = canvas.getContext("2d")!;


canvas.width = 800;
canvas.height = 600;


// Example plane state
let plane = { x: 1000, y: 10, width: 50, height: 20, angle: 0 };

animate(canvas, ctx, plane);
}, []);

function drawScene(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D, plane: any) {
  ctx.setTransform(1,0,0,1,0,0);
  ctx.clearRect(0, 0, canvas.width, canvas.height);

  const canvasCenterX = canvas.width / 2;
const canvasCenterY = canvas.height / 2;
const pixelsPerMeter = 0.1;

  // Move origin to center of canvas
  ctx.translate(canvasCenterX, canvasCenterY);
  ctx.scale(pixelsPerMeter, -pixelsPerMeter); // Flip Y so north/up is positive

  // Example runway (static)
  ctx.fillStyle = "gray";
  ctx.fillRect(-100, 50, 200, 20); // in world coordinates

  // Draw plane
  ctx.save(); // isolate transformations for the plane
  ctx.translate(plane.x, plane.y);  // move to plane position
  ctx.rotate(plane.angle);          // rotate plane
  ctx.fillStyle = "red";
  ctx.fillRect(-plane.width/2, -plane.height/2, plane.width, plane.height);
  ctx.restore(); // reset transform for anything else
}

function animate(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D, plane: any) {
  plane.y += 5;        // move north
  plane.x += 1;
  drawScene(canvas, ctx, plane);
  requestAnimationFrame(() => animate(canvas, ctx, plane));
}

    return (
        <ContentContainer>
            <CenteredFlexBox>
                <canvas id="airportCanvas" style={{width: "100%", height: "100%", overflow: "hidden", display: "block"}}></canvas>
            </CenteredFlexBox>
        </ContentContainer>
    )
}

export default FlightMapPage;