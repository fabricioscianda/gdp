package gdp.model;

import gdp.utils.DBUtils;

import java.io.Serializable;
import java.util.List;

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
 * @created 26-nov-2014 07:09:50 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Sede implements Serializable {

	private static final long serialVersionUID = 6628426712920392734L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_SEDE")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_SEDE", sequenceName = "SEDE_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;
	@ManyToOne
	private Localidad localidad;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sede")
	private List<Carrera> carreras;
	@ManyToOne(fetch = FetchType.LAZY)
	private Instituto instituto;

	public Sede() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Sede) obj).getId().equals(this.getId())) {
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

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public Instituto getInstituto() {
		return instituto;
	}

	public void setInstituto(Instituto instituto) {
		this.instituto = instituto;
	}

}