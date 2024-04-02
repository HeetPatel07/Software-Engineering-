'use client'
import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import ReactPlayer from 'react-player';
import { Typography } from '@mui/material';
import PlayCircleIcon from '@mui/icons-material/PlayCircle';


export default function Video() {
    const [isMounted, setIsMounted] = React.useState(false);

    React.useEffect(() => {
        setIsMounted(true); 
    }, []);

    return (
        <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={2}>
                <Grid item xs={12} sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: '100%',
                    mt: 6
                }}>
                    <PlayCircleIcon sx={{ color: 'white', mr: 2, height: 35, width: 35 }} />
                    <Typography variant='h4' sx={{ color: 'white' }}>
                        Video
                    </Typography>
                </Grid>
                <Grid item xs={12} sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: '100%',
                    mt: 2
                }}>
                    {isMounted && ( // Only render ReactPlayer if the component has mounted
                        <ReactPlayer
                            url='https://www.youtube.com/watch?v=cPtyKJRjg8o'
                            width={'70%'}
                            height={'70vh'}
                        />
                    )}
                </Grid>
            </Grid>
        </Box>
    );
}
