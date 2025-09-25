import { Style } from "@mui/icons-material";
import Box from "@mui/material/Box"
import { useEffect, useState } from "react";

interface Coordinate {
  x: number;
  y: number;
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

const PlaneCanvas: React.FC<{
    zIndex: number,
    pixelsPerMeter: number
}> = ({zIndex, pixelsPerMeter}) => {

    useEffect(() => {
        const fetchData = async (canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D) => {
            const response = await fetch("http://localhost:8080/api/v1/flight-tracking");
            const positions: Position[] = await response.json();

            drawPlaneAt(canvas, ctx, {x: 100, y: 100, h: 100});

            for(let i = 0; i < positions.length; i++){
                const p = positions[i];
                drawPlaneAt(canvas, ctx, {x: p.xposition, y: p.yposition, h: 100});
                await timeout(1);
            }
        }

        
        const canvas = document.getElementById("planeCanvas") as HTMLCanvasElement;

        
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;

        const ctx = canvas.getContext("2d")!;

        ctx.save();
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.restore();

        const canvasCenterX = canvas.width / 2;
        const canvasCenterY = canvas.height / 2;
        ctx.setTransform(1, 0, 0, 1, 0, 0);

        ctx.translate(canvasCenterX, canvasCenterY);
        ctx.scale(pixelsPerMeter, -pixelsPerMeter);
        fetchData(canvas, ctx);
    }, []);

    function timeout(ms: number) {
        return new Promise(resolve => setTimeout(resolve, ms));
    }

    function drawPlaneAt(canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D, p: {x: number, y: number, h: number}) {
        ctx.clearRect(0, 0, canvas.width / pixelsPerMeter, canvas.height / pixelsPerMeter);
        
        const plane: Plane = {
            position: {x: p.x, y: p.y},
            heading: (p.h - 180), altitude: 0, callsign: ""
        };
        drawPlane(ctx, plane);
    }

    function drawPlane(ctx: CanvasRenderingContext2D, plane: Plane) {
        ctx.beginPath();
        ctx.arc(plane.position.x, plane.position.y, 20, 0, 2*Math.PI);
        ctx.fillStyle = plane.altitude > 0 ? "blue" : "red";
        ctx.fill();
        ctx.beginPath();
        ctx.arc(plane.position.x, plane.position.y, 20, 0, 2*Math.PI);
        ctx.fillStyle = plane.altitude > 0 ? "blue" : "red";
        ctx.fill();
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
            <canvas id="planeCanvas" style={{width: "100%", height: "100%", overflow: "hidden", display: "block"}}></canvas>
        </Box>
    )
}

export default PlaneCanvas;