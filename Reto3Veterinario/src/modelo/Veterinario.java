package modelo;

public class Veterinario extends Persona {

	private int idVeterinario;
	private int idPersona;
	private String numColegiado;
	public Veterinario(int idPersona, String dni, String nombre, int idVeterinario, int idPersona2,
			String numColegiado) {
		super(idPersona, dni, nombre);
		this.idVeterinario = idVeterinario;
		idPersona = idPersona2;
		this.numColegiado = numColegiado;
	}
	public Veterinario(int idPersona, String dni, String nombre, int idPersona2, String numColegiado) {
		super(idPersona, dni, nombre);
		idPersona = idPersona2;
		this.numColegiado = numColegiado;
	}
	public Veterinario() {
		super();
	}
	public int getIdVeterinario() {
		return idVeterinario;
	}
	public void setIdVeterinario(int idVeterinario) {
		this.idVeterinario = idVeterinario;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getNumColegiado() {
		return numColegiado;
	}
	public void setNumColegiado(String numColegiado) {
		this.numColegiado = numColegiado;
	}
	@Override
	public String toString() {
		return "Veterinario [idVeterinario=" + idVeterinario + ", idPersona=" + idPersona + ", numColegiado="
				+ numColegiado + ", toString()=" + super.toString() + "]";
	}
	
	
}
