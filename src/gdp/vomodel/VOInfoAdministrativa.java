package gdp.vomodel;

import java.io.Serializable;

public class VOInfoAdministrativa implements Serializable {

	private static final long serialVersionUID = -615303149933117311L;
	private Long id;
	private Long fechaAlta;
	private Long fechaMotivo;
	private String legajo;
	private String nroExpe;
	private String nroResol;
	private VODocumentacion documentacion;
	private VOPersona persona;
	private VOTipoEstadoContractual estadoContrac;
	private VOTipoMotivo motivo;
	private VOTipoPersonal tipoPersonal;
	private VOTipoSituacionRevista situRevista;
	private VOTipoSituacion situActual;

	public VOInfoAdministrativa() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOInfoAdministrativa) obj).getId().equals(this.getId())) {
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

	public VOPersona getPersona() {
		return persona;
	}

	public void setPersona(VOPersona persona) {
		this.persona = persona;
	}

	public VOTipoPersonal getTipoPersonal() {
		return tipoPersonal;
	}

	public void setTipoPersonal(VOTipoPersonal tipoPersonal) {
		this.tipoPersonal = tipoPersonal;
	}

	public VOTipoSituacionRevista getSituRevista() {
		return situRevista;
	}

	public void setSituRevista(VOTipoSituacionRevista situRevista) {
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

	public VOTipoSituacion getSituActual() {
		return situActual;
	}

	public void setSituActual(VOTipoSituacion situActual) {
		this.situActual = situActual;
	}

	public VOTipoMotivo getMotivo() {
		return motivo;
	}

	public void setMotivo(VOTipoMotivo motivo) {
		this.motivo = motivo;
	}

	public Long getFechaMotivo() {
		return fechaMotivo;
	}

	public void setFechaMotivo(Long fechaMotivo) {
		this.fechaMotivo = fechaMotivo;
	}

	public VOTipoEstadoContractual getEstadoContrac() {
		return estadoContrac;
	}

	public void setEstadoContrac(VOTipoEstadoContractual estadoContrac) {
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

	public VODocumentacion getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(VODocumentacion documentacion) {
		this.documentacion = documentacion;
	}

}