package modelo;

public class Tratamiento {
	protected int idTratamiento;
	protected String nombre;
	protected double precio;
	
	public Tratamiento(int idTratamiento, String nombre, double precio) {
		super();
		this.idTratamiento = idTratamiento;
		this.nombre = nombre;
		this.precio = precio;
	}

	public Tratamiento(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}

	public Tratamiento() {
		super();
	}

	public int getIdTratamiento() {
		return idTratamiento;
	}

	public void setIdTratamiento(int idTratamiento) {
		this.idTratamiento = idTratamiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "Tratamiento [idTratamiento=" + idTratamiento + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
}
