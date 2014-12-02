package gdp.vomodel;

import java.io.Serializable;

public class VOEmpleo implements Serializable {

	private static final long serialVersionUID = -3446981137575129696L;
	private Long id;
	private String razonSocial;
	private String detalleCargo;
	private VOPersona persona;
	private VOTipoAdministracion tipoAdmin;
	private VOTipoCargo cargo;
	private VOTipoRelacion relacion;

	public VOEmpleo() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOEmpleo) obj).getId().equals(this.getId())) {
			return true;
		}
		return false;
	}

	public VOPersona getPersona() {
		return persona;
	}

	public void setPersona(VOPersona persona) {
		this.persona = persona;
	}

	public VOTipoRelacion getRelacion() {
		return relacion;
	}

	public void setRelacion(VOTipoRelacion relacion) {
		this.relacion = relacion;
	}

	public VOTipoAdministracion getTipoAdmin() {
		return tipoAdmin;
	}

	public void setTipoAdmin(VOTipoAdministracion tipoAdmin) {
		this.tipoAdmin = tipoAdmin;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public VOTipoCargo getCargo() {
		return cargo;
	}

	public void setCargo(VOTipoCargo cargo) {
		this.cargo = cargo;
	}

	public String getDetalleCargo() {
		return detalleCargo;
	}

	public void setDetalleCargo(String detalleCargo) {
		this.detalleCargo = detalleCargo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}