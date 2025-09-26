import { Button } from "@mui/material";
import AbsoluteFullBox from "../Shared/Custom/AbsoluteFullBox";
import CenteredFlexBox from "../Shared/Custom/CenteredFlexBox";


const MapInterface: React.FC<{
    zIndex: number
}> = ({zIndex}) => {

    return (
        <AbsoluteFullBox zIndex={zIndex}>
            <CenteredFlexBox>
                
            </CenteredFlexBox>
        </AbsoluteFullBox>
    )
}

export default MapInterface;