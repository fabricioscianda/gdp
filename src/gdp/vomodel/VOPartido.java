package gdp.vomodel;

import java.io.Serializable;
import java.util.List;

public class VOPartido implements Serializable {

	private static final long serialVersionUID = 7741607975377349536L;
	private Long id;
	private String nombre;
	private VOProvincia provincia;
	private List<VOLocalidad> localidades;

	public VOPartido() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOPartido) obj).getId().equals(this.getId())) {
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

	public VOProvincia getProvincia() {
		return provincia;
	}

	public void setProvincia(VOProvincia provincia) {
		this.provincia = provincia;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<VOLocalidad> getLocalidades() {
		return localidades;
	}

	public void setLocalidades(List<VOLocalidad> localidades) {
		this.localidades = localidades;
	}

}