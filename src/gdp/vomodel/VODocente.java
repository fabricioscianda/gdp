package gdp.vomodel;

import java.util.List;

public class VODocente extends VOPersona {

	private static final long serialVersionUID = -7074989519547946920L;
	private Long id;
	private List<VODesempenio> desempenios;

	public VODocente() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VODocente) obj).getId().equals(this.getId())) {
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

	public List<VODesempenio> getDesempenios() {
		return desempenios;
	}

	public void setDesempenios(List<VODesempenio> desempenios) {
		this.desempenios = desempenios;
	}

}