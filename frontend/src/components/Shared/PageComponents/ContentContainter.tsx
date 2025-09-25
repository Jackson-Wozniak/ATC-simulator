import Box from "@mui/material/Box";

const ContentContainer: React.FC<{
    children?: React.ReactNode
}> = ({children}) => {

    return (
        <Box width="100%" height="100%" margin="0px" padding="0px">
            {children}
        </Box>
    )
}

export default ContentContainer;