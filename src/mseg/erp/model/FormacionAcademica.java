package mseg.erp.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mseg.erp.utils.DBUtils;

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
	private String titulo;
	private String expedidoPor;
	private Long anio;
	@ManyToOne(fetch = FetchType.LAZY)
	private Persona persona;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoFormacion tipoFormacion;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoEstadoFormacion tipoEstadoFormacion;

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

	public TipoFormacion getTipoFormacion() {
		return tipoFormacion;
	}

	public void setTipoFormacion(TipoFormacion tipoFormacion) {
		this.tipoFormacion = tipoFormacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getExpedidoPor() {
		return expedidoPor;
	}

	public void setExpedidoPor(String expedidoPor) {
		this.expedidoPor = expedidoPor;
	}

	public Long getAnio() {
		return anio;
	}

	public void setAnio(Long anio) {
		this.anio = anio;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoEstadoFormacion getTipoEstadoFormacion() {
		return tipoEstadoFormacion;
	}

	public void setTipoEstadoFormacion(TipoEstadoFormacion tipoEstadoFormacion) {
		this.tipoEstadoFormacion = tipoEstadoFormacion;
	}

}