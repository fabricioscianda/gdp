package gdp.vomodel;

import java.io.Serializable;

public class VOTipoSituacion implements Serializable {

	private static final long serialVersionUID = -6850614565246631133L;
	private Long id;
	private String nombre;

	public VOTipoSituacion() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOTipoSituacion) obj).getId().equals(this.getId())) {
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