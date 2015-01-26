package mseg.erp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.desempenio.IDesempenioDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VODesempenio;
import mseg.erp.vomodel.VOResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/rest/desempenioService")
@Scope("request")
public class DesempenioService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private IDesempenioDAO desempenioDAO;
	
	@Inject
	private Gson gson;

	public DesempenioService() {
	}

	@RequestMapping(value = "/desempenio/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VODesempenio voDesempenio = gson.fromJson(object.get("nuevo"), VODesempenio.class);
		try {
			VODesempenio desempenio = null;
			emfh.beginTransaction(em);
			if (voDesempenio.getId() != null && voDesempenio.getId() != 0) {
				desempenio = desempenioDAO.modificar(voDesempenio, em);
			} else {
				desempenio = desempenioDAO.guardar(voDesempenio, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(desempenio));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo desempeño.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/desempenio/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VODesempenio voDesempenio = gson.fromJson(object.get("nuevo"), VODesempenio.class);
		try {
			emfh.beginTransaction(em);
			VODesempenio desempenio = desempenioDAO.modificar(voDesempenio, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(desempenio));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el desempeño.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/desempenio/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VODesempenio> desempenios = desempenioDAO.listar(em);
			voResponse.setData(gson.toJson(desempenios));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los desempeños.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/desempenio/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				desempenioDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar el desempeño.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/desempenio/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VODesempenio voDesempenio = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voDesempenio = desempenioDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voDesempenio));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el desempeño.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
