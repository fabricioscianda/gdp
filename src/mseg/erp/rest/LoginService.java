package mseg.erp.rest;
//package mseg.erp.rest;
//
//import java.util.Date;
//
//import javax.inject.Inject;
//import javax.servlet.http.HttpServletRequest;
//
//import org.springframework.context.annotation.Scope;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//
//import gdp.exceptions.DAOException;
//import gdp.vomodel.VOCredential;
//import gdp.vomodel.VOResponse;
//
//@RestController
//@RequestMapping(value = "/rest/loginService")
//@Scope("request")
//public class LoginService {
//
//	@Inject
//	private IInternoDAO internoDAO;
//	
//	@Inject
//	private IOficinaDAO oficinaDAO;
//
//	@Inject
//	private Gson gson;
//
//	public LoginService() {
//	}
//
//	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String login(@RequestBody String data, HttpServletRequest request) {
//		VOResponse responsevo = new VOResponse();
//		VOCredential credentialvo = new VOCredential();
//
//		VOInterno internovo = new VOInterno();
//
//		JsonParser parser = new JsonParser();
//		JsonObject interno = parser.parse(data).getAsJsonObject();
//
//		String u = interno.get("nombreUsuario").getAsString();
//		String c = interno.get("clave").getAsString();
//
//		try {
//			internovo = internoDAO.login(u, c); //Busca por nombre de usuario y verifica que la contrase�a sea valida
//			//internovo = internoDAO.buscar(u); //Busca solo por nombre de usuario, sin contrase�a
//
//			if (internovo != null) {
//				String token = String.valueOf(new Date().getTime());
//
//					credentialvo.setToken(token);
//					credentialvo.setUsuario(internovo);
//					credentialvo.setCantRecordatorios(new Long(internovo.getAgenda().getRecordatorios().size()));
//					credentialvo.setEsAdmin(internovo.tieneRolAdministrador());
//
//					request.getSession().setAttribute("credentials", credentialvo);
//
//					JsonObject t = new JsonObject();
//					t.addProperty("token", token);
//
//					responsevo.setOk(true);
//					responsevo.setData(t.toString());
//
//			} else {
//				responsevo.setOk(false);
//				responsevo.setErrorCode("111");
//				responsevo.setErrorMessage("Nombre de usuario o contraseña incorrectos");
//			}
//		} catch (DAOException e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error en el login");
//		}
//
//		return gson.toJson(responsevo);
//	}
//
//	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String logout(@RequestBody String data, HttpServletRequest request) {
//		VOResponse responsevo = new VOResponse();
//
//		try {
//			request.getSession().removeAttribute("credentials");
//			responsevo.setOk(true);
//		} catch (Exception e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error en el logout");
//		}
//
//		return gson.toJson(responsevo);
//	}
//
//	@RequestMapping(value = "/datos", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String buscarDatos(@RequestBody String data, HttpServletRequest request) {
//		VOResponse responsevo = new VOResponse();
//
//		try {
//			VOCredential credentialvo = (VOCredential) request.getSession().getAttribute("credentials");
//			String claveTemporal = credentialvo.getUsuario().getClave();
//			credentialvo.getUsuario().setClave(null);
//			credentialvo.setCantRecordatorios(internoDAO.contarRecordatoriosHoy(credentialvo.getUsuario().getId()));
//			
////			Solo descomentar esta linea si el usuario logueado se refresca (se recarga de la BD por ejemplo)
////			credentialvo.setEsAdmin(internovo.esAdministrador());
//			
//			responsevo.setOk(true);
//			responsevo.setData(gson.toJson(credentialvo));
//			credentialvo.getUsuario().setClave(claveTemporal);
//		} catch (Exception e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error al traer los datos de la session");
//		}
//
//		return gson.toJson(responsevo);
//	}
//
//	@RequestMapping(value = "/cambiar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
//	public String cambiarOficina(@RequestBody String data, HttpServletRequest request) {
//		VOResponse responsevo = new VOResponse();
//		try {
//			JsonObject obj = gson.fromJson(data, JsonObject.class);
//			Long idOficina = obj.get("idOficina").getAsLong();
//			
//			VOOficina voOficina= oficinaDAO.encontrar(idOficina);
//			VOCredential credentialvo= (VOCredential) request.getSession().getAttribute("credentials");
//			credentialvo.setOficina(voOficina);
//			request.getSession().setAttribute("credentials", credentialvo);
//			
//			responsevo.setOk(true);
//			responsevo.setData(gson.toJson(credentialvo));
//		} catch (Exception e) {
//			responsevo.setOk(false);
//			responsevo.setErrorMessage("Error");
//		}
//
//		return gson.toJson(responsevo);
//	}
//
//}
