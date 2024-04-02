'use client'
import React, { useState, useEffect } from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Button from '@mui/material/Button';
import { Typography } from '@mui/material';

function scrollToSection(sectionId) {
    document.getElementById(sectionId).scrollIntoView({ behavior: 'smooth' });
}

function Navbar() {
    const [isScrolled, setIsScrolled] = useState(false);

    useEffect(() => {
        const handleScroll = () => {
            if (window.scrollY > 0) {
                setIsScrolled(true);
            } else {
                setIsScrolled(false);
            }
        };

        window.addEventListener('scroll', handleScroll);

        return () => {
            window.removeEventListener('scroll', handleScroll);
        };
    }, []);

    return (
        <AppBar position="fixed" style={{
            backgroundColor: isScrolled ? '#7e57c2' : 'transparent',
            transition: 'background-color 0.3s',
            boxShadow: 'none',
            width: '100%'
        }}>
            <Toolbar>
                <Button sx={{ color: 'white', mx: 2 }} onClick={() => scrollToSection('intro')}>
                    <Typography>
                        Introduction
                    </Typography>
                </Button>
                <Button sx={{ color: 'white', mx: 2 }} onClick={() => scrollToSection('vision')}>
                    <Typography>
                        Vision Statement
                    </Typography>
                </Button>
                <Button sx={{ color: 'white', mx: 2 }} onClick={() => scrollToSection('feature')}>
                    <Typography>
                        Features
                    </Typography>
                </Button>
                <Button sx={{ color: 'white', mx: 2 }} onClick={() => scrollToSection('devpostmortem')}>
                    <Typography>
                        Dev Process & Postmortem
                    </Typography>
                </Button>
                <Button sx={{ color: 'white', mx: 2 }} onClick={() => scrollToSection('video')}>
                    <Typography>
                        Video
                    </Typography>
                </Button>
                <Button sx={{ color: 'white', mx: 2 }} onClick={() => scrollToSection('team')}>
                    <Typography>
                        Team
                    </Typography>
                </Button>
            </Toolbar>
        </AppBar>
    );
}

export default Navbar;
