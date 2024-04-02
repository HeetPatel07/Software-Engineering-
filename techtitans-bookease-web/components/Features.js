'use client'
import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Typography } from '@mui/material';
import AppShortcutIcon from '@mui/icons-material/AppShortcut';
import Pagination from '@mui/material/Pagination';
import Filter1Icon from '@mui/icons-material/Filter1';
import Filter2Icon from '@mui/icons-material/Filter2';
import Filter3Icon from '@mui/icons-material/Filter3';
import Filter4Icon from '@mui/icons-material/Filter4';
import Filter5Icon from '@mui/icons-material/Filter5';
import Filter6Icon from '@mui/icons-material/Filter6';
import Filter7Icon from '@mui/icons-material/Filter7';
function FeatureDescription({ title, description, pic, IconComp }) {
    return (
        <>
            <Grid item xs={6} sx={{
                display: 'flex',
                flexDirection: 'column',
                justifyContent: 'center',
            }}>
                <Typography variant='h4' sx={{ ml: 10, color: 'white', textTransform: 'capitalize' }}>
                    <IconComp sx={{ color: 'white', mr: 2, height: 35, width: 35 }} />
                    {title}
                </Typography>
                <Typography variant='h6' sx={{ ml: 10, mt: 5, color: 'white', textTransform: 'capitalize' }}>
                    {description}
                </Typography>
            </Grid>
            <Grid item xs={6} sx={{ mt: 3 }}>
                <Box
                    sx={{
                        ml: 10,
                        position: 'relative',
                        width: '340px',
                        height: '470px',
                        display: 'inline-block',
                        backgroundImage: 'url(/pixel.png)',
                        backgroundSize: 'cover',
                        backgroundPosition: 'center',
                    }}
                >
                    <Box
                        component="img"
                        src={pic}
                        sx={{
                            position: 'absolute',
                            top: '50%',
                            left: '50%',
                            width: 260,
                            transform: 'translate(-50%, -50%)',
                        }}
                        alt="App Icon"
                    />
                </Box>
            </Grid>
        </>
    )
}


export default function Features() {
    const [page, setPage] = React.useState(1);
    const handleChange = (event, value) => {
        setPage(value);
    };

    const featuresNUm = 7;

    const f1 = "sell books";
    const f2 = "favorite book list";
    const f3 = "buy books";
    const f4 = "ratings and comments";
    const f5 = "view books info";
    const f6 = "search for books";
    const f7 = "create account (prof and student)";

    const features = [
        {
            title: "Create Account",
            description: "Sign up quickly as a student or a professor.",
            pic: "create-account.png",
            IconComp: Filter1Icon
        },
        {
            title: "Search For Books",
            description: "Search for books with long name with only few letters!",
            pic: "search-book.png",
            IconComp: Filter2Icon
        },
        {
            title: "View Books Information",
            description: "Quickly view the books information and review.",
            pic: "view-bookinfo.png",
            IconComp: Filter3Icon
        },
        {
            title: "Ratings and Comments",
            description: "Not sure about the book? View ratings and comments from verified customers.",
            pic: "ratings-comments.png",
            IconComp: Filter4Icon
        },
        {
            title: "Favorite Book List",
            description: "Find a book that is interesting?. The application can save it for you to view and buy it later.",
            pic: "favourite-book.png",
            IconComp: Filter5Icon
        },
        {
            title: "Sell Preowned Books",
            description: "The customers can sell their used books. For the price they decide.",
            pic: "sell-book.png",
            IconComp: Filter6Icon
        },
        {
            title: "Buy Book",
            description: "Save all the books you like in a cart. Confirm the address when you checkout!",
            pic: "buy-book.png",
            IconComp: Filter7Icon
        },
    ]


    return (
        <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={2} sx={{ px: 25 }}>
                <Grid item xs={12} sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: '100%',
                    mt: 6
                }}>
                    <AppShortcutIcon sx={{ color: 'white', mr: 2, height: 35, width: 35 }} />
                    <Typography variant='h4' sx={{ color: 'white' }}>
                        Features
                    </Typography>
                </Grid>
                <FeatureDescription
                    title={features[page - 1].title}
                    description={features[page - 1].description}
                    pic={features[page - 1].pic}
                    IconComp={features[page - 1].IconComp}
                />
                <Grid item xs={12} sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    mt: 5
                }}>
                    <Pagination
                        count={featuresNUm}
                        page={page}
                        onChange={handleChange}
                        color='primary'
                        size="large"
                        sx={{
                            '& .MuiPaginationItem-root': {
                                color: 'white',
                            },
                        }}
                    />
                </Grid>
            </Grid>
        </Box>
    )
}