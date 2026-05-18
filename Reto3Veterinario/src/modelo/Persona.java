package modelo;

public class Persona {

	private int idPersona;
	private String dni;
	private String nombre;
	/**
	 * Constructor con todos los campos.
	 * @param idPersona identificador de la persona
	 * @param dni DNI de la persona
	 * @param nombre nombre completo de la persona
	 */
	public Persona(int idPersona, String dni, String nombre) {
		super();
		this.idPersona = idPersona;
		this.dni = dni;
		this.nombre = nombre;
	}
	/**
	 * Constructor sin id (para INSERT).
	 * @param dni DNI de la persona
	 * @param nombre nombre completo de la persona
	 */
	public Persona(String dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
	}
	/**
	 * Constructor vacío.
	 */
	public Persona() {
		super();
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", dni=" + dni + ", nombre=" + nombre + "]";
	}
	
	
}
