package modelo;

public class Persona {

	private int idPersona;
	private String dni;
	private String nombre;
	public Persona(int idPersona, String dni, String nombre) {
		super();
		this.idPersona = idPersona;
		this.dni = dni;
		this.nombre = nombre;
	}
	public Persona(String dni, String nombre) {
		super();
		this.dni = dni;
		this.nombre = nombre;
	}
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
