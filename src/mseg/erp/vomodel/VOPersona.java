package mseg.erp.vomodel;

import java.io.Serializable;

public class VOPersona implements Serializable {

	private static final long serialVersionUID = 7697274154353729907L;
	private Long id;
	private Long fechaNac;
	private String numeroDoc;
	private String cuil;
	private String nombre;
	private String apellido;
	private VOTipoDocumento tipoDoc;
	
//	private VOInfoAdministrativa infoAdministrativa;
//	private List<VOContacto> mediosContacto;
//	private List<VODomicilio> domicilios;
//	private List<VOEmpleo> empleos;
//	private List<VOFormacionAcademica> formacionAcadem;

	public VOPersona() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOPersona) obj).getId().equals(this.getId())) {
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public VOTipoDocumento getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(VOTipoDocumento tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public Long getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Long fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

}