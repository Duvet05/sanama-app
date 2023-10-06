// Appointments.jsx
import React, { useState, useEffect } from "react";
import { Typography, Paper, Grid, Container } from "@mui/material";
import AppointmentForm from "../components/appointments/AppointmentForm";
import AppointmentInfo from "./AppointmentInfo";
import SelectMedic from "../components/appointments/SelectMedic";
import NavigationButtons from "../components/common/NavigationButtons";
import MainLayout from "@/components/layout/MainLayout";
import TriajeONo from "../components/appointments/TriageRequest";
import CustomizedDialog from "@/components/appointments/CustomizedDialog";

const CONFIRM_EXIT_MESSAGE =
  "¿Está seguro de que desea abandonar esta página? Sus datos no guardados se perderán.";

const PAGES = [
  { component: <AppointmentForm />, title: "Información del paciente" },
  { component: <SelectMedic />, title: "Seleccionar médico" },
  { component: <TriajeONo />, title: "Triage" },
  { component: <AppointmentInfo />, title: "Información de la cita" },
];

const Appointments = () => {
  const [currentPage, setCurrentPage] = useState(0);
  const [isProcessCompleted, setIsProcessCompleted] = useState(false);

  const navigate = (delta) => {
    const nextPage = Math.min(
      Math.max(currentPage + delta, 0),
      PAGES.length - 1
    );
    setCurrentPage(nextPage);

    if (nextPage === PAGES.length - 1) {
      setIsProcessCompleted(true);
    }
  };

  useEffect(() => {
    const confirmExit = (e) => {
      e.preventDefault();
      e.returnValue = CONFIRM_EXIT_MESSAGE;
      return "";
    };

    window.addEventListener("beforeunload", confirmExit);

    return () => {
      window.removeEventListener("beforeunload", confirmExit);
    };
  }, []);

  return (
    <MainLayout>
      <Container maxWidth={false} style={{ height: "auto" }}>
        <Paper elevation={3} style={{ padding: "20px", marginTop: "20px" }}>
          <Typography variant="h4" gutterBottom style={{ marginBottom: "10px" }}>
                Nueva Atención
          </Typography>
          <hr />
          <Grid>
            <Grid item xs={12}>
              <Typography variant="h5" gutterBottom>
                {PAGES[currentPage].title}
              </Typography>
              {PAGES[currentPage].component}
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
      {isProcessCompleted && (
        <CustomizedDialog
          onClose={() => setIsProcessCompleted(false)}
          open={isProcessCompleted}
        />
      )}
    </MainLayout>
  );
};

export default Appointments;
