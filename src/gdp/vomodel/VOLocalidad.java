package gdp.vomodel;

import java.io.Serializable;

public class VOLocalidad implements Serializable {

	private static final long serialVersionUID = -5888675574810920072L;
	private Long id;
	private String nombre;
	private String cp;
	private VOPartido partido;

	public VOLocalidad() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOLocalidad) obj).getId().equals(this.getId())) {
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public VOPartido getPartido() {
		return partido;
	}

	public void setPartido(VOPartido partido) {
		this.partido = partido;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}