package mseg.erp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mseg.erp.utils.DBUtils;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:50 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Persona implements Serializable {

	private static final long serialVersionUID = 1575687743792235547L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_PERS")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_PERS", sequenceName = "PERS_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;
	private String apellido;
	private Long fechaNac;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoDocumento tipoDoc;
	@Column(unique = true, nullable = false)
	private String numeroDoc;
	@Column(unique = true, nullable = true)
	private String cuil;
	@ManyToOne(fetch = FetchType.LAZY)
	private Localidad localidad;
	private String cp;
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "persona")
	private List<Domicilio> domicilios;
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "persona")
	private List<Contacto> mediosContacto;
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "persona")
	private List<FormacionAcademica> formacionAcademica;
	@OneToMany(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "persona")
	private List<Empleo> empleos;
	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true, mappedBy = "persona", cascade = CascadeType.ALL)
	private InfoAdministrativa infoAdministrativa;

	public Persona() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Persona) obj).getId().equals(this.getId())) {
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

	public TipoDocumento getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(TipoDocumento tipoDoc) {
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

	public List<Domicilio> getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(List<Domicilio> domicilios) {
		this.domicilios = domicilios;
	}

	public List<Contacto> getMediosContacto() {
		return mediosContacto;
	}

	public void setMediosContacto(List<Contacto> mediosContacto) {
		this.mediosContacto = mediosContacto;
	}

	public List<FormacionAcademica> getFormacionAcademica() {
		return formacionAcademica;
	}

	public void setFormacionAcademica(List<FormacionAcademica> formacionAcademica) {
		this.formacionAcademica = formacionAcademica;
	}

	public List<Empleo> getEmpleos() {
		return empleos;
	}

	public void setEmpleos(List<Empleo> empleos) {
		this.empleos = empleos;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public InfoAdministrativa getInfoAdministrativa() {
		return infoAdministrativa;
	}

	public void setInfoAdministrativa(InfoAdministrativa infoAdministrativa) {
		this.infoAdministrativa = infoAdministrativa;
	}

}