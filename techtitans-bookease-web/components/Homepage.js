'use client'
import React from 'react';
import Navbar from './Appbar';
import Box from '@mui/material/Box';
import Intro from './Intro';
import VisionStatement from './VisionStatement';
import Features from './Features';
import Video from './Video';
import Team from './Team';
import Postmortem from './Postmortem';

function Homepage() {
    return (
        <Box>
            <Navbar />
            <Box id="intro" sx={{ height: '100vh' }}>
                <Intro />
            </Box>
            <Box id="vision" sx={{ height: '100vh' }}>
                <VisionStatement />
            </Box>
            <Box id="feature" sx={{ height: '100vh', backgroundColor: '#7e57c2' }}>
                <Features />
            </Box>
            <Box id="devpostmortem" sx={{ height: '160vh' }}>
                <Postmortem />
            </Box>
            <Box id="video" sx={{ height: '100vh', backgroundColor: '#7e57c2' }}>
                <Video />
            </Box>
            <Box id="team" sx={{ height: '100vh' }}>
                <Team />
            </Box>
        </Box>
    );
}

export default Homepage;
