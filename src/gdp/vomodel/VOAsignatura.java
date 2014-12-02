package gdp.vomodel;

import java.io.Serializable;

public class VOAsignatura implements Serializable {

	private static final long serialVersionUID = 78794106119496009L;
	
	private Long id;
	private String nombre;
	private Integer anio;
	private VOCarrera carrera;

	@Override
	public boolean equals(Object obj) {
		if (((VOAsignatura) obj).getId().equals(this.getId())) {
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

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public VOCarrera getCarrera() {
		return carrera;
	}

	public void setCarrera(VOCarrera carrera) {
		this.carrera = carrera;
	}

}