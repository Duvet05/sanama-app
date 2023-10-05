import React, { useState, useEffect } from "react";
import { Typography, Paper, Grid, Button, Container } from "@mui/material";
import AppointmentForm from "../../components/formNewAppointment/AppointmentForm";
import AskForTriage from "../../components/formNewAppointment/AskForTriage";
import AppointmentInfo from "../../components/formNewAppointment/AppointmentInfo";
import NavigationButtons from "../../components/common/NavigationButtons";
import SelectMedic from "../../components/formNewAppointment/SelectMedic";

const Appointments = () => {
  const [currentPage, setCurrentPage] = useState(0);

  const navigate = (delta) => {
    setCurrentPage((prevPage) =>
      Math.min(Math.max(prevPage + delta, 0), PAGES.length - 1)
    );
  };

  const PAGES = [
    <AppointmentForm navigate={navigate} />,
    <SelectMedic />,
    <AskForTriage />,
    <AppointmentInfo />,
  ];

  const PAGE_TITLES = [
    "Información del paciente",
    "Seleccionar médico",
    "Mandar a triaje",
    "Visualizar Atención",
  ];

  useEffect(() => {
    const confirmExit = (e) => {
      e.preventDefault();
      e.returnValue =
        "¿Está seguro de que desea abandonar esta página? Sus datos no guardados se perderán.";

      return ""; // Para navegadores antiguos
    };

    window.addEventListener("beforeunload", confirmExit);

    return () => {
      window.removeEventListener("beforeunload", confirmExit);
    };
  }, []);

  return (
    <Container>
      <Paper elevation={3} style={{ padding: "20px", marginTop: "20px" }}>
        <Typography variant="h4" gutterBottom>
          Nueva Atención
        </Typography>
        <hr />
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <Typography variant="h5" gutterBottom>
              {PAGE_TITLES[currentPage]}
            </Typography>
            {PAGES[currentPage]}
          </Grid>
          <Grid item xs={12}>
            <NavigationButtons
              currentPage={currentPage}
              totalPages={PAGES.length}
              navigate={navigate}
            />
          </Grid>
        </Grid>
      </Paper>
    </Container>
  );
};

export default Appointments;
