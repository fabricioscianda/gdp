package mseg.erp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mseg.erp.utils.DBUtils;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:50 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Provincia implements Serializable {

	private static final long serialVersionUID = 3354224017504749367L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_PROV")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_PROV", sequenceName = "PROV_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "provincia", orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Partido> partidos;

	public Provincia() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Provincia) obj).getId().equals(this.getId())) {
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

	public List<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<Partido> partidos) {
		this.partidos = partidos;
	}

}