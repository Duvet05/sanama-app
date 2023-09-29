// External libraries
import React from 'react';
import { RouteType } from "./config";


//Iconos
import { Diversity1, MedicalServices, Today , WorkHistory , MonitorHeart, Vaccines, Settings}   from '@mui/icons-material';

// Pages
import HomePage from "../pages/home/HomePage";
import PatientPage from "../pages/PatientManagement/PatientPage";
import StaffManagement from "../pages/StaffManagement/StaffManagement";
import StaffPage from "../pages/StaffManagement/StaffPage";
import AttentionLogPage from "../pages/StaffManagement/AttentionLogPage";
import SchedulePage from "../pages/StaffManagement/SchedulePage";
import TriagePage from "../pages/MedicalAssistance/TriagePage";
import NewAttentionPage from "../pages/MedicalAssistance/NewAttentionPage";
import LaboratoryPage from "../pages/LaboratoryManagement/LaboratoryPage";
import ConfigurationPage from "../pages/ConfigurationManagement/ConfigurationPage";

const appRoutes: RouteType[] = [
  {
    index: true,
    element: <HomePage />,
    state: "home"
  },
  {
    path: "/pacientes",
    element: <PatientPage />,
    state: "patient",
    sidebarProps: {
      displayText: "Pacientes",
      icon: <Diversity1 />
    }
  },
  {
    path: "/medicos",
    element: <StaffPage />,
    state: "staff",
    sidebarProps: {
      displayText: "Medicos",
      icon: <MedicalServices />
    },
  },

 
  {
    path: "/horarios",
    element: <SchedulePage />,
    state: "staff.schedules",
    sidebarProps: {
      displayText: "Horarios",
      icon: <Today />
    }
  },
  {
    path: "/citas",
    element: <AttentionLogPage />,
    state: "staff.attentions",
    sidebarProps: {
      displayText: "Citas",
      icon: <WorkHistory />
    }
  },

  {
    path: "/citas/new",
    element: <NewAttentionPage />,
    state: "assistance.new",
    sidebarProps: {
      displayText: "Nueva Atencion",
      icon: <WorkHistory />
    }
  },
  {
    path: "/triaje",
    element: <TriagePage />,
    state: "assistance.triage",
    sidebarProps: {
      displayText: "Triaje",
      icon: <MonitorHeart />
    },
  },
  {
    path: "/laboratorio",
    element: <LaboratoryPage />,
    state: "laboratory",
    sidebarProps: {
      displayText: "Laboratorio",
      icon: <Vaccines />
    }
  },
  {
    path: "/configuracion",
    element: <ConfigurationPage />,
    state: "configuration",
    sidebarProps: {
      displayText: "Configuracion",
      icon: <Settings />
    }
  }
];

export default appRoutes;