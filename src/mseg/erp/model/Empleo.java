package mseg.erp.model;

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

import mseg.erp.utils.DBUtils;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:49 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Empleo implements Serializable {

	private static final long serialVersionUID = -4855565164142192941L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_EMPLEOS")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_EMPLEOS", sequenceName = "EMPLEOS_SEQ", allocationSize = 1)
	private Long id;
	private String razonSocial;
	private String detalleCargo;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Persona persona;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoAdministracion tipoAdmin;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoCargo tipoCargo;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoRelacion tipoRelacion;

	public Empleo() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Empleo) obj).getId().equals(this.getId())) {
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

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getDetalleCargo() {
		return detalleCargo;
	}

	public void setDetalleCargo(String detalleCargo) {
		this.detalleCargo = detalleCargo;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoAdministracion getTipoAdmin() {
		return tipoAdmin;
	}

	public void setTipoAdmin(TipoAdministracion tipoAdmin) {
		this.tipoAdmin = tipoAdmin;
	}

	public TipoCargo getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(TipoCargo tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public TipoRelacion getTipoRelacion() {
		return tipoRelacion;
	}

	public void setTipoRelacion(TipoRelacion tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

}