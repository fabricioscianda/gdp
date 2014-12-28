package mseg.erp.vomodel;

import java.io.Serializable;

public class VOCredential implements Serializable {

	private static final long serialVersionUID = 1350025036866380740L;
	private String token;

	public VOCredential() {
	};

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
