package gdp.model;

import gdp.utils.DBUtils;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:51 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class TipoMotivo implements Serializable {

	private static final long serialVersionUID = 1180327123772011694L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_TIPOMOT")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_TIPOMOT", sequenceName = "TIPOMOT_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;

	public TipoMotivo() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((TipoMotivo) obj).getId().equals(this.getId())) {
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

}