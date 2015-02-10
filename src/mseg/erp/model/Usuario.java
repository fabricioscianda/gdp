package mseg.erp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import mseg.erp.utils.DBUtils;

@Entity
@Table(schema = DBUtils.SCHEMA)
public class Usuario implements Serializable {

	private static final long serialVersionUID = -3282769552834421454L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_USUARIO")
	@SequenceGenerator(schema = DBUtils.SCHEMA, name = "SEQUENCE_USUARIO", sequenceName = "USUARIO_SEQ", allocationSize = 1)
	private Long id;
	private String apellido;
	private String nombre;
	@Column(unique = true)
	private String username;
	private String password;
	private boolean esAdmin;

	@Override
	public boolean equals(Object obj) {
		if (((Usuario) obj).getId().equals(this.getId())) {
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