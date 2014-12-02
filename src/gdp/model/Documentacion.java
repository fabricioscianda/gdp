package gdp.model;

import gdp.utils.DBUtils;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:49 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Documentacion implements Serializable {

	private static final long serialVersionUID = 3206099983013099135L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_DOCUM")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_DOCUM", sequenceName = "DOCUM_SEQ", allocationSize = 1)
	private Long id;
	private Boolean ddjj;
	private Boolean fotoDoc;
	private Boolean cuil;
	private Boolean cv;
	private Boolean titulos;
	private Boolean certifAntec;
	private Boolean certifReincid;

	public Documentacion() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Documentacion) obj).getId().equals(this.getId())) {
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

	public Boolean getDdjj() {
		return ddjj;
	}

	public void setDdjj(Boolean ddjj) {
		this.ddjj = ddjj;
	}

	public Boolean getFotoDoc() {
		return fotoDoc;
	}

	public void setFotoDoc(Boolean fotoDoc) {
		this.fotoDoc = fotoDoc;
	}

	public Boolean getCuil() {
		return cuil;
	}

	public void setCuil(Boolean cuil) {
		this.cuil = cuil;
	}

	public Boolean getCv() {
		return cv;
	}

	public void setCv(Boolean cv) {
		this.cv = cv;
	}

	public Boolean getTitulos() {
		return titulos;
	}

	public void setTitulos(Boolean titulos) {
		this.titulos = titulos;
	}

	public Boolean getCertifAntec() {
		return certifAntec;
	}

	public void setCertifAntec(Boolean certifAntec) {
		this.certifAntec = certifAntec;
	}

	public Boolean getCertifReincid() {
		return certifReincid;
	}

	public void setCertifReincid(Boolean certifReincid) {
		this.certifReincid = certifReincid;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}