package gdp.model;

import gdp.utils.DBUtils;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:50 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class TipoContacto implements Serializable {

	private static final long serialVersionUID = -6983376664966429963L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_TIPOCONT")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_TIPOCONT", sequenceName = "TIPOCONT_SEQ", allocationSize = 1)
	private Long id;
	@Column(unique = true)
	private String nombre;

	public TipoContacto() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((TipoContacto) obj).getId().equals(this.getId())) {
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