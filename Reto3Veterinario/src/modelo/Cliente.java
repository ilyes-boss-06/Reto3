package modelo;

public class Cliente extends Persona {
	private static final long serialVersionUID = 1L;
	
	private int idCliente;
	private int idPersona;
	private String tenefono;
	/**
	 * Constructor con todos los campos.
	 * @param idCliente identificador del cliente
	 * @param idPersona identificador de la persona asociada
	 * @param dni DNI del cliente
	 * @param nombre nombre completo del cliente
	 * @param telefono teléfono de contacto
	 */
	public Cliente(int idPersona, String dni, String nombre, int idCliente, int idPersona2, String tenefono) {
		super(idPersona, dni, nombre);
		this.idCliente = idCliente;
		idPersona = idPersona2;
		this.tenefono = tenefono;
	}
	/**
	 * Constructor sin ids (para INSERT).
	 * @param dni DNI del cliente
	 * @param nombre nombre completo del cliente
	 * @param telefono teléfono de contacto
	 */
	public Cliente(String dni, String nombre, int idPersona2, String tenefono) {
		super(dni, nombre);
		idPersona = idPersona2;
		this.tenefono = tenefono;
	}
	/**
	 * Constructor vacío.
	 */
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
