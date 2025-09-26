import CenteredFlexBox from "../Shared/Custom/CenteredFlexBox";
import ContentContainer from "../Shared/PageComponents/ContentContainter";
import FlightMap from "./FlightMap";

const FlightMapPage: React.FC = () => {

    return (
        <ContentContainer>
            <CenteredFlexBox>
                <FlightMap/>
            </CenteredFlexBox>
        </ContentContainer>
    )
}

export default FlightMapPage;