package gdp.vomodel;

import java.io.Serializable;
import java.util.List;

public class VOInstituto implements Serializable {

	private static final long serialVersionUID = -1728835852174165167L;
	private Long id;
	private String nombre;
	private List<VOSede> sedes;

	public VOInstituto() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOInstituto) obj).getId().equals(this.getId())) {
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

	public List<VOSede> getSedes() {
		return sedes;
	}

	public void setSedes(List<VOSede> sedes) {
		this.sedes = sedes;
	}

}