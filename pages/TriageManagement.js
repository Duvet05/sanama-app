import React, { useState } from "react";
import {
  Container,
  Grid,
  Typography,
  Paper,
  TextField,
  Button as MUIButton,
  InputAdornment,
} from "@mui/material";
import SearchIcon from "@mui/icons-material/Search";
import TriageOrdersTable from "@/components/Triage/TriageOrdersTable"; 
import MainLayout from "@/components/layout/MainLayout";
import WidgetStateHolder from "@/components/DATE/WidgetStateHolder";
import EditIcon  from "@mui/icons-material/Edit";
import Box from '@mui/material/Box';
import Link from 'next/link';
import axios from 'axios';

const TriageManagement = () => {
  const [patientName, setPatientName] = useState("");
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState(null);

  const handleSearchClick = async () => {
    if (patientName.trim() !== "") {
      setError(null);
      
      try {
        const response = await axios.post('http://localhost:8080/admision/post/listarTriajePorFiltro', {
          patientName: patientName,  // nombre del paciente en la solicitud
        }, {
          headers: {
            'Content-Type': 'application/json',
          },
        });
    
        console.log(response.data);
        setOrders(response.data);  // lista de triajes
      
      } catch (error) {
        console.error("Error al buscar triajes:", error);
        setError('Error al buscar triajes.');
      }
    }
  };
  
  return (
    <MainLayout>
      <Container maxWidth={false} style={{ height: "100vh" }}>
        <Typography
          variant="h4"
          sx={{
            color: "#000",
            gap: "0.1mm",
            marginBottom: "5px",
            marginTop: "-50px",
          }}
        >
          Gestión de Triajes
        </Typography>
        <WidgetStateHolder providerId={1} />

        <Paper sx={{ my: 2, p: 2 }}>
          <Grid 
                container
                spacing={2}
                alignItems="center"
                justifyContent="space-between"
                sx={{ flexWrap: 'nowrap' }}>
              <Grid item xs={12} md={8} sx={{ display: 'flex', alignItems: 'center' }}>
                  <TextField
                      style={{ width: '333px' }}
                      variant="outlined"
                      label="Buscar por Nombre..."
                      value={patientName}
                      onChange={(e) => setPatientName(e.target.value)}
                      InputProps={{
                          startAdornment: (
                              <InputAdornment position="start"></InputAdornment>
                          ),
                      }}
                  /> 
                  <MUIButton
                      variant="contained"
                      sx={{
                          width: '150px',
                          backgroundColor: "#2196f3",
                          color: "white",
                          textTransform: "none",
                          "&:hover": {
                              backgroundColor: "#b3b3b3",
                          },
                          marginLeft: "0.5em"
                      }}
                      startIcon={<SearchIcon />}
                      onClick={handleSearchClick}
                  >
                      Buscar
                  </MUIButton>
              </Grid>

              <Grid item xs={12} md={6} sx={{ display: "flex", justifyContent: "flex-end" }}>
                <Link href="/NewTriagePage">
                  <MUIButton              
                      variant="contained"
                      sx={{
                          width: '150px', 
                          backgroundColor: "#2196f3", 
                          color: "white",
                          textTransform: "none",
                          "&:hover": {
                              backgroundColor: "#b3b3b3",
                          },
                      }}
                      startIcon={<EditIcon  />}
                  >
                  <Link href="/newTriage">Editar</Link>
                  </MUIButton> 
                </Link>
              </Grid>
          </Grid>
      </Paper>



        {error && (
          <Typography variant="body2" color="error">
            {error}
          </Typography>
        )}

        <TriageOrdersTable orders={orders} />
      </Container>
    </MainLayout>
  );
};

export default TriageManagement;
