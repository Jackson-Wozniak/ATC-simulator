import CenteredFlexBox from "../Shared/Custom/CenteredFlexBox";
import ContentContainer from "../Shared/PageComponents/ContentContainter";
import AirportCanvas from "../AirportCanvas/AirportCanvas";
import PlaneCanvas from "../PlaneCanvas/PlaneCanvas";

const FlightMapPage: React.FC = () => {

    return (
        <ContentContainer>
            <CenteredFlexBox>
                <AirportCanvas zIndex={-1} pixelsPerMeter={.2}/>
                <PlaneCanvas zIndex={1} pixelsPerMeter={.2}/>
            </CenteredFlexBox>
        </ContentContainer>
    )
}

export default FlightMapPage;