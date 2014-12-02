package gdp.vomodel;

import java.io.Serializable;

public class VOTipoCargo implements Serializable {

	private static final long serialVersionUID = -610782498038997641L;
	private Long id;
	private String nombre;

	public VOTipoCargo() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOTipoCargo) obj).getId().equals(this.getId())) {
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