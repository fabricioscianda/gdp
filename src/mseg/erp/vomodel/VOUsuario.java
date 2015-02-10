package mseg.erp.vomodel;

import java.io.Serializable;

public class VOUsuario implements Serializable {

	private static final long serialVersionUID = -3614729275436341609L;
	private Long id;
	private String apellido;
	private String nombre;
	private String username;
	private String password;
	private boolean esAdmin;
	
	public VOUsuario() {
	}

	@Override
	public boolean equals(Object obj) {
		if (((VOUsuario) obj).getId().equals(this.getId())) {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

}
