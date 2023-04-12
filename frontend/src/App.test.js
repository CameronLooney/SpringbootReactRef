import React, { useEffect, useState } from 'react';
import Box from '@mui/material/Box';
import Paper from '@mui/material/Paper';
import Typography from '@mui/material/Typography';
import Avatar from '@mui/material/Avatar';
import { blue, green, orange, purple, red } from '@mui/material/colors';

const StudentList = () => {


  
  const getRandomColor = () => {
    const colors = [blue[500], green[500], orange[500], purple[500], red[500]];
    return colors[Math.floor(Math.random() * colors.length)];
  };

  return (
    <Paper elevation={3} sx={{ padding: '20px', width: 600, margin: '20px auto' }}>
      <Typography variant="h4" component="h1" align="center" gutterBottom>
        Student List
      </Typography>
      {students.length > 0 ? (
        <Box sx={{ display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          {students.map((student) => (
            <Box
              key={student.id}
              sx={{
                width: '300px',
                margin: '10px',
                padding: '10px',
                borderRadius: '10px',
                backgroundColor: getRandomColor(),
                color: 'white',
              }}
            >
              <Avatar sx={{ bgcolor: 'white', color: getRandomColor(), mb: '10px' }}>
                {student.name.charAt(0).toUpperCase()}
              </Avatar>
              <Typography variant="h5" component="h2" mb={1}>
                {student.name}
              </Typography>
              <Typography variant="body1">{student.email}</Typography>
            </Box>
          ))}
        </Box>
      ) : (
        <Typography variant="body1" align="center">
          No students found.
        </Typography>
      )}
    </Paper>
  );
};

export default StudentList;
