package gdp.vomodel;

import java.io.Serializable;
import java.util.List;

public class VOPersona implements Serializable {

	private static final long serialVersionUID = 7697274154353729907L;
	private Long id;
	private Long fechaNac;
	private VOInfoAdministrativa infoAdministrativa;
	private Integer numeroDoc;
	private String nombre;
	private String apellido;
	private VOTipoDocumento tipoDoc;
	private List<VOContacto> mediosContacto;
	private List<VODomicilio> domicilios;
	private List<VOEmpleo> empleos;
	private List<VOFormacionAcademica> formacionAcadem;

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

	public Integer getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(Integer numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public VOInfoAdministrativa getInfoAdministrativa() {
		return infoAdministrativa;
	}

	public void setInfoAdministrativa(VOInfoAdministrativa infoAdministrativa) {
		this.infoAdministrativa = infoAdministrativa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<VOContacto> getMediosContacto() {
		return mediosContacto;
	}

	public void setMediosContacto(List<VOContacto> mediosContacto) {
		this.mediosContacto = mediosContacto;
	}

	public List<VODomicilio> getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(List<VODomicilio> domicilios) {
		this.domicilios = domicilios;
	}

	public List<VOEmpleo> getEmpleos() {
		return empleos;
	}

	public void setEmpleos(List<VOEmpleo> empleos) {
		this.empleos = empleos;
	}

	public List<VOFormacionAcademica> getFormacionAcadem() {
		return formacionAcadem;
	}

	public void setFormacionAcadem(List<VOFormacionAcademica> formacionAcadem) {
		this.formacionAcadem = formacionAcadem;
	}

	public Long getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Long fechaNac) {
		this.fechaNac = fechaNac;
	}

}