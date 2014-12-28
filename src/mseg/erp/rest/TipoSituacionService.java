package mseg.erp.rest;

import mseg.erp.dao.tiposituacion.ITipoSituacionDAO;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoSituacion;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/rest/tipoSituacionService")
@Scope("request")
public class TipoSituacionService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoSituacionDAO tipoSituacionDAO;

	@Inject
	private Gson gson;

	public TipoSituacionService() {
	}

	@RequestMapping(value = "/tipoSituacion/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoSituacion voTipoSituacion = gson.fromJson(object.get("nuevo"), VOTipoSituacion.class);
		try {
			VOTipoSituacion tipoSituacion = null;
			emfh.beginTransaction(em);
			if (voTipoSituacion.getId() != null && voTipoSituacion.getId() != 0) {
				tipoSituacion = tipoSituacionDAO.modificar(voTipoSituacion, em);
			} else {
				tipoSituacion = tipoSituacionDAO.guardar(voTipoSituacion, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoSituacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoSituacion/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoSituacion voTipoSituacion = gson.fromJson(object.get("nuevo"), VOTipoSituacion.class);
		try {
			emfh.beginTransaction(em);
			VOTipoSituacion tipoSituacion = tipoSituacionDAO.modificar(voTipoSituacion, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoSituacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoSituacion/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoSituacion> tipoSituaciones = tipoSituacionDAO.listar(em);
			voResponse.setData(gson.toJson(tipoSituaciones));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoSituacion/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoSituacionDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar el elemento.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoSituacion/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoSituacion VOTipoSituacion = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			VOTipoSituacion = tipoSituacionDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(VOTipoSituacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
