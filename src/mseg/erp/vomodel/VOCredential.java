package mseg.erp.vomodel;

import java.io.Serializable;

public class VOCredential implements Serializable {

	private static final long serialVersionUID = 1350025036866380740L;
	private String token;
	private boolean esAdmin;
	private VOUsuario usuario;

	public VOCredential() {
	};

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public VOUsuario getUsuario() {
		return usuario;
	}

	public void setUsuario(VOUsuario usuario) {
		this.usuario = usuario;
	}

}
