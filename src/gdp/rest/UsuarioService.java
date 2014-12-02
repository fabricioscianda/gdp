package gdp.rest;
//package gdp.rest;
//
//import java.lang.reflect.Type;
//import java.util.List;
//
//import javax.inject.Inject;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//import com.google.gson.reflect.TypeToken;
//
//import gdp.exceptions.DAOException;
//import gdp.vomodel.VOCredential;
//import gdp.vomodel.VOResponse;
//import gdp.vomodel.VOUsuario;
//
///** 
// * @author nserra
// * 
// **/
//
///**
// * Servicio para la gesti�n de usuarios
// * 
// * **/
//@RestController
//@RequestMapping(value = "/rest/usuarioService")
//@Scope("request")
//public class UsuarioService {
//
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
//	 * **/
//	@RequestMapping(value = "/usuario/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String guardarUsuario(@RequestBody String data) {
//		VOResponse responsevo = new VOResponse();
//
//		JsonObject obj = gson.fromJson(data, JsonObject.class);
//
//		VOUsuario vousuario = gson.fromJson(obj.get("usuario"),VOUsuario.class);
//
//		List<Long> oficinas=null;
//
//		JsonArray oficinaJson = obj.get("oficinas").getAsJsonArray();
//		if (!oficinaJson.isJsonNull()) {
//			Type listType = new TypeToken<List<Long>>() {}.getType();
//			oficinas = gson.fromJson(oficinaJson, listType);
//		}else{
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Debe elegir al menos una oficina");
//		}
//		Long idRol = obj.get("idRol").getAsLong();
//		if(vousuario.isLogueable()){
//			String nombreUsuario = vousuario.getEmail().substring(0,vousuario.getEmail().indexOf('@'));
//			vousuario.setNombreUsuario(nombreUsuario);
//		}
//		try {
//			usuarioDAO.crearUsuario(vousuario, idRol, oficinas);
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
//	 * Retorna un VO con el listado de todos los usuarios correspondientes al
//	 * municipio logueado
//	 * 
//	 * **/
//	@RequestMapping(value = "/usuario/oficinas", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String listarOficinas(@RequestBody String data) {
//		VOResponse responsevo = new VOResponse();
//		try {
//			
//			Long idUsuario = credentials.getUsuario().getId();
//			
//			List<VOOficina> listaOficinas = usuarioDAO.listarOficinas(idUsuario);
//			responsevo.setOk(true);
//			responsevo.setData(gson.toJson(listaOficinas));
//		} catch (Exception e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error al intentar listar oficinas de un usuario");
//		}
//
//		return gson.toJson(responsevo);
//	}
//
//	/**
//	 * Recibe un string json con un id de usuario Retorna un VO con el usuario
//	 * correspondiente al id recibido como parametro
//	 * 
//	 * **/
//	@RequestMapping(value = "/usuario/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String buscarUsuario(@RequestBody String data) {
//		VOResponse responsevo = new VOResponse();
//		JsonObject obj = gson.fromJson(data,JsonObject.class);
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
//	 * Recibe un string json con los datos del usuario Modifica los datos del
//	 * usuario recibido como parametro Retorna un VO informando si el usuario se
//	 * pudo modificar
//	 * 
//	 * **/
//	@RequestMapping(value = "/usuario/modificar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String modificarUsuario(@RequestBody String data) {
//		VOResponse responsevo = new VOResponse();
//
//		JsonObject obj = gson.fromJson(data, JsonObject.class);
//
//		VOUsuario vousuario = gson.fromJson(obj.get("usuario"),VOUsuario.class);
//
//		List<Long> oficinas=null;
//
//		JsonArray oficinaJson = obj.get("oficinas").getAsJsonArray();
//		if (!oficinaJson.isJsonNull()) {
//			Type listType = new TypeToken<List<Long>>() {}.getType();
//			oficinas = gson.fromJson(oficinaJson, listType);
//		}else{
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Debe elegir al menos una oficina");
//		}
//		Long idRol = obj.get("idRol").getAsLong();
//		
//		try {
//			usuarioDAO.crearUsuario(vousuario,idRol,oficinas);
//			responsevo.setOk(true);
//		} catch (Exception e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error al intentar modificar un usuario");
//		}
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
//		JsonObject usuario = gson.fromJson(data,JsonObject.class);
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
//}
