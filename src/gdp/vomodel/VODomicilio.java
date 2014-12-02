package gdp.vomodel;

import java.io.Serializable;

public class VODomicilio implements Serializable {

	private static final long serialVersionUID = -4059754941393402739L;
	private Long id;
	private String domicilio;
	private Boolean actual;
	private VOPersona persona;
	private VOLocalidad localidad;

	public VODomicilio() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VODomicilio) obj).getId().equals(this.getId())) {
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

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public VOLocalidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(VOLocalidad localidad) {
		this.localidad = localidad;
	}

	public Boolean getActual() {
		return actual;
	}

	public void setActual(Boolean actual) {
		this.actual = actual;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}