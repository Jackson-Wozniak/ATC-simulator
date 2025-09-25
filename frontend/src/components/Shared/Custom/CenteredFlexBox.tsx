import Box from "@mui/material/Box";

const CenteredFlexBox: React.FC<{
    children?: React.ReactNode,
    flexDirection?: "row" | "column",
    width?: string,
    height?: string
}> = ({children, flexDirection = "row", width = "100%", height = "100%"}) => {

    return (
        <Box display="flex" flexDirection={flexDirection} width={width} height={height}>
            {children}
        </Box>
    )
}

export default CenteredFlexBox;