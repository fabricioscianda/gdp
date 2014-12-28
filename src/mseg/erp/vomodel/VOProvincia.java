package mseg.erp.vomodel;

import java.io.Serializable;
import java.util.List;

public class VOProvincia implements Serializable {

	private static final long serialVersionUID = 7387951733219364267L;
	private Long id;
	private String nombre;
	private List<VOPartido> partidos;

	public VOProvincia() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOProvincia) obj).getId().equals(this.getId())) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<VOPartido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<VOPartido> partidos) {
		this.partidos = partidos;
	}

}