package gdp.vomodel;

import java.io.Serializable;

public class VODocumentacion implements Serializable {

	private static final long serialVersionUID = -1666457327780688619L;
	private Long id;
	private Boolean ddjj;
	private Boolean fotoDoc;
	private Boolean cuil;
	private Boolean cv;
	private Boolean titulos;
	private Boolean certifAntec;
	private Boolean certifReincid;

	public VODocumentacion() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VODocumentacion) obj).getId().equals(this.getId())) {
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