'use client'
import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Button, Typography } from '@mui/material';
import CloudDownloadIcon from '@mui/icons-material/CloudDownload';

export default function Intro() {

    return (
        <Box sx={{
            flexGrow: 1,
            backgroundImage: 'url(library.jpg)',
            backgroundSize: 'cover',
            height: '100vh',
            width: '100%'
        }}>
            <Grid container spacing={6} sx={{ px: 10 }}>
                <Grid item xs={5}>
                    <Grid container spacing={2}>
                        <Grid item xs={12} sx={{ mt: 20 }}>
                            <Typography variant='h2' sx={{ color: 'white', textTransform: 'capitalize' }}>
                                BookEase
                            </Typography>
                            <Typography variant='h4' sx={{ color: 'white', mt: 4 }}>
                                BookEase streamlines academic book discovery and promotes sustainable practices for students and professors.
                            </Typography>
                        </Grid>
                        <Grid item xs={6} sx={{ mt: 10 }}>
                            <a href="https://code.cs.umanitoba.ca/comp3350-winter2024/techtitans-a01-9"
                                target="_blank"
                                rel="noopener noreferrer"
                                style={{ textDecoration: 'none' }}
                            >
                                <Button variant='outlined' sx={{
                                    color: 'white',
                                    borderColor: 'white', border: 1,
                                    height: 70,
                                    width: 220
                                }}>
                                    <Typography variant='h5' sx={{
                                        textTransform: 'capitalize',
                                    }}>
                                        Source Code
                                    </Typography>
                                </Button>
                            </a>
                        </Grid>
                        <Grid item xs={6} sx={{ mt: 10 }}>
                            <a href="https://umanitoba-my.sharepoint.com/:f:/g/personal/patelhs_myumanitoba_ca/Eq4AK7UrKtFIn23Qv1TflNsBniB0PmcrV-YvDXG00P5DOQ?e=KYyrgd"
                                target="_blank"
                                rel="noopener noreferrer"
                                style={{ textDecoration: 'none' }}
                            >
                                <Button variant='outlined' sx={{
                                    color: 'white',
                                    borderColor: 'white', border: 1,
                                    height: 70,
                                    width: 220
                                }}>
                                    <Typography variant='h5' sx={{
                                        textTransform: 'capitalize',
                                    }}>
                                        <CloudDownloadIcon sx={{ color: 'white', mr: 2, height: 35, width: 35 }} />
                                        Download
                                    </Typography>
                                </Button>

                            </a>
                        </Grid>
                    </Grid>
                </Grid>
                <Grid item xs={7} sx={{ mt: 10 }}>
                    <Box
                        sx={{
                            ml: 20,
                            position: 'relative',
                            width: 400, // Width of the container matches the background image
                            height: 550, // Adjust the height as needed
                            display: 'inline-block',
                            backgroundImage: 'url(/pixel.png)', // Background image URL
                            backgroundSize: 'cover', // Cover the entire area of the box
                        }}
                    >
                        <Box
                            component="img"
                            src="/app.png" // Foreground image URL
                            sx={{
                                position: 'absolute',
                                top: '50%',
                                left: '50%',
                                width: 300, // Width of the centered image, adjust as needed
                                transform: 'translate(-50%, -50%)', // Center the image
                            }}
                            alt="App Icon"
                        />
                    </Box>
                </Grid>
            </Grid>
        </Box>
    )
}