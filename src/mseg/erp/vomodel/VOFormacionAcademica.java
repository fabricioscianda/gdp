package mseg.erp.vomodel;

import java.io.Serializable;

public class VOFormacionAcademica implements Serializable {

	private static final long serialVersionUID = -1306676621245711521L;
	private Long id;
	private VOPersona persona;
	private VOTipoFormacion tipoFormacion;
	private VOTipoEstadoFormacion estado;

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

	public VOTipoEstadoFormacion getEstado() {
		return estado;
	}

	public void setEstado(VOTipoEstadoFormacion estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}