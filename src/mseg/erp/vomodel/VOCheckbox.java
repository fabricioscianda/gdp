package mseg.erp.vomodel;

import java.io.Serializable;

public class VOCheckbox implements Serializable {

	private static final long serialVersionUID = 227681144443127281L;

	private Long id;
	private Boolean checked;

	public VOCheckbox() {
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getChecked() {
		return checked;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	
}
