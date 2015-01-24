package mseg.erp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
public class InfoAdministrativa implements Serializable {

	private static final long serialVersionUID = 2633356972933105245L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_INFOADMIN")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_INFOADMIN", sequenceName = "INFOADMIN_SEQ", allocationSize = 1)
	private Long id;
	private Long fechaAlta;
	private Long fechaMotivo;
	@Column(unique = true, nullable = true)
	private String nroLegajo;
	private String nroExpe;
	private String nroResol;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	// @JoinColumn(name = "id_documentacion")
	private Documentacion documentacion;
	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
	private Persona persona;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoEstadoContractual tipoEstadoContractual;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoMotivo tipoMotivo;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoPersonal tipoPersonal;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoSituacionRevista tipoSituacionRevista;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoSituacion tipoSituacion;

	public InfoAdministrativa() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((InfoAdministrativa) obj).getId().equals(this.getId())) {
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

	public TipoPersonal getTipoPersonal() {
		return tipoPersonal;
	}

	public void setTipoPersonal(TipoPersonal tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
	}

	public Long getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Long fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Long getFechaMotivo() {
		return fechaMotivo;
	}

	public void setFechaMotivo(Long fechaMotivo) {
		this.fechaMotivo = fechaMotivo;
	}

	public String getNroExpe() {
		return nroExpe;
	}

	public void setNroExpe(String nroExpe) {
		this.nroExpe = nroExpe;
	}

	public String getNroResol() {
		return nroResol;
	}

	public void setNroResol(String nroResol) {
		this.nroResol = nroResol;
	}

	public Documentacion getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(Documentacion documentacion) {
		this.documentacion = documentacion;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoEstadoContractual getTipoEstadoContractual() {
		return tipoEstadoContractual;
	}

	public void setTipoEstadoContractual(TipoEstadoContractual tipoEstadoContractual) {
		this.tipoEstadoContractual = tipoEstadoContractual;
	}

	public TipoMotivo getTipoMotivo() {
		return tipoMotivo;
	}

	public void setTipoMotivo(TipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}

	public TipoSituacionRevista getTipoSituacionRevista() {
		return tipoSituacionRevista;
	}

	public void setTipoSituacionRevista(TipoSituacionRevista tipoSituacionRevista) {
		this.tipoSituacionRevista = tipoSituacionRevista;
	}

	public TipoSituacion getTipoSituacion() {
		return tipoSituacion;
	}

	public void setTipoSituacion(TipoSituacion tipoSituacion) {
		this.tipoSituacion = tipoSituacion;
	}

	public String getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(String nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

}