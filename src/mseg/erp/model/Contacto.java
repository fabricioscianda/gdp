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
public class Contacto implements Serializable {

	private static final long serialVersionUID = -2002480838505136125L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_CONTACTO")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_CONTACTO", sequenceName = "CONTACTO_SEQ", allocationSize = 1)
	private Long id;
	private String valor;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoContacto tipoContacto;
	@ManyToOne(fetch = FetchType.LAZY)
	private Persona persona;

	// private Persona persona;

	public Contacto() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Contacto) obj).getId().equals(this.getId())) {
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

	public TipoContacto getTipoContacto() {
		return tipoContacto;
	}

	public void setTipoContacto(TipoContacto tipoContacto) {
		this.tipoContacto = tipoContacto;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

}