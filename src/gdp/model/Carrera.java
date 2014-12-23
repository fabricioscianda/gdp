package gdp.model;

import gdp.utils.DBUtils;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:49 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Carrera implements Serializable {

	private static final long serialVersionUID = 6029176636025504545L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CARRERA")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_CARRERA", sequenceName = "CARRERA_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;
	private Integer cantAnios;
	@ManyToOne
	private Sede sede;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "carrera", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Asignatura> asignaturas;
	
	public Carrera() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Carrera) obj).getId().equals(this.getId())) {
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

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Integer getCantAnios() {
		return cantAnios;
	}

	public void setCantAnios(Integer cantAnios) {
		this.cantAnios = cantAnios;
	}

}