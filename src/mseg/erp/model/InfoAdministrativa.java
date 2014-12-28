package mseg.erp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
	private String legajo;
	private String nroExpe;
	private String nroResol;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "id_documentacion")
	private Documentacion documentacion;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Persona persona;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoEstadoContractual estadoContrac;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoMotivo motivo;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoPersonal tipoPersonal;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoSituacionRevista situRevista;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private TipoSituacion situActual;

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

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public TipoPersonal getTipoPersonal() {
		return tipoPersonal;
	}

	public void setTipoPersonal(TipoPersonal tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
	}

	public TipoSituacionRevista getSituRevista() {
		return situRevista;
	}

	public void setSituRevista(TipoSituacionRevista situRevista) {
		this.situRevista = situRevista;
	}

	public Long getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Long fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public String getLegajo() {
		return legajo;
	}

	public void setLegajo(String legajo) {
		this.legajo = legajo;
	}

	public TipoSituacion getSituActual() {
		return situActual;
	}

	public void setSituActual(TipoSituacion situActual) {
		this.situActual = situActual;
	}

	public TipoMotivo getMotivo() {
		return motivo;
	}

	public void setMotivo(TipoMotivo motivo) {
		this.motivo = motivo;
	}

	public Long getFechaMotivo() {
		return fechaMotivo;
	}

	public void setFechaMotivo(Long fechaMotivo) {
		this.fechaMotivo = fechaMotivo;
	}

	public TipoEstadoContractual getEstadoContrac() {
		return estadoContrac;
	}

	public void setEstadoContrac(TipoEstadoContractual estadoContrac) {
		this.estadoContrac = estadoContrac;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Documentacion getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(Documentacion documentacion) {
		this.documentacion = documentacion;
	}

}