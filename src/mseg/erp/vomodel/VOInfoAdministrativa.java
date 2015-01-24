package mseg.erp.vomodel;

import java.io.Serializable;

public class VOInfoAdministrativa implements Serializable {

	private static final long serialVersionUID = -615303149933117311L;
	private Long id;
	private Long fechaAlta;
	private Long fechaMotivo;
	private String nroLegajo;
	private String nroExpe;
	private String nroResol;
	private VODocumentacion documentacion;
	private VOPersona persona;
	private VOTipoEstadoContractual tipoEstadoContractual;
	private VOTipoMotivo tipoMotivo;
	private VOTipoPersonal tipoPersonal;
	private VOTipoSituacionRevista tipoSituacionRevista;
	private VOTipoSituacion tipoSituacion;

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

	public VODocumentacion getDocumentacion() {
		return documentacion;
	}

	public void setDocumentacion(VODocumentacion documentacion) {
		this.documentacion = documentacion;
	}

	public VOTipoEstadoContractual getTipoEstadoContractual() {
		return tipoEstadoContractual;
	}

	public void setTipoEstadoContractual(VOTipoEstadoContractual tipoEstadoContractual) {
		this.tipoEstadoContractual = tipoEstadoContractual;
	}

	public VOTipoMotivo getTipoMotivo() {
		return tipoMotivo;
	}

	public void setTipoMotivo(VOTipoMotivo tipoMotivo) {
		this.tipoMotivo = tipoMotivo;
	}

	public VOTipoSituacionRevista getTipoSituacionRevista() {
		return tipoSituacionRevista;
	}

	public void setTipoSituacionRevista(VOTipoSituacionRevista tipoSituacionRevista) {
		this.tipoSituacionRevista = tipoSituacionRevista;
	}

	public VOTipoSituacion getTipoSituacion() {
		return tipoSituacion;
	}

	public void setTipoSituacion(VOTipoSituacion tipoSituacion) {
		this.tipoSituacion = tipoSituacion;
	}

	public String getNroLegajo() {
		return nroLegajo;
	}

	public void setNroLegajo(String nroLegajo) {
		this.nroLegajo = nroLegajo;
	}

}