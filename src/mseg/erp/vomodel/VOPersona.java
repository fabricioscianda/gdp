package mseg.erp.vomodel;

import java.io.Serializable;
import java.util.List;

public class VOPersona implements Serializable {

	private static final long serialVersionUID = 7697274154353729907L;
	private Long id;
	private Long fechaNac;
	private String numeroDoc;
	private String cuil;
	private String nombre;
	private String apellido;
	private VOTipoDocumento tipoDoc;
	private VOLocalidad localidad;
	private String cp;
	private List<VODomicilio> domicilios;
	private List<VOContacto> mediosContacto;
	private List<VOFormacionAcademica> formacionAcademica;
	private List<VOEmpleo> empleos;
	private VOInfoAdministrativa infoAdministrativa;

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

	public List<VODomicilio> getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(List<VODomicilio> domicilios) {
		this.domicilios = domicilios;
	}

	public List<VOContacto> getMediosContacto() {
		return mediosContacto;
	}

	public void setMediosContacto(List<VOContacto> mediosContacto) {
		this.mediosContacto = mediosContacto;
	}

	public List<VOFormacionAcademica> getFormacionAcademica() {
		return formacionAcademica;
	}

	public void setFormacionAcademica(List<VOFormacionAcademica> formacionAcademica) {
		this.formacionAcademica = formacionAcademica;
	}

	public List<VOEmpleo> getEmpleos() {
		return empleos;
	}

	public void setEmpleos(List<VOEmpleo> empleos) {
		this.empleos = empleos;
	}

	public VOLocalidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(VOLocalidad localidad) {
		this.localidad = localidad;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public VOInfoAdministrativa getInfoAdministrativa() {
		return infoAdministrativa;
	}

	public void setInfoAdministrativa(VOInfoAdministrativa infoAdministrativa) {
		this.infoAdministrativa = infoAdministrativa;
	}

	
}