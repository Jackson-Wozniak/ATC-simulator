import Box from "@mui/material/Box"

const AbsoluteFullBox: React.FC<{
    children?: React.ReactNode,
    zIndex?: number
}> = ({children, zIndex = 0}) => {
    return (
        <Box sx={{
            zIndex: zIndex,
            position: "absolute",
            top: 0,
            left: 0,
            width: "100%",
            height: "100%"
        }}>{children}</Box>
    )
}

export default AbsoluteFullBox;