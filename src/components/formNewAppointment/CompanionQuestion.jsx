import React from "react";
import {
  Typography,
  RadioGroup,
  FormControlLabel,
  Radio,
  Grid,
} from "@mui/material";

function CompanionQuestion({ value, onChange }) {
  return (
    <Grid item xs={12}>
      <Typography variant="h6" gutterBottom>
        ¿El paciente tiene un acompañante?
      </Typography>
      <RadioGroup row value={value} onChange={onChange}>
        <FormControlLabel value="yes" control={<Radio />} label="Sí" />
        <FormControlLabel value="no" control={<Radio />} label="No" />
      </RadioGroup>
    </Grid>
  );
}

export default CompanionQuestion;
