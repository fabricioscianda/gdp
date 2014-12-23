package gdp.vomodel;

import java.io.Serializable;
import java.util.List;

public class VOCarrera implements Serializable {

	private static final long serialVersionUID = 8878813645484021339L;
	private Long id;
	private String nombre;
	private Integer cantAnios;
	private VOSede sede;
	private List<VOAsignatura> asignaturas;

	public VOCarrera() {}

	@Override
	public boolean equals(Object obj) {
		if (((VOCarrera) obj).getId().equals(this.getId())) {
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

	public List<VOAsignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<VOAsignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public VOSede getSede() {
		return sede;
	}

	public void setSede(VOSede sede) {
		this.sede = sede;
	}

	public Integer getCantAnios() {
		return cantAnios;
	}

	public void setCantAnios(Integer cantAnios) {
		this.cantAnios = cantAnios;
	}

}