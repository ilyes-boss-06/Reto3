package modelo;

public class Cliente extends Persona {
	private static final long serialVersionUID = 1L;
	
	private int idCliente;
	private int idPersona;
	private String tenefono;
	public Cliente(int idPersona, String dni, String nombre, int idCliente, int idPersona2, String tenefono) {
		super(idPersona, dni, nombre);
		this.idCliente = idCliente;
		idPersona = idPersona2;
		this.tenefono = tenefono;
	}
	public Cliente(int idPersona, String dni, String nombre, int idPersona2, String tenefono) {
		super(idPersona, dni, nombre);
		idPersona = idPersona2;
		this.tenefono = tenefono;
	}
	public Cliente() {
		super();
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	public String getTenefono() {
		return tenefono;
	}
	public void setTenefono(String tenefono) {
		this.tenefono = tenefono;
	}
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", idPersona=" + idPersona + ", tenefono=" + tenefono
				+ ", toString()=" + super.toString() + "]";
	}
	
	
}
