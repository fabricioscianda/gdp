package mseg.erp.rest;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Servicio para la gesti�n de usuarios
 * 
 */
@RestController
@RequestMapping(value = "/rest/usuarioService")
@Scope("request")
public class UsuarioService {

//	@Inject
//	private IUsuarioDAO usuarioDAO;
//
//	@Inject
//	private VOCredential credentials;
//
//	@Inject
//	private Gson gson;
//
//	public UsuarioService() {
//	}
//
//	/**
//	 * Recibe un string json con los datos del usuario Guarda el usuario
//	 * recibido como parametro Retorna un VO informando si el usuario se pudo
//	 * guardar en la base
//	 * 
//	 */
//	@RequestMapping(value = "/usuario/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String guardarUsuario(@RequestBody String data) {
//		VOResponse responsevo = new VOResponse();
//
//		JsonObject obj = gson.fromJson(data, JsonObject.class);
//
//		VOUsuario vousuario = gson.fromJson(obj.get("usuario"), VOUsuario.class);
//		vousuario.setUsername(vousuario.getUsername());
//		try {
//			usuarioDAO.crearUsuario(vousuario);
//			responsevo.setOk(true);
//		} catch (Exception e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error al intentar guardar el usuario");
//		}
//		return gson.toJson(responsevo);
//	}
//
//	/**
//	 * Retorna un VO con el listado de todos los usuarios
//	 * 
//	 * **/
//	@RequestMapping(value = "/usuarios", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String listarUsuarios() {
//		VOResponse responsevo = new VOResponse();
//
//		try {
//			List<VOUsuario> usuarios = usuarioDAO.listar();
//			responsevo.setData(gson.toJson(usuarios));
//			responsevo.setOk(true);
//		} catch (DAOException e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error al intentar listar los usuarios");
//		}
//
//		return gson.toJson(responsevo);
//	}
//
//	/**
//	 * Recibe un string json con un id de usuario Retorna un VO con el usuario
//	 * correspondiente al id recibido como parametro
//	 * 
//	 */
//	@RequestMapping(value = "/usuario/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String buscarUsuario(@RequestBody String data) {
//		VOResponse responsevo = new VOResponse();
//		JsonObject obj = gson.fromJson(data, JsonObject.class);
//
//		Long idUsuario = obj.get("idUsuario").getAsLong();
//
//		try {
//			VOUsuario vousuario = usuarioDAO.encontrar(idUsuario);
//			responsevo.setOk(true);
//			responsevo.setData(gson.toJson(vousuario));
//		} catch (Exception e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error al intentar encontrar el usuario");
//		}
//
//		return gson.toJson(responsevo);
//	}
//
//	/**
//	 * Recibe un string json con el id de un usuario Elimina el usuario
//	 * correspondiente al id recibido como parametro Retorna un VO informando si
//	 * el usuario se pudo eliminar
//	 * 
//	 * **/
//	@RequestMapping(value = "/usuario/eliminar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String eliminarUsuario(@RequestBody String data) {
//		VOResponse responsevo = new VOResponse();
//
//		JsonObject usuario = gson.fromJson(data, JsonObject.class);
//		Long idUsuario = usuario.get("idUsuario").getAsLong();
//
//		try {
//			usuarioDAO.borrar(idUsuario);
//			responsevo.setOk(true);
//		} catch (Exception e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error al intentar eliminar un usuario");
//		}
//		return gson.toJson(responsevo);
//	}
}
