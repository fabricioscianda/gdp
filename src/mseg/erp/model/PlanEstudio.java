package mseg.erp.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mseg.erp.utils.DBUtils;

/**
 * @author fabricio
 * @version 1.0
 * 
 */
@Entity
@Table(schema = DBUtils.SCHEMA)
public class PlanEstudio implements Serializable {

	private static final long serialVersionUID = 6029176636025504545L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_PLANESTUDIO")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_PLANESTUDIO", sequenceName = "PLANESTUDIO_SEQ", allocationSize = 1)
	private Long id;
	private String nombre;
	private Integer anio;
	@ManyToMany(targetEntity = Asignatura.class, fetch = FetchType.LAZY)
	@JoinTable(name = "plan_asignatura", schema = DBUtils.SCHEMA, 
			joinColumns = { @JoinColumn(name = "id_plan", referencedColumnName = "id") }, 
			inverseJoinColumns = { @JoinColumn(name = "id_asignatura", referencedColumnName = "id") })
	private List<Asignatura> asignaturas;
	
	public PlanEstudio() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((PlanEstudio) obj).getId().equals(this.getId())) {
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

	public List<Asignatura> getAsignaturas() {
		return asignaturas;
	}

	public void setAsignaturas(List<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

}