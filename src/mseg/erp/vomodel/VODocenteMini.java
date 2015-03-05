package mseg.erp.vomodel;

import java.io.Serializable;

public class VODocenteMini implements Serializable {

	private static final long serialVersionUID = 4653048028530407124L;

	private Long id;
	private String nombre;
	private String apellido;
	private Long fechaAlta;
	private String nroLegajo;
	private String estadoContractual;
	private String motivo;
	private String personal;
	private String situacionRevista;
	private String situacion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Long getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Long fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(String nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

	public String getEstadoContractual() {
		return estadoContractual;
	}

	public void setEstadoContractual(String estadoContractual) {
		this.estadoContractual = estadoContractual;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getPersonal() {
		return personal;
	}

	public void setPersonal(String personal) {
		this.personal = personal;
	}

	public String getSituacionRevista() {
		return situacionRevista;
	}

	public void setSituacionRevista(String situacionRevista) {
		this.situacionRevista = situacionRevista;
	}

	public String getSituacion() {
		return situacion;
	}

	public void setSituacion(String situacion) {
		this.situacion = situacion;
	}

}
