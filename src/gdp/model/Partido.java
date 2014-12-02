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
 * @created 26-nov-2014 07:09:50 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Partido implements Serializable {

	private static final long serialVersionUID = 1447960091240758106L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_PART")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_PART", sequenceName = "PART_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;
	@ManyToOne
	private Provincia provincia;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "partido", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Localidad> localidades;

	public Partido() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Partido) obj).getId().equals(this.getId())) {
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

	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Localidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(List<Localidad> localidades) {
		this.localidades = localidades;
	}

}