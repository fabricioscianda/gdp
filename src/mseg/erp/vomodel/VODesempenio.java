package mseg.erp.vomodel;

import java.io.Serializable;

public class VODesempenio implements Serializable {

	private static final long serialVersionUID = -8768895317292284647L;
	private Long id;
	private Integer anio;
	private Integer mes;
	private Integer hcs;
	private Integer faltas;
	private VODocente docente;
	private VOAsignatura asignatura;

	public VODesempenio() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VODesempenio) obj).getId().equals(this.getId())) {
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

	public VODocente getDocente() {
		return docente;
	}

	public void setDocente(VODocente docente) {
		this.docente = docente;
	}

	public VOAsignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(VOAsignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getHcs() {
		return hcs;
	}

	public void setHcs(Integer hcs) {
		this.hcs = hcs;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getFaltas() {
		return faltas;
	}

	public void setFaltas(Integer faltas) {
		this.faltas = faltas;
	}

}