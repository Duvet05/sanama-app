package com.minsa.sanama.repository.admision;

import com.minsa.sanama.model.admision.Triaje;
import com.minsa.sanama.model.atencionmedica.CitaMedica;
import com.minsa.sanama.model.admision.Paciente;
import com.minsa.sanama.model.rrhh.Medico;
import com.minsa.sanama.model.rrhh.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

@Repository
public class TriajeRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public TriajeRepository(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    private final TriajeMapper triajeMapper = new TriajeMapper();

    public List<CitaMedica> listarTriajePorFiltro(String pv_filtro) {
        String procedureCall = "{call dbSanama.ssm_adm_listar_triaje_por_filtro('"+pv_filtro+"')};";
        return jdbcTemplate.query(procedureCall, triajeMapper);
    }

    public int actualizarTriaje(Triaje triaje) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbSanama")
                .withProcedureName("ssm_adm_actualizar_triaje")
                .declareParameters(
                        new SqlParameter("pn_id_triaje", Types.INTEGER),
                        new SqlParameter("pn_peso", Types.INTEGER),
                        new SqlParameter("pn_talla", Types.INTEGER),
                        new SqlParameter("pn_temperatura", Types.INTEGER),
                        new SqlParameter("pv_motivo_visita", Types.VARCHAR),
                        new SqlParameter("pn_presion_arterial", Types.INTEGER),
                        new SqlParameter("pv_condicionesPrexistentes", Types.VARCHAR),
                        new SqlParameter("pv_prioridad", Types.VARCHAR),
                        new SqlParameter("pn_estado", Types.INTEGER),
                        new SqlParameter("pn_saturacionOxigeno", Types.INTEGER),
                        new SqlParameter("pn_frecuenciaCardiaca", Types.INTEGER),
                        new SqlParameter("pv_nivelConciencia", Types.VARCHAR),
                        new SqlParameter("pv_nivelDolor", Types.VARCHAR)
                );
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource
                .addValue("pn_id_triaje", triaje.getIdTriaje())
                .addValue("pn_peso", triaje.getPeso())
                .addValue("pn_talla", triaje.getTalla())
                .addValue("pn_temperatura", triaje.getTemperatura())
                .addValue("pv_motivo_visita", triaje.getMotivoVisita())
                .addValue("pn_presion_arterial", triaje.getPresionArterial())
                .addValue("pv_condicionesPrexistentes", triaje.getCondicionesPrexistentes())
                .addValue("pv_prioridad", triaje.getPrioridad())
                .addValue("pn_estado", triaje.getEstado())
                .addValue("pn_saturacionOxigeno", triaje.getSaturacionOxigeno())
                .addValue("pn_frecuenciaCardiaca", triaje.getFrecuenciaCardiaca())
                .addValue("pv_nivelConciencia", triaje.getNivelConciencia())
                .addValue("pv_nivelDolor", triaje.getNivelDolor());

        Map<String, Object> result = simpleJdbcCall.execute(mapSqlParameterSource);

        if (result.containsKey("ERROR_CODE") || result.containsKey("ERROR_MESSAGE")) {
            return 0;
        } else {
            return 1;
        }
    }

    private static class TriajeMapper implements RowMapper<CitaMedica> {
        @Override
        public CitaMedica mapRow(ResultSet rs, int rowNum) throws SQLException {

            Triaje triaje = new Triaje();
            // Mapea los campos de Triaje
            triaje.setIdTriaje(rs.getInt("id_triaje"));
            triaje.setCodigoTriaje(rs.getString("codigo_triaje"));
            triaje.setPeso(rs.getInt("peso"));
            triaje.setTalla(rs.getInt("talla"));
            triaje.setTemperatura(rs.getInt("temperatura"));
            triaje.setMotivoVisita(rs.getString("motivo_visita"));
            triaje.setPresionArterial(rs.getInt("presion_arterial"));
            triaje.setPrioridad(rs.getString("prioridad"));
            triaje.setEstado(rs.getInt("estado"));
            triaje.setFechaTriaje(rs.getDate("fecha").toLocalDate());
            triaje.setHoraTriaje(rs.getTime("hora").toLocalTime());
            triaje.setSaturacionOxigeno(rs.getString("saturacionOxigeno"));
            triaje.setFrecuenciaCardiaca(rs.getString("frecuenciaCardiaca"));
            triaje.setNivelConciencia(rs.getString("nivelConciencia"));
            triaje.setNivelDolor(rs.getString("nivelDolor"));
            triaje.setCondicionesPrexistentes(rs.getString("condicionesPrexistentes"));

            // Mapea ProgramacionCita
            CitaMedica programacionCita = new CitaMedica();
            programacionCita.setIdCita(rs.getInt("id_cita"));
            programacionCita.setCodigoCita(rs.getString("codigo_cita_laboratorio"));
            programacionCita.setTipoCita(rs.getString("tipo_cita"));
            programacionCita.setHoraCita(rs.getTime("hora_cita").toLocalTime());
            programacionCita.setFechaCita(rs.getDate("fecha_cita").toLocalDate());
            programacionCita.setLugarCita(rs.getString("lugar_cita"));
            programacionCita.setRequiereTriaje(rs.getInt("requiere_triaje"));

            // Mapea Medico
            Medico medico = new Medico();
            medico.setNombres(rs.getString("medico_nombres"));
            medico.setApellidoPaterno(rs.getString("medico_apellido_paterno"));
            medico.setApellidoMaterno(rs.getString("medico_apellido_materno"));

            // Mapea Especialidad
            Especialidad especialidad = new Especialidad();
            especialidad.setNombre(rs.getString("especialidad"));

            // Mapea Paciente
            Paciente paciente = new Paciente();
            paciente.setNombres(rs.getString("paciente_nombres"));
            paciente.setApellidoPaterno(rs.getString("paciente_apellido_paterno"));
            paciente.setApellidoMaterno(rs.getString("paciente_apellido_materno"));
            paciente.setDni(rs.getString("paciente_dni"));
            paciente.setFechaNacimiento(rs.getDate("paciente_fecha_nacimiento").toLocalDate());
            paciente.setSexo(rs.getString("paciente_sexo"));

            // Establece las relaciones
            programacionCita.setTriaje(triaje);
            medico.setEspecialidad(especialidad);
            programacionCita.setMedico(medico);
            programacionCita.setPaciente(paciente);

            return programacionCita;
        }
    }
}