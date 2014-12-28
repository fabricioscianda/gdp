package mseg.erp.rest;

import mseg.erp.dao.tipopersonal.ITipoPersonalDAO;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoPersonal;

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
@RequestMapping(value = "/rest/tipoPersonalService")
@Scope("request")
public class TipoPersonalService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoPersonalDAO tipoPersonalDAO;

	@Inject
	private Gson gson;

	public TipoPersonalService() {
	}

	@RequestMapping(value = "/tipoPersonal/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoPersonal voTipoPersonal = gson.fromJson(object.get("nuevo"), VOTipoPersonal.class);
		try {
			VOTipoPersonal tipoPersonal = null;
			emfh.beginTransaction(em);
			if (voTipoPersonal.getId() != null && voTipoPersonal.getId() != 0) {
				tipoPersonal = tipoPersonalDAO.modificar(voTipoPersonal, em);
			} else {
				tipoPersonal = tipoPersonalDAO.guardar(voTipoPersonal, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoPersonal));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoPersonal/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoPersonal voTipoPersonal = gson.fromJson(object.get("nuevo"), VOTipoPersonal.class);
		try {
			emfh.beginTransaction(em);
			VOTipoPersonal tipoPersonal = tipoPersonalDAO.modificar(voTipoPersonal, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoPersonal));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoPersonal/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoPersonal> tiposPersonal = tipoPersonalDAO.listar(em);
			voResponse.setData(gson.toJson(tiposPersonal));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoPersonal/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoPersonalDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoPersonal/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoPersonal voTipoPersonal = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoPersonal = tipoPersonalDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoPersonal));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
