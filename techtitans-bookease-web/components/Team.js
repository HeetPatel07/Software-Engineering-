'use client'
import React, { useCallback } from 'react';
import ReactFlow, { useNodesState, useEdgesState, addEdge, Background } from 'reactflow';
import 'reactflow/dist/base.css';
import CustomNode from './CustomNode';
import Box from '@mui/material/Box';
import Grid from '@mui/material/Grid';
import { Typography } from '@mui/material';
import Diversity3Icon from '@mui/icons-material/Diversity3';
const nodeTypes = {
    custom: CustomNode,
};

const initNodes = [
    {
        id: '1',
        type: 'custom',
        data: {
            name: 'Arrayman Pol',
            job: <ul>
                <p>Project Leader</p>
                <li>3 tiered architecture design</li>
                <li>Backend design</li>
                <li>Database Implementation</li>
                <li>System Tests</li>
            </ul>,
            emoji: '😎'
        },
        position: { x: 0, y: 50 },
    },
    {
        id: '2',
        type: 'custom',
        data: {
            name: 'Chang Geng', job: <ul>
                <p>Developer</p>
                <li>Android UI Development</li>
                <li>Connecting a database an application</li>
                <li>Documentation</li>
                <li>Connect Presentation, Logic and Persistence</li>
            </ul>, emoji: '🤓'
        },

        position: { x: -350, y: 300 },
    },
    {
        id: '3',
        type: 'custom',
        data: {
            name: 'Heet Patel', job: <ul>
                <p>Developer</p>
                <li>Android UI Development</li>
                <li>Connecting a database an application</li>
                <li>Integration Tests</li>
                <li>Connect Presentation, Logic and Persistence</li>
            </ul>, emoji: '🤩'
        },
        position: { x: -200, y: 600 },
    },
    {
        id: '4',
        type: 'custom',
        data: {
            name: 'Heping Dong', job: <ul>
                <p>Developer</p>
                <li>Unit Tests</li>
                <li>Integration Tests</li>
                <li>System Tests</li>
                <li>Scheduling each iteration</li>
            </ul>, emoji: '🤩'
        },
        position: { x: 250, y: 300 },
    },

    {
        id: '5',
        type: 'custom',
        data: {
            name: 'Mahamud Hasan Asif', job: <ul>
                <p>Developer</p>
                <li>Integration Tests</li>
                <li>Backend Development</li>
                <li>Documentation</li>
            </ul>, emoji: '🤩'
        },
        position: { x: 350, y: 600 },
    },
];

const initEdges = [
    {
        id: 'e1-2',
        source: '1',
        target: '2',
        animated: true,
        style: {
            strokeWidth: 2,
            stroke: '#00e676',
        },
    },
    {
        id: 'e1-3',
        source: '1',
        target: '3',
        animated: true,
        style: {
            strokeWidth: 2,
            stroke: '#00e676',
        },
    },
    {
        id: 'e1-4',
        source: '1',
        target: '4',
        animated: true,
        style: {
            strokeWidth: 2,
            stroke: '#00e676',
        },
    },

    {
        id: 'e1-5',
        source: '1',
        target: '5',
        animated: true,
        style: {
            strokeWidth: 2,
            stroke: '#00e676',
        },
    },
];

export default function Team() {

    const [nodes, setNodes, onNodesChange] = useNodesState(initNodes);
    const [edges, setEdges, onEdgesChange] = useEdgesState(initEdges);

    const onConnect = useCallback((params) => setEdges((eds) => addEdge(params, eds)), []);

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
                    <Diversity3Icon sx={{ color: '#757575', mr: 2, height: 35, width: 35 }} />
                    <Typography variant='h4' sx={{ color: '#757575' }}>
                        Team
                    </Typography>
                </Grid>
                <Grid item xs={12} sx={{ mt: 1, mx: 40, height: '80vh' }}>
                    <ReactFlow
                        nodes={nodes}
                        edges={edges}
                        onNodesChange={onNodesChange}
                        onEdgesChange={onEdgesChange}
                        onConnect={onConnect}
                        nodeTypes={nodeTypes}
                        fitView
                        nodesDraggable={false}
                    >
                        <Background color="#aaa" gap={16} />
                    </ReactFlow>
                </Grid>
            </Grid>
        </Box>

    );
}