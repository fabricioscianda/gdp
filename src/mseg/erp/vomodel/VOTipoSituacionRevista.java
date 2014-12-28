package mseg.erp.vomodel;

import java.io.Serializable;

public class VOTipoSituacionRevista implements Serializable {

	private static final long serialVersionUID = 8468205676740252353L;
	private Long id;
	private String nombre;

	public VOTipoSituacionRevista() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOTipoSituacionRevista) obj).getId().equals(this.getId())) {
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