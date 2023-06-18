package org.balanpy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/*
 * UsuarioImpl es un singleton para evitar tener que copiar el objeto cada vez que se cambia de pantalla, recargando los datos desde disco innecesariamente.
 *
 * TODO: Las funciones de validación deberían lanzar una excepción para permitir mejor manejo de error.
 */
public class UsuarioImpl implements Usuario {
	private static final Path USUARIO_PATH = Paths.get(Configuracion.getRootDir(), "usuario.json");
	private static final String MASCULINO = "masculino";
	private static final String FEMENINO = "femenino";
	private static final Pattern NIE_PATTERN = Pattern.compile("^\\p{Upper}\\d{7}\\p{Upper}$");
	private static final Pattern DNI_PATTERN = Pattern.compile("^\\d{8}\\p{Upper}$");
	private static final Pattern EMAIL_PATTERN = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");

	@JsonProperty(value = "DNI")
	private String dni = "";
	private Integer edad = 0;
	private String nombre = "";
	private String telefono = "";
	private String c_emerg_nombre = "";
	private String c_emerg_telefono = "";
	private String email_usuario = "";
	private String sexo = "";
	private String direccion = "";
	private String direccion_pais = "";
	private String direccion_provincia = "";
	private String direccion_localidad = "";
	private String direccion_cp= "";
	private Byte tipo_acceso = 0;
	@JsonProperty(value = "archivo_ppp")
	private String pathPPP = "";
	@JsonProperty(value = "archivo_seguro")
	private String pathSeguro = "";

	private static UsuarioImpl instance;
	private static ObjectMapper om = new ObjectMapper();
	
	//Generar un atributo connection
	private Connection con = null;
	

	private UsuarioImpl() throws SQLException {
		
		con = ConexionBD.getConnection();
	}
	

	public static UsuarioImpl getInstance() throws SQLException {
		
		/*if (instance == null) {
			try {
				//Archivo a bd
				//Lea de la bd
				instance = om.readValue(USUARIO_PATH.toFile(), UsuarioImpl.class);
			} catch (IOException e) {
				System.out.println("Failed to load user data. " + e.getMessage());
				instance = new UsuarioImpl();
			}
		}

		return instance;*/
		
		//****** BD ******
		//Se llama asi mismo 
		//Comprueba si el atributo es nulo.
		if(instance == null) {
			
			instance = new UsuarioImpl();
		}
		return instance;
		//****** BD ******	
	}
		

	@Override
	public String getDNI() {
		return dni;
	}

	@Override
	public Integer getEdad() {
		return edad;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public String getTelefono() {
		return telefono;
	}
	
	@Override
	public String getEmail_usuario() {
		return email_usuario;
	}

	@Override
	public String getSexo() {
		return sexo;
	}

	@Override
	public String getDireccion() {
		return direccion;
	}
	
	
	public String getC_emerg_nombre() {
		return c_emerg_nombre;
	}

	@Override
	public String getPathPPP() {
		return pathPPP;
	}

	@Override
	public String getPathSeguro() {
		return pathSeguro;
	}
	
	public String getC_emerg_telefono() {
		return c_emerg_telefono;
	}
	
	public String getDireccion_pais() {
		return direccion_pais;
	}


	public String getDireccion_provincia() {
		return direccion_provincia;
	}

	
	public String getDireccion_localidad() {
		return direccion_localidad;
	}


	public String getDireccion_cp() {
		return direccion_cp;
	}
	
	
	public Byte getTipo_acceso() {
		return tipo_acceso;
	}
	

	public void setC_emerg_nombre(String c_emerg_nombre) {
		this.c_emerg_nombre = c_emerg_nombre;
	}


	public void setC_emerg_telefono(String c_emerg_telefono) {
		this.c_emerg_telefono = c_emerg_telefono;
	}


	public void setDireccion_pais(String direccion_pais) {
		this.direccion_pais = direccion_pais;
	}


	public void setDireccion_provincia(String direccion_provincia) {
		this.direccion_provincia = direccion_provincia;
	}


	public void setDireccion_localidad(String direccion_localidad) {
		this.direccion_localidad = direccion_localidad;
	}


	public void setDireccion_cp(String direccion_cp) {
		this.direccion_cp = direccion_cp;
	}
	

	public void setTipo_acceso(Byte tipo_acceso) {
		this.tipo_acceso = tipo_acceso;
	}


	@Override
	public void setDNI(String dni) throws UsuarioException {
		if (!isValidDNI(dni)) {
			throw new UsuarioException("DNI invalido");
		}
		this.dni = dni;
	}

	@Override
	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	@Override
	public void setNombre(String nombre) throws UsuarioException {
		if (nombre.isEmpty()) {
			throw new UsuarioException("No se aceptan nombres vacios");
		}
		this.nombre = nombre;
	}

	@Override
	public void setTelefono(String telefono) {
		// TODO Validar formato telefono
		this.telefono = telefono;
	}

	@Override
	public void setEmail_usuario(String email_usuario) throws UsuarioException {
		if (!isValidEmail(email_usuario)) {
			throw new UsuarioException("email invalido");
		}
		this.email_usuario = email_usuario;
	}
	

	@Override
	public void setSexo(String sexo) throws UsuarioException {
		if (!isValidSexo(sexo)) {
			throw new UsuarioException("Sexo invalido");
		}
		this.sexo = sexo;
	}

	@Override
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	@Override
	public void setPathPPP(String pathPPP) {
		// TODO: Validar que el fichero existe
		this.pathPPP = pathPPP;
	}

	@Override
	public void setPathSeguro(String seguroPath) {
		// TODO: Validar que el fichero existe
		this.pathSeguro = seguroPath;
	}


	@Override
	public void save() throws StreamReadException, DatabindException, IOException {
		try {
			om.writeValue(USUARIO_PATH.toFile(), instance);
		} catch (IOException e) {
			// try to rollback to the saved values
			reload();
		}
	}
	
	public static void deleteUser() {
		Path usuarioPath = Paths.get(Configuracion.getRootDir(), "usuario.json");
		try {
			Files.delete(usuarioPath);
			instance = null;
		} catch (IOException e) {
			System.out.print("No se pudo borrar el archivo");
			e.printStackTrace();
		}
	}


	@Override
	public Usuario reload() throws StreamReadException, DatabindException, IOException {
		instance = om.readValue(USUARIO_PATH.toFile(), UsuarioImpl.class);
		return instance;
	}

	@Override
	@JsonIgnore
	public boolean isValid() {
		// Como el dni es nuestro indicador unico para cada usuario,
		// si este está vacío asumimos que el usuario no existe.
		return isValidDNI(dni) && !nombre.isEmpty() && isValidEmail(email_usuario) && isValidSexo(sexo);
	}

	public static boolean isValidSexo(String sexo) {
		String sexoCaseInsensitive = sexo.toLowerCase();

		return sexoCaseInsensitive.equals(MASCULINO) || sexoCaseInsensitive.equals(FEMENINO);
	}

	public static boolean isValidDNI(String dni) {
		return DNI_PATTERN.matcher(dni).matches() || NIE_PATTERN.matcher(dni).matches();
	}

	public static boolean isValidEmail(String email) {
		return EMAIL_PATTERN.matcher(email).matches();
	}
	
	// ************ BD ************ 
	public void insert() throws SQLException {
		DaoUsuario.getInstance().daoInsert(this);
	}
		
	public void update() throws SQLException {
		DaoUsuario.getInstance().daoUpdate(this);
	}
	
	public void delete() throws SQLException {
		DaoUsuario.getInstance().daoDelete(this);
	}
	// ************ BD ************ 

}
