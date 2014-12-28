package mseg.erp.vomodel;

import java.io.Serializable;

public class VOTipoMotivo implements Serializable {

	private static final long serialVersionUID = 4826126279372088531L;
	private Long id;
	private String nombre;

	public VOTipoMotivo() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOTipoMotivo) obj).getId().equals(this.getId())) {
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