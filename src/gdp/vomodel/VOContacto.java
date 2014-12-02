package gdp.vomodel;

import java.io.Serializable;

public class VOContacto implements Serializable {

	private static final long serialVersionUID = -8763597175884390837L;
	private Long id;
	private String valor;
	private VOPersona persona;
	private VOTipoContacto tipoContacto;

	public VOContacto() {}

	@Override
	public boolean equals(Object obj) {
		if (((VOContacto) obj).getId().equals(this.getId())) {
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

	public VOTipoContacto getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(VOTipoContacto tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public VOPersona getPersona() {
		return persona;
	}

	public void setPersona(VOPersona persona) {
		this.persona = persona;
	}

}