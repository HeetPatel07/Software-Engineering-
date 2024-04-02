'use client'
import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Typography } from '@mui/material';
import DocumentScannerIcon from '@mui/icons-material/DocumentScanner';
import HelpIcon from '@mui/icons-material/Help';
import SupervisedUserCircleIcon from '@mui/icons-material/SupervisedUserCircle';

export default function VisionStatement() {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={4} sx={{ px: 20 }}>
                <Grid item xs={12} sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: '100%',
                    mt: 6,
                }}>
                    <DocumentScannerIcon sx={{ color: '#757575', mr: 2, height: 35, width: 35 }} />
                    <Typography variant='h4' sx={{ color: '#757575' }}>
                        Vision Statement
                    </Typography>
                </Grid>
                <Grid item xs={6} sx={{ mt: 4 }}>
                    <Box sx={{ display: 'flex', alignItems: 'center' }}>
                        <HelpIcon sx={{ color: '#757575', mr: 2, height: 35, width: 35 }} />
                        <Typography variant='h5' sx={{ color: '#757575' }}>
                            What is BookEase for?
                        </Typography>
                    </Box>
                    <Typography variant='h6' sx={{ color: '#757575', mt: 4 }}>
                        BookEase is a comprehensive platform that redefines the acquisition and management of academic and literary resources,
                        facilitating not only the search and purchase of new and used books but also enabling dynamic information management.
                        It supports sustainable practices by encouraging the recycling of textbooks through a user-friendly marketplace.
                        Moreover, it integrates with university databases to ensure accuracy and ease of access to academic materials,
                        all while providing a personalized experience tailored to the specific needs of its users.
                    </Typography>
                </Grid>
                <Grid item xs={6} sx={{ mt: 4 }}>
                    <Box sx={{ display: 'flex', alignItems: 'center' }}>
                        <SupervisedUserCircleIcon sx={{ color: '#757575', mr: 2, height: 35, width: 35 }} />
                        <Typography variant='h5' sx={{ color: '#757575' }}>
                            Who is BookEase for?
                        </Typography>
                    </Box>
                    <Typography variant='h6' sx={{ color: '#757575', mt: 4 }}>
                        Primarily designed for the academic community, BookEase serves students, professors,
                        and customer representatives by offering distinct access levels and functionalities.
                        Students benefit from streamlined access to required reading materials for their courses,
                        professors can update course requirements in real time, and customer representatives are equipped with tools for efficient service.
                        Additionally, general customers looking for academic and non-academic books can use BookEase without mandatory registration,
                        making it a versatile resource for a wide audience.
                    </Typography>
                </Grid>
            </Grid>
        </Box>
    )
}