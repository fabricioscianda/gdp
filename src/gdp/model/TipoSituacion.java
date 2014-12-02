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
public class TipoSituacion implements Serializable {

	private static final long serialVersionUID = 311214567722630771L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_TIPOSITU")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_TIPOSITU", sequenceName = "TIPOSITU_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;

	public TipoSituacion() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((TipoSituacion) obj).getId().equals(this.getId())) {
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