package mseg.erp.vomodel;

import java.io.Serializable;

public class VOEmpleo implements Serializable {

	private static final long serialVersionUID = -3446981137575129696L;
	private Long id;
	private String razonSocial;
	private String detalleCargo;
	private VOPersona persona;
	private VOTipoAdministracion tipoAdmin;
	private VOTipoCargo tipoCargo;
	private VOTipoRelacion tipoRelacion;

	public VOEmpleo() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOEmpleo) obj).getId().equals(this.getId())) {
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

	public VOPersona getPersona() {
		return persona;
	}

	public void setPersona(VOPersona persona) {
		this.persona = persona;
	}

	public VOTipoAdministracion getTipoAdmin() {
		return tipoAdmin;
	}

	public void setTipoAdmin(VOTipoAdministracion tipoAdmin) {
		this.tipoAdmin = tipoAdmin;
	}

	public VOTipoCargo getTipoCargo() {
		return tipoCargo;
	}

	public void setTipoCargo(VOTipoCargo tipoCargo) {
		this.tipoCargo = tipoCargo;
	}

	public VOTipoRelacion getTipoRelacion() {
		return tipoRelacion;
	}

	public void setTipoRelacion(VOTipoRelacion tipoRelacion) {
		this.tipoRelacion = tipoRelacion;
	}

}