package gdp.vomodel;

import java.io.Serializable;

public class VOUsuario implements Serializable {

	private static final long serialVersionUID = -3614729275436341609L;
	private Long id;
	private String apellido;
	private String nombre;
	private String nombreUsuario;
	private String clave;

	private boolean logueable;

	public VOUsuario() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean isLogueable() {
		return logueable;
	}

	public void setLogueable(boolean logueable) {
		this.logueable = logueable;
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOUsuario) obj).getId().equals(this.getId())) {
			return true;
		}
		return false;
	}

}
