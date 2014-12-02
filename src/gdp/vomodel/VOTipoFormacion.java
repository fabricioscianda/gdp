package gdp.vomodel;

import java.io.Serializable;

public class VOTipoFormacion implements Serializable {

	private static final long serialVersionUID = -5548551529964970595L;
	private Long id;
	private String nombre;

	public VOTipoFormacion() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOTipoFormacion) obj).getId().equals(this.getId())) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}