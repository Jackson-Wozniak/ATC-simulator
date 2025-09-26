import { useEffect } from "react";
import AbsoluteFullBox from "../Shared/Custom/AbsoluteFullBox";
import type { PlanePositionDto } from "../../types/api/PositionDto";
import { drawPlane } from "../../utils/canvas/PlaneCanvasUtils";
import type { Plane } from "../../types/Plane";
import { timeoutPromise } from "../../utils/TimeoutUtils";
import { fetchPlanePositions } from "../../api/PlaneHttpClient";

const PlaneCanvas: React.FC<{
    zIndex: number,
    pixelsPerMeter: number
}> = ({zIndex, pixelsPerMeter}) => {

    useEffect(() => {
        const fetchData = async (canvas: HTMLCanvasElement, ctx: CanvasRenderingContext2D) => {
            const positions: PlanePositionDto[] = await fetchPlanePositions();

            for(let i = 0; i < positions.length; i++){
                const p = positions[i];
                const plane: Plane = {
                    callsign: "TEST",
                    aircraftState: {
                        position: {
                            x: p.xposition,
                            y: p.yposition,
                            altitude: 0
                        },
                        heading: p.heading,
                        speedKnots: 0
                    }
                }
                drawPlane(canvas, ctx, plane);
                await timeoutPromise(1);
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

        ctx.translate(canvasCenterX, canvasCenterY);
        ctx.scale(pixelsPerMeter, -pixelsPerMeter);
        fetchData(canvas, ctx);
    }, []);

    return (
        <AbsoluteFullBox zIndex={zIndex}>
            <canvas id="planeCanvas" style={{width: "100%", height: "100%", overflow: "hidden", display: "block"}}></canvas>
        </AbsoluteFullBox>
    )
}

export default PlaneCanvas;