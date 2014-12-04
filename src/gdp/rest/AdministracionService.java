package gdp.rest;

import gdp.dao.tipoadministracion.ITipoAdministracionDAO;
import gdp.spring.bootstrap.EntityManagerFactoryHolder;
import gdp.vomodel.VOResponse;
import gdp.vomodel.VOTipoAdministracion;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/rest/administracionService")
@Scope("request")
public class AdministracionService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoAdministracionDAO administracionDAO;

	@Inject
	private Gson gson;

	public AdministracionService() {
	}

	@RequestMapping(value = "/administracion/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardarAdministracion(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoAdministracion voTipoAdministracion = gson.fromJson(object.get("nuevo"), VOTipoAdministracion.class);
		try {
			emfh.beginTransaction(em);
			administracionDAO.guardar(voTipoAdministracion, em);
			emfh.commitTransaction(em);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
