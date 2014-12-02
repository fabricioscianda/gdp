package gdp.model;

import gdp.utils.DBUtils;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class Instituto implements Serializable {

	private static final long serialVersionUID = 1883829818595215084L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_INST")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_INST", sequenceName = "INST_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "instituto")
	private List<Sede> sedes;

	public Instituto() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Instituto) obj).getId().equals(this.getId())) {
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

	public List<Sede> getSedes() {
		return sedes;
	}

	public void setSedes(List<Sede> sedes) {
		this.sedes = sedes;
	}

}