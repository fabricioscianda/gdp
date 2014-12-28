package mseg.erp.vomodel;

import java.io.Serializable;

public class VOResponse implements Serializable {

	private static final long serialVersionUID = 82488394837008553L;
	private boolean ok;
	private String errorMessage;
	private String data;
	private String errorCode;

	public VOResponse() {
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}