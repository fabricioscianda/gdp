package mseg.erp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
public class TipoAdministracion implements Serializable {

	private static final long serialVersionUID = 6407593855242219286L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_TIPOADMIN")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_TIPOADMIN", sequenceName = "TIPOADMIN_SEQ", allocationSize = 1)
	private Long id;
	@Column(unique = true) 
	private String nombre;

	public TipoAdministracion() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((TipoAdministracion) obj).getId().equals(this.getId())) {
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