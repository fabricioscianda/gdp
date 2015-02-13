package mseg.erp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import mseg.erp.dao.usuario.IUsuarioDAO;
import mseg.erp.exceptions.DAOException;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VOCredential;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOUsuario;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servicio para la gesti�n de usuarios
 * 
 */
@RestController
@RequestMapping(value = "/rest/usuarioService")
@Scope("request")
public class UsuarioService {

	@Inject
	private EntityManagerFactoryHolder emfh;
	@Inject
	private IUsuarioDAO usuarioDAO;
	@Inject
	private Gson gson;
	@Inject
	private VOCredential credentials;
	
	public UsuarioService() {
	}

	/**
	 * Recibe un string json con los datos del usuario Guarda el usuario
	 * recibido como parametro Retorna un VO informando si el usuario se pudo
	 * guardar en la base
	 * 
	 */
	@RequestMapping(value = "/usuario/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardarUsuario(@RequestBody String data, HttpServletRequest request) {
		EntityManager em = null;
		VOResponse responsevo = new VOResponse();
		
		if (!credentials.isEsAdmin()) {
			responsevo.setOk(false);
			responsevo.setErrorMessage("No tiene autorización para la acción seleccionada.");
			return gson.toJson(responsevo);
		}
		
		JsonObject obj = gson.fromJson(data, JsonObject.class);
		VOUsuario vousuario = gson.fromJson(obj.get("usuario"), VOUsuario.class),
				  usuario = null;
		vousuario.setUsername(vousuario.getUsername());
		vousuario.setApellido(vousuario.getApellido());;
		vousuario.setUsername(vousuario.getUsername());
		vousuario.setPassword(vousuario.getPassword());
		try {
			em = emfh.getEntityManager();
			emfh.beginTransaction(em);
			if (vousuario.getId() != null && vousuario.getId() != 0) {
				usuario = usuarioDAO.modificar(vousuario, em);
			} else {
				usuario = usuarioDAO.guardar(vousuario, em);
			}
			emfh.commitTransaction(em);
			responsevo.setOk(true);
			responsevo.setData(gson.toJson(usuario));
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			responsevo.setOk(false);
			responsevo.setErrorMessage("Error al intentar guardar el usuario");
		}
		return gson.toJson(responsevo);
	}

	/**
	 * Retorna un VO con el listado de todos los usuarios
	 * 
	 * **/
	@RequestMapping(value = "/usuario/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar() {
		VOResponse responsevo = new VOResponse();
		if (!credentials.isEsAdmin()) {
			responsevo.setOk(false);
			responsevo.setErrorMessage("No tiene autorización para la acción seleccionada.");
			return gson.toJson(responsevo);
		}
		EntityManager em = null;
		try {
			em = emfh.getEntityManager();
			List<VOUsuario> usuarios = usuarioDAO.listar(em);
			responsevo.setData(gson.toJson(usuarios));
			responsevo.setOk(true);
		} catch (DAOException e) {
			responsevo.setOk(false);
			responsevo.setErrorMessage("Error al intentar listar los usuarios");
		}

		return gson.toJson(responsevo);
	}

	/**
	 * Recibe un string json con un id de usuario Retorna un VO con el usuario
	 * correspondiente al id recibido como parametro
	 * 
	 */
	@RequestMapping(value = "/usuario/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String buscarUsuario(@RequestBody String data) {
		VOResponse responsevo = new VOResponse();
		
		if (!credentials.isEsAdmin()) {
			responsevo.setOk(false);
			responsevo.setErrorMessage("No tiene autorización para la acción seleccionada.");
			return gson.toJson(responsevo);
		}
		
		JsonObject obj = gson.fromJson(data, JsonObject.class);
		EntityManager em = null;
		Long idUsuario = obj.get("idUsuario").getAsLong();

		try {
			em = emfh.getEntityManager();
			VOUsuario vousuario = usuarioDAO.encontrar(idUsuario, em);
			responsevo.setOk(true);
			responsevo.setData(gson.toJson(vousuario));
		} catch (Exception e) {
			responsevo.setOk(false);
			responsevo.setErrorMessage("Error al intentar encontrar el usuario");
		}

		return gson.toJson(responsevo);
	}

	/**
	 * Recibe un string json con el id de un usuario Elimina el usuario
	 * correspondiente al id recibido como parametro Retorna un VO informando si
	 * el usuario se pudo eliminar
	 * 
	 * **/
	@RequestMapping(value = "/usuario/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String eliminar(@RequestBody String data) {
		VOResponse responsevo = new VOResponse();
		if (!credentials.isEsAdmin()) {
			responsevo.setOk(false);
			responsevo.setErrorMessage("No tiene autorización para la acción seleccionada.");
			return gson.toJson(responsevo);
		}
		JsonObject usuario = gson.fromJson(data, JsonObject.class);
		Long id = usuario.get("id").getAsLong();
		EntityManager em = null;
		try {
			em = emfh.getEntityManager();
			emfh.beginTransaction(em);
			usuarioDAO.borrar(id, em);
			emfh.commitTransaction(em);
			responsevo.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			responsevo.setOk(false);
			responsevo.setErrorMessage("Error al intentar eliminar un usuario");
		}
		return gson.toJson(responsevo);
	}
}