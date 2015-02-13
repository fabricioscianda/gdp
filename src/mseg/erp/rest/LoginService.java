package mseg.erp.rest;

import java.util.Date;

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
import com.google.gson.JsonParser;

@RestController
@RequestMapping(value = "/rest/loginService")
@Scope("request")
public class LoginService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private IUsuarioDAO usuarioDAO;

	@Inject
	private Gson gson;

	public LoginService() {
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String login(@RequestBody String data, HttpServletRequest request) {

		VOResponse responsevo = new VOResponse();
		VOCredential credentialvo = new VOCredential();
		VOUsuario usuarioVO = null;
		JsonParser parser = new JsonParser();
		JsonObject usuario = parser.parse(data).getAsJsonObject();
		String username = usuario.get("username").getAsString();
		String password = usuario.get("password").getAsString();

		try {
			EntityManager em = emfh.getEntityManager();
			usuarioVO = usuarioDAO.login(username, password, em);
			if (usuarioVO != null) {
				String token = String.valueOf(new Date().getTime());
				credentialvo.setToken(token);
				credentialvo.setUsuario(usuarioVO);
				credentialvo.setEsAdmin(usuarioVO.isEsAdmin());
				request.getSession().setAttribute("credentials", credentialvo);
				JsonObject t = new JsonObject();
				t.addProperty("token", token);
				t.addProperty("esAdmin", usuarioVO.isEsAdmin());
				t.addProperty("loginName", (usuarioVO.getNombre() + " " + usuarioVO.getApellido()));
				responsevo.setOk(true);
				responsevo.setData(t.toString());
			} else {
				responsevo.setOk(false);
				responsevo.setErrorCode("111");
				responsevo.setErrorMessage("Nombre de usuario o contraseña incorrectos");
			}
		} catch (DAOException e) {
			responsevo.setOk(false);
			responsevo.setErrorMessage("Error en el login");
		}

		return gson.toJson(responsevo);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String logout(@RequestBody String data, HttpServletRequest request) {
		VOResponse responsevo = new VOResponse();

		try {
			request.getSession().removeAttribute("credentials");
			responsevo.setOk(true);
		} catch (Exception e) {
			responsevo.setOk(false);
			responsevo.setErrorMessage("Error en el logout");
		}

		return gson.toJson(responsevo);
	}

}
