package mseg.erp.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class Docente extends Persona {

	private static final long serialVersionUID = -7585540691720404843L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_DOCEN")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_DOCENT", sequenceName = "DOCEN_SEQ", allocationSize = 1)
	private Long id;
	@OneToMany(mappedBy = "docente", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
	private List<Desempenio> desempenios;

	public Docente() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((Docente) obj).getId().equals(this.getId())) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Desempenio> getDesempenios() {
		return desempenios;
	}

	public void setDesempenios(List<Desempenio> desempenios) {
		this.desempenios = desempenios;
	}

}