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
	private boolean logueable;
	@Column(unique = true)
	private String nombreUsuario;

	private String clave;
	@Column(unique = true)
	private String email;

	// @ManyToOne
	// private Rol rol;

	// @ManyToMany
	// @JoinTable(schema = DBUtils.SCHEMA, name = "USUARIO_OFICINA", joinColumns
	// = @JoinColumn(name = "USUARIO_ID"), inverseJoinColumns = @JoinColumn(name
	// = "OFICINA_ID"))
	// private List<Oficina> oficinas = new ArrayList<Oficina>();

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public Usuario() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isLogueable() {
		return logueable;
	}

	public void setLogueable(boolean logueable) {
		this.logueable = logueable;
	}

	@Override
	public boolean equals(Object obj) {
		if (((Usuario) obj).getId().equals(this.getId())) {
			return true;
		}
		return false;
	}

}
