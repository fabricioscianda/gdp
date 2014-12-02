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
public class Domicilio implements Serializable {

	private static final long serialVersionUID = 1702936451735439254L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_DOMIC")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_DOMIC", sequenceName = "DOMIC_SEQ", allocationSize = 1)
	private Long id;
	private String domicilio;
	private Boolean actual;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Persona persona;
	@ManyToOne(fetch = FetchType.LAZY)
	private Localidad localidad;

	public Domicilio() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Domicilio) obj).getId().equals(this.getId())) {
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

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public Boolean getActual() {
		return actual;
	}

	public void setActual(Boolean actual) {
		this.actual = actual;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}