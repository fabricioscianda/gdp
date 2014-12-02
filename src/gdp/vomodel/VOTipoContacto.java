package gdp.vomodel;

import java.io.Serializable;

public class VOTipoContacto implements Serializable {

	private static final long serialVersionUID = -8493349870732626813L;
	private Long id;
	private String nombre;

	public VOTipoContacto() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOTipoContacto) obj).getId().equals(this.getId())) {
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