package gdp.model;

import gdp.utils.DBUtils;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:49 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class FormacionAcademica implements Serializable {

	private static final long serialVersionUID = 9143655643310079155L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_FORMACA")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_FORMACA", sequenceName = "FORMACA_SEQ", allocationSize = 1)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Persona persona;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoFormacion tipoFormacion;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoEstadoFormacion estado;

	public FormacionAcademica() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((FormacionAcademica) obj).getId().equals(this.getId())) {
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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoFormacion getTipoFormacion() {
		return tipoFormacion;
	}

	public void setTipoFormacion(TipoFormacion tipoFormacion) {
		this.tipoFormacion = tipoFormacion;
	}

	public TipoEstadoFormacion getEstado() {
		return estado;
	}

	public void setEstado(TipoEstadoFormacion estado) {
		this.estado = estado;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}