package gdp.model;

import gdp.utils.DBUtils;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
	private Long fechaNac;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	@JoinColumn(name = "id_infoadmin")
	private InfoAdministrativa infoAdministrativa;
	private Integer numeroDoc;
	private String nombre;
	private String apellido;
	@ManyToOne(fetch = FetchType.LAZY)
	private TipoDocumento tipoDoc;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private List<Contacto> mediosContacto;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private List<Domicilio> domicilios;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private List<Empleo> empleos;
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "persona")
	private List<FormacionAcademica> formacionAcadem;

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

	public Integer getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(Integer numeroDoc) {
		this.numeroDoc = numeroDoc;
	}

	public InfoAdministrativa getInfoAdministrativa() {
		return infoAdministrativa;
	}

	public void setInfoAdministrativa(InfoAdministrativa infoAdministrativa) {
		this.infoAdministrativa = infoAdministrativa;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Contacto> getMediosContacto() {
		return mediosContacto;
	}

	public void setMediosContacto(List<Contacto> mediosContacto) {
		this.mediosContacto = mediosContacto;
	}

	public List<Domicilio> getDomicilios() {
		return domicilios;
	}

	public void setDomicilios(List<Domicilio> domicilios) {
		this.domicilios = domicilios;
	}

	public List<Empleo> getEmpleos() {
		return empleos;
	}

	public void setEmpleos(List<Empleo> empleos) {
		this.empleos = empleos;
	}

	public List<FormacionAcademica> getFormacionAcadem() {
		return formacionAcadem;
	}

	public void setFormacionAcadem(List<FormacionAcademica> formacionAcadem) {
		this.formacionAcadem = formacionAcadem;
	}

	public Long getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Long fechaNac) {
		this.fechaNac = fechaNac;
	}

}