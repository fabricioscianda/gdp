package mseg.erp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mseg.erp.utils.DBUtils;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:49 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Asignatura implements Serializable {

	private static final long serialVersionUID = -6663352899269676900L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_ASIGNATURAS")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_ASIGNATURAS", sequenceName = "ASIGNATURA_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;
	private Integer anio;
	private boolean extra;
	@ManyToOne
	private Carrera carrera;
	
	public Asignatura() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Asignatura) obj).getId().equals(this.getId())) {
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

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public boolean isExtra() {
		return extra;
	}

	public void setExtra(boolean extra) {
		this.extra = extra;
	}

}