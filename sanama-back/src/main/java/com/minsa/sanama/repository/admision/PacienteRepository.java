package com.minsa.sanama.repository.admision;

import com.minsa.sanama.model.admision.Paciente;
import com.minsa.sanama.model.atencionmedica.HistorialClinico;
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
public class PacienteRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public PacienteRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    private final PacienteMapper pacienteMapper = new PacienteMapper();
    private final PacienteMapperSolo pacienteMapperSolo = new PacienteMapperSolo();
    private final PacienteHistorialMapper pacienteHistorialMapper = new PacienteHistorialMapper();

    public List<Paciente> listarPacientes() {
        String procedureCall = "{call dbSanama.ssm_adm_listar_paciente()};";
        return jdbcTemplate.query(procedureCall, pacienteMapper);
    }

    public List<Paciente> buscarPacienteFiltro(String pv_filtro){
        String procedureCall = "{call dbSanama.ssm_adm_buscar_paciente_filtro('"+pv_filtro+"')};";
        return jdbcTemplate.query(procedureCall, pacienteHistorialMapper);
    }


    public List<Paciente> buscarPaciente(String pv_nombre_dni){
        String procedureCall = "{call dbSanama.ssm_adm_buscar_paciente('"+pv_nombre_dni+"')};";
        return jdbcTemplate.query(procedureCall, pacienteMapperSolo);
    }

    public int actualizarPacienteShort(Paciente paciente){
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbSanama")
                .withProcedureName("ssm_adm_actualizar_paciente")
                .declareParameters(new SqlParameter[]{
                        new SqlParameter("pn_id_paciente ", Types.INTEGER),
                        new SqlParameter("pv_telefono", Types.VARCHAR),
                        new SqlParameter("pv_correo", Types.VARCHAR),
                        new SqlParameter("pv_direccion", Types.VARCHAR)
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource
                .addValue("pn_id_paciente", paciente.getIdPersona())
                .addValue("pv_telefono", paciente.getTelefono())
                .addValue("pv_correo", paciente.getCorreo())
                .addValue("pv_direccion", paciente.getDireccion());

        Map<String, Object> result = simpleJdbcCall.execute(mapSqlParameterSource);
        if(result.containsKey("ERROR_CODE") || result.containsKey("ERROR_MESSAGE")){
            return -1;
        }
        else return 1;
    }

    private static class PacienteMapperSolo implements RowMapper<Paciente> {
        @Override
        public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {

            Paciente paciente = new Paciente();

            paciente.setIdPersona(rs.getInt("id_paciente"));
            paciente.setNombres(rs.getString("nombres"));
            paciente.setApellidoPaterno(rs.getString("apellido_paterno"));
            paciente.setApellidoMaterno(rs.getString("apellido_materno"));
            paciente.setDni(rs.getString("dni"));

            return paciente;
        }
    }

    private static class PacienteMapper implements RowMapper<Paciente> {
        @Override
        public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {

            Paciente paciente = new Paciente();

            paciente.setIdPersona(rs.getInt("id_paciente"));
            paciente.setNombres(rs.getString("nombres"));
            paciente.setApellidoPaterno(rs.getString("apellido_paterno"));
            paciente.setApellidoMaterno(rs.getString("apellido_materno"));
            paciente.setDni(rs.getString("dni"));
            paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            paciente.setSexo(rs.getString("sexo"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setFoto(rs.getBytes("foto"));
            paciente.setCorreo(rs.getString("correo"));
            paciente.setDireccion(rs.getString("direccion"));
            paciente.setCodigoSeguro(rs.getString("codigo_seguro"));
            paciente.setTipoSeguro(rs.getString("tipo_seguro"));
            paciente.setEstado(rs.getInt("estado"));

            return paciente;
        }

    }

    private static class PacienteHistorialMapper implements RowMapper<Paciente> {
        @Override
        public Paciente mapRow(ResultSet rs, int rowNum) throws SQLException {

            Paciente paciente = new Paciente();

            paciente.setIdPersona(rs.getInt("id_paciente"));
            paciente.setNombres(rs.getString("nombres"));
            paciente.setApellidoPaterno(rs.getString("apellido_paterno"));
            paciente.setApellidoMaterno(rs.getString("apellido_materno"));
            paciente.setDni(rs.getString("dni"));
            paciente.setFechaNacimiento(rs.getDate("fecha_nacimiento").toLocalDate());
            paciente.setSexo(rs.getString("sexo"));
            paciente.setTelefono(rs.getString("telefono"));
            paciente.setFoto(rs.getBytes("foto"));
            paciente.setCorreo(rs.getString("correo"));
            paciente.setDireccion(rs.getString("direccion"));
            paciente.setCodigoSeguro(rs.getString("codigo_seguro"));
            paciente.setTipoSeguro(rs.getString("tipo_seguro"));
            paciente.setEstado(rs.getInt("estado"));
            paciente.setHistorialClinico(new HistorialClinico());
            paciente.getHistorialClinico().setIdHistorialClinico(rs.getInt("id_historial_clinico"));
            paciente.getHistorialClinico().setCodigo(rs.getString("codigo"));
            paciente.getHistorialClinico().setEstado(rs.getInt("historial_estado"));

            return paciente;
        }

    }

    public int registrarPaciente(Paciente paciente){
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbSanama")
                .withProcedureName("ssm_adm_registrar_paciente")
                .declareParameters(new SqlParameter[]{
                        new SqlOutParameter("pn_id_paciente ", Types.INTEGER),
                        new SqlParameter("pv_nombres ", Types.VARCHAR),
                        new SqlParameter("pv_apellido_paterno", Types.VARCHAR),
                        new SqlParameter("pv_apellido_materno", Types.VARCHAR),
                        new SqlParameter("pv_dni", Types.VARCHAR),
                        new SqlParameter("pd_fecha_nacimiento", Types.DATE),
                        new SqlParameter("pv_sexo", Types.VARCHAR),
                        new SqlParameter("pv_telefono", Types.VARCHAR),
                        new SqlParameter("pb_foto", Types.BLOB),
                        new SqlParameter("pv_codigo_seguro", Types.VARCHAR),
                        new SqlParameter("pv_tipo_seguro", Types.VARCHAR),
                        new SqlParameter("pv_correo", Types.VARCHAR),
                        new SqlParameter("pv_direccion", Types.VARCHAR)
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource
                .addValue("pv_nombres", paciente.getNombres())
                .addValue("pv_apellido_paterno", paciente.getApellidoPaterno())
                .addValue("pv_apellido_materno", paciente.getApellidoMaterno())
                .addValue("pv_dni", paciente.getDni())
                .addValue("pd_fecha_nacimiento", paciente.getFechaNacimiento())
                .addValue("pv_sexo", paciente.getSexo())
                .addValue("pv_telefono", paciente.getTelefono())
                .addValue("pb_foto", paciente.getFoto())
                .addValue("pv_codigo_seguro", paciente.getCodigoSeguro())
                .addValue("pv_tipo_seguro", paciente.getTipoSeguro())
                .addValue("pv_correo", paciente.getCorreo())
                .addValue("pv_direccion", paciente.getDireccion());

        Map<String, Object> result = simpleJdbcCall.execute(mapSqlParameterSource);
        if(result.containsKey("ERROR_CODE") || result.containsKey("ERROR_MESSAGE")){
            return -1;
        }
        else{
            int idPaciente = (int)result.get("pn_id_paciente");
            paciente.setIdPersona(idPaciente);
            return idPaciente;
        }
    }

    public int actualizarPaciente(Paciente paciente){
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbSanama")
                .withProcedureName("ssm_adm_actualizar_paciente")
                .declareParameters(new SqlParameter[]{
                        new SqlParameter("pn_id_paciente ", Types.INTEGER),
                        new SqlParameter("pv_nombres ", Types.VARCHAR),
                        new SqlParameter("pv_apellido_paterno", Types.VARCHAR),
                        new SqlParameter("pv_apellido_materno", Types.VARCHAR),
                        new SqlParameter("pv_dni", Types.VARCHAR),
                        new SqlParameter("pd_fecha_nacimiento", Types.DATE),
                        new SqlParameter("pv_sexo", Types.VARCHAR),
                        new SqlParameter("pv_telefono", Types.VARCHAR),
                        new SqlParameter("pb_foto", Types.BLOB),
                        new SqlParameter("pv_codigo_seguro", Types.VARCHAR),
                        new SqlParameter("pv_tipo_seguro", Types.VARCHAR),
                        new SqlParameter("pv_correo", Types.VARCHAR),
                        new SqlParameter("pv_direccion", Types.VARCHAR)
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource
                .addValue("pn_id_paciente", paciente.getIdPersona())
                .addValue("pv_nombres", paciente.getNombres())
                .addValue("pv_apellido_paterno", paciente.getApellidoPaterno())
                .addValue("pv_apellido_materno", paciente.getApellidoMaterno())
                .addValue("pv_dni", paciente.getDni())
                .addValue("pd_fecha_nacimiento", paciente.getFechaNacimiento())
                .addValue("pv_sexo", paciente.getSexo())
                .addValue("pv_telefono", paciente.getTelefono())
                .addValue("pb_foto", paciente.getFoto())
                .addValue("pv_codigo_seguro", paciente.getCodigoSeguro())
                .addValue("pv_tipo_seguro", paciente.getTipoSeguro())
                .addValue("pv_correo", paciente.getCorreo())
                .addValue("pv_direccion", paciente.getDireccion());

        Map<String, Object> result = simpleJdbcCall.execute(mapSqlParameterSource);
        if(result.containsKey("ERROR_CODE") || result.containsKey("ERROR_MESSAGE")){
            return -1;
        }
        else return 1;
    }

    public int eliminarPaciente(Paciente paciente){
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withSchemaName("dbSanama")
                .withProcedureName("ssm_adm_eliminar_paciente")
                .declareParameters(new SqlParameter[]{
                        new SqlParameter("pn_id_paciente ", Types.INTEGER)
                });
        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource
                .addValue("pn_id_paciente", paciente.getIdPersona());

        Map<String, Object> result = simpleJdbcCall.execute(mapSqlParameterSource);
        if(result.containsKey("ERROR_CODE") || result.containsKey("ERROR_MESSAGE")){
            return -1;
        }
        else return 1;
    }

}
