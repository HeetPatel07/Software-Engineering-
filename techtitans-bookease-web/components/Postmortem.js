'use client'
import * as React from 'react';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Typography } from '@mui/material';
import PsychologyIcon from '@mui/icons-material/Psychology';
import dynamic from 'next/dynamic';
const Chart = dynamic(() => import('react-apexcharts'), { ssr: false });


function PieChart() {
    const series = [3, 4, 3, 1, 2, 2, 2.5];

    const chartOptions = {
        chart: {
            type: 'pie',
            toolbar: {
                show: true,
                tools: {
                    download: true
                }
            },
        },
        labels: ['Create Account', 'Book Search', 'View Books', 'Ratings and Comments', 'Favorite Book', 'Sell Books', 'Buy Books'],
        stroke: {
            width: 3
        },
        title: {
            text: 'Actual Time for Each Feature',
        },
        responsive: [{
            breakpoint: 480,
            options: {
                chart: {
                    width: 200
                },
            }
        }],

        legend: {
            position: 'top'
        }
    };

    return (
        <Chart options={{ ...chartOptions, labels: chartOptions.labels }}
            series={series}
            type='pie'
            width='100%'
            height={350}
        />
    )
}

function BarChart() {

    const chartData = {

        series: [{
            name: 'Estimated Days',
            data: [2, 3, 2, 2, 4, 3, 3]
        }, {
            name: 'Actual Days',
            data: [3, 4, 3, 1, 2, 2, 2.5]
        }],
        options: {
            chart: {
                type: 'bar',
                height: 350
            },
            plotOptions: {
                bar: {
                    horizontal: false,
                    columnWidth: '55%',
                    endingShape: 'rounded'
                },
            },
            dataLabels: {
                enabled: true
            },
            stroke: {
                show: true,
                width: 2,
                colors: ['transparent']
            },
            xaxis: {
                categories: ['Create Account', 'Book Search', 'View Books', 'Ratings and Comments', 'Favorite Book', 'Sell Books', 'Buy Books'],
            },
            yaxis: {
                title: {
                    text: 'days'
                }
            },
            fill: {
                opacity: 1
            },
            title: {
                text: 'Estimated Time VS Actual Time',
            },
            tooltip: {
                y: {
                    formatter: function (val) {
                        return val + "days"
                    }
                }
            },
            legend: {
                position: 'top',
            }
        },

    };

    return (
        <Chart options={chartData.options} series={chartData.series} type="bar" width="100%" height={400} />
    );
}

export default function Postmortem() {
    return (
        <Box sx={{ flexGrow: 1 }}>
            <Grid container spacing={3}>
                <Grid item xs={12} sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: '100%',
                    mt: 6
                }}>
                    <PsychologyIcon sx={{ color: '#757575', mr: 2, height: 35, width: 35 }} />
                    <Typography variant='h4' sx={{ color: '#757575' }}>
                        Development Process
                    </Typography>
                </Grid>
                <Grid item xs={12}>
                    <Box sx={{ ml: 10 }}>
                        <Typography variant='body1' sx={{ color: '#757575' }}>
                            Below is the chart depicting estimated vs actual time for the development of this application
                        </Typography>
                    </Box>
                </Grid>
                <Grid item xs={6}>
                    <Box sx={{ ml: 10, mt: 1 }}>
                        <BarChart />
                    </Box>
                </Grid>
                <Grid item xs={6}>
                    <Box sx={{ mr: 10, mt: 1 }}>
                        <PieChart />
                    </Box>
                </Grid>
                <Grid item xs={12} sx={{
                    display: 'flex',
                    justifyContent: 'center',
                    alignItems: 'center',
                    height: '100%',
                }}>
                    <PsychologyIcon sx={{ color: '#757575', mr: 2, height: 35, width: 35 }} />
                    <Typography variant='h4' sx={{ color: '#757575' }}>
                        Postmortem
                    </Typography>
                </Grid>
                <Grid item xs={12}>
                    <Box sx={{ mx: 10 }}>
                        <Typography variant='h6' sx={{ color: '#757575' }}>
                            What took the most time? The least? Any surprises?
                        </Typography>
                        <Typography variant='subtitle2' sx={{ color: '#757575' }}>
                            Throughout the project, the most time-consuming task proved to be setting up the database,
                            followed closely by refactoring the codebase and addressing existing technical debt.
                            Surprisingly, the implementation of the favorite book feature took the least amount of time.
                            However, a notable surprise emerged during development when an error was discovered in the database reading script provided in the sample project.
                            Specifically, a fixed character array of size 1024 caused unexpected issues, leading to additional troubleshooting and debugging efforts.
                        </Typography>
                    </Box>
                </Grid>
                <Grid item xs={12}>
                    <Box sx={{ mx: 10 }}>
                        <Typography variant='h6' sx={{ color: '#757575' }}>
                            What did you learn about team or large project development? What will you start doing, keep doing, or stop doing next time?
                        </Typography>
                        <Typography variant='subtitle2' sx={{ color: '#757575' }}>
                            Team or large project development often involves the time-consuming process of analyzing the strengths of team members, which can sometimes lead to delays in the development process. Moving forward, we recognize the importance of assigning tasks with realistic time expectations and deadlines to ensure the project stays on track.
                            Additionally, we have begun implementing backup plans, recognizing their crucial role in managing unexpected delays and ensuring adequate time for team meetings to discuss project progress.
                            To streamline our development process, we plan to stop allocating entire features to individual team members, as this often results in numerous merge conflicts and consumes significant time to resolve. Instead, we will focus on breaking down tasks into smaller, more manageable components, facilitating smoother collaboration and reducing the likelihood of conflicts arising.
                        </Typography>
                    </Box>
                </Grid>
                <Grid item xs={12}>
                    <Box sx={{ mx: 10 }}>
                        <Typography variant='h6' sx={{ color: '#757575' }}>
                            What went right in the development process?
                        </Typography>
                        <Typography variant='subtitle2' sx={{ color: '#757575' }}>
                            Several aspects of the development process went smoothly and contributed to the success of the project.
                            Firstly, the creation of an architecture diagram proved to be highly beneficial,
                            as it provided a clear overview of the system's structure and facilitated the seamless addition of additional functionality.
                            Additionally, leveraging appropriate design patterns at specific instances significantly aided the development process across all three iterations,
                            ensuring code maintainability and scalability. The utilization of design patterns learned in class, particularly those promoting low coupling,
                            enabled the easy incorporation of system tests during the third iteration,
                            enhancing the overall robustness and quality of the application.
                        </Typography>
                    </Box>
                </Grid>
            </Grid>
        </Box>
    )
}