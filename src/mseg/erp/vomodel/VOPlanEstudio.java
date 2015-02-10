package mseg.erp.vomodel;

import java.io.Serializable;
import java.util.List;

public class VOPlanEstudio implements Serializable {

	private static final long serialVersionUID = 8878813645484021339L;
	private Long id;
	private String nombre;
	private Integer anio;
	private List<VOAsignatura> asignaturas;

	public VOPlanEstudio() {}

	@Override
	public boolean equals(Object obj) {
		if (((VOPlanEstudio) obj).getId().equals(this.getId())) {
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

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

}