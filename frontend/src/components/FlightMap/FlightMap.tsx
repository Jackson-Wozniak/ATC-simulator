import AirportCanvas from "../AirportCanvas/AirportCanvas";
import MapInterface from "../MapInterface/MapInterface";
import PlaneCanvas from "../PlaneCanvas/PlaneCanvas";


const FlightMap: React.FC<{

}> = ({}) => {

    return (
        <>
            <AirportCanvas zIndex={-1} pixelsPerMeter={.1}/>
            <PlaneCanvas zIndex={1} pixelsPerMeter={.1}/>
            <MapInterface zIndex={100}/>
        </>
    )
}

export default FlightMap;