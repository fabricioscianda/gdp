package gdp.vomodel;

import java.io.Serializable;

public class VOTipoEstadoContractual implements Serializable {

	private static final long serialVersionUID = -7758629005542086934L;
	private Long id;
	private String nombre;

	public VOTipoEstadoContractual() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOTipoEstadoContractual) obj).getId().equals(this.getId())) {
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