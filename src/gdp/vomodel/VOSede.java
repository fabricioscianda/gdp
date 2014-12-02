package gdp.vomodel;

import java.io.Serializable;
import java.util.List;

public class VOSede implements Serializable {

	private static final long serialVersionUID = -2077716851737179034L;
	private Long id;
	private String nombre;
	private VOLocalidad localidad;
	private List<VOCarrera> carreras;
	private VOInstituto instituto;

	public VOSede() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOSede) obj).getId().equals(this.getId())) {
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

	public VOLocalidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(VOLocalidad localidad) {
		this.localidad = localidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<VOCarrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<VOCarrera> carreras) {
		this.carreras = carreras;
	}

	public VOInstituto getInstituto() {
		return instituto;
	}

	public void setInstituto(VOInstituto instituto) {
		this.instituto = instituto;
	}

}