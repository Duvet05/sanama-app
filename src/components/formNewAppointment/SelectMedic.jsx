import React, { useState, useEffect } from "react";
import dayjs from "dayjs";
import {
  Button,
  FormControl,
  InputLabel,
  Select,
  MenuItem,
  TextField,
} from "@mui/material";
import Autocomplete from "@mui/material/Autocomplete";
import Selectdate from "./Selectdate";
import { medicService } from "../../services/medicService";

function SelectMedic() {
  const [isLoading, setIsLoading] = useState(true);
  const [specialities, setSpecialties] = useState([]);
  const [selectedSpeciality, setSelectedSpeciality] = useState(null);
  const [doctors, setDoctors] = useState([]);
  const [selectedDoctor, setSelectedDoctor] = useState("");
  const [selectedDate, setSelectedDate] = useState(null);
  const [availableHours, setAvailableHours] = useState([]);
  const [selectedHour, setSelectedHour] = useState(null);

  const fetchAvailableHours = (fecha, medicId) => {
    medicService
      .buscarHorariosByID(fecha, medicId)
      .then((data) => {
        setAvailableHours(data);
      })
      .catch((error) => {
        console.error("Error fetching available hours:", error);
      });
  };

  // Effects
  useEffect(() => {
    if (selectedDate && selectedDoctor) {
      fetchAvailableHours(selectedDate, selectedDoctor); // Corregido el nombre de la función aquí
    }
  }, [selectedDate, selectedDoctor]);

  useEffect(() => {
    medicService
      .listarEspecialidades()
      .then((data) => {
        setSpecialties(data);
        setIsLoading(false);
      })
      .catch((error) => {
        setIsLoading(false);
        console.error("Error fetching specialties:", error);
      });
  }, []);

  // Utility functions and handlers
  const obtenerDoctoresPorEspecialidad = (especialidadNombre) => {
    medicService
      .buscarPorEspecialidad(especialidadNombre)
      .then((data) => {
        setDoctors(data);
      })
      .catch((error) => {
        console.error("Error fetching doctors:", error);
      });
  };

  const handleHourChange = (hour) => {
    setSelectedHour(dayjs(hour).format("HH:mm:ss"));
  };

  return (
    <div>
      <Autocomplete
        fullWidth
        disabled={isLoading}
        options={specialities}
        getOptionLabel={(option) => option.nombre}
        value={selectedSpeciality}
        onChange={(event, newValue) => {
          setSelectedSpeciality(newValue);
          setSelectedDoctor("");
          if (newValue) {
            obtenerDoctoresPorEspecialidad(newValue.nombre);
          } else {
            setDoctors([]); // Clear doctor list if no speciality is selected
          }
        }}
        renderInput={(params) => (
          <TextField
            {...params}
            label="Especialidad"
            variant="outlined"
            placeholder="Seleccionar Especialidad"
          />
        )}
      />

      {selectedSpeciality && (
        <FormControl variant="outlined" fullWidth margin="normal">
          <InputLabel>Médico</InputLabel>
          <Select
            value={selectedDoctor}
            onChange={(event) => setSelectedDoctor(event.target.value)}
          >
            {doctors.map((doctor) => (
              <MenuItem key={doctor.idPersona} value={doctor.idPersona}>
                {`${doctor.sexo === "M" ? "Dr." : "Dra."} ${doctor.nombres} ${
                  doctor.apellidoPaterno
                } ${doctor.apellidoMaterno}`}
              </MenuItem>
            ))}
          </Select>
        </FormControl>
      )}

      {selectedDoctor && (
        <Selectdate
          onDateChange={setSelectedDate}
          selectedDate={selectedDate}
          availableHours={availableHours}
          onHourChange={handleHourChange}
          selectedHour={selectedHour}
        />
      )}

      {selectedDoctor && selectedDate && selectedHour && (
        <Button
          variant="contained"
          color="primary"
          fullWidth
          style={{ marginTop: "20px" }}
        >
          Reservar Cita con {selectedDoctor}
        </Button>
      )}
    </div>
  );
}

export default SelectMedic;
