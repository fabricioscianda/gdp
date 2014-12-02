package gdp.model;

import gdp.utils.DBUtils;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:50 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Localidad implements Serializable {

	private static final long serialVersionUID = -8150699406625517508L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_LOCAL")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_LOCAL", sequenceName = "LOCAL_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;
	private String cp;
	@ManyToOne
	private Partido partido;

	public Localidad() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Localidad) obj).getId().equals(this.getId())) {
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

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}