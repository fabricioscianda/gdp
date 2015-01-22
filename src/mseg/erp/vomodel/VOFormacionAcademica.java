package mseg.erp.vomodel;

import java.io.Serializable;

public class VOFormacionAcademica implements Serializable {

	private static final long serialVersionUID = -1306676621245711521L;
	private Long id;
	private String titulo;
	private String expedidoPor;
	private Long anio;
	private VOPersona persona;
	private VOTipoFormacion tipoFormacion;
	private VOTipoEstadoFormacion tipoEstadoFormacion;

	public VOFormacionAcademica() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOFormacionAcademica) obj).getId().equals(this.getId())) {
			return true;
		}
		return false;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VOPersona getPersona() {
		return persona;
	}

	public void setPersona(VOPersona persona) {
		this.persona = persona;
	}

	public VOTipoFormacion getTipoFormacion() {
		return tipoFormacion;
	}

	public void setTipoFormacion(VOTipoFormacion tipoFormacion) {
		this.tipoFormacion = tipoFormacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getExpedidoPor() {
		return expedidoPor;
	}

	public void setExpedidoPor(String expedidoPor) {
		this.expedidoPor = expedidoPor;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public VOTipoEstadoFormacion getTipoEstadoFormacion() {
		return tipoEstadoFormacion;
	}

	public void setTipoEstadoFormacion(VOTipoEstadoFormacion tipoEstadoFormacion) {
		this.tipoEstadoFormacion = tipoEstadoFormacion;
	}

}