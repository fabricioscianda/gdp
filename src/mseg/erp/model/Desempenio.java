package mseg.erp.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mseg.erp.utils.DBUtils;

/**
 * @author fabricio
 * @version 1.0
 * @created 26-nov-2014 07:09:49 p.m.
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class Desempenio implements Serializable {

	private static final long serialVersionUID = -6175907375526677301L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_DESEMP")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_DESEMP", sequenceName = "DESEMP_SEQ", allocationSize = 1)
	private Long id;
	private Integer anio;
	private Integer mes;
	private Integer hcs;
	private Integer faltas;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Docente docente;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Asignatura asignatura;

	public Desempenio() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Desempenio) obj).getId().equals(this.getId())) {
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

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
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