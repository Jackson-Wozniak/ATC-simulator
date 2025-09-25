import Box from "@mui/material/Box"
import { useEffect } from "react";

const AirportCanvas: React.FC<{
    zIndex: number
}> = ({zIndex}) => {
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

animate(canvas, ctx);
}, []);

function drawScene(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D) {
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
}

function animate(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D) {
  drawScene(canvas, ctx);
  requestAnimationFrame(() => animate(canvas, ctx));
}

    return (
        <Box sx={{
            zIndex: zIndex,
            position: "absolute",
            top: 0,
            left: 0,
            width: "100%",
            height: "100%"
        }}>
            <canvas id="airportCanvas" style={{width: "100%", height: "100%", overflow: "hidden", display: "block"}}></canvas>
        </Box>
    )
}

export default AirportCanvas;