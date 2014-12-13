package gdp.rest;

import gdp.dao.tipoestadoformacion.ITipoEstadoFormacionDAO;
import gdp.spring.bootstrap.EntityManagerFactoryHolder;
import gdp.vomodel.VOResponse;
import gdp.vomodel.VOTipoEstadoFormacion;

import java.util.List;

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
@RequestMapping(value = "/rest/tipoEstadoFormacionService")
@Scope("request")
public class TipoEstadoFormacionService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoEstadoFormacionDAO tipoEstadoFormacionDAO;

	@Inject
	private Gson gson;

	public TipoEstadoFormacionService() {
	}

	@RequestMapping(value = "/tipoEstadoFormacion/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoEstadoFormacion voTipoEstadoFormacion = gson.fromJson(object.get("nuevo"), VOTipoEstadoFormacion.class);
		try {
			VOTipoEstadoFormacion tipoEstadoFormacion = null;
			emfh.beginTransaction(em);
			if (voTipoEstadoFormacion.getId() != null && voTipoEstadoFormacion.getId() != 0) {
				tipoEstadoFormacion = tipoEstadoFormacionDAO.modificar(voTipoEstadoFormacion, em);
			} else {
				tipoEstadoFormacion = tipoEstadoFormacionDAO.guardar(voTipoEstadoFormacion, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoEstadoFormacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoEstadoFormacion/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoEstadoFormacion voTipoEstadoFormacion = gson.fromJson(object.get("nuevo"), VOTipoEstadoFormacion.class);
		try {
			emfh.beginTransaction(em);
			VOTipoEstadoFormacion tipoEstadoFormacion = tipoEstadoFormacionDAO.modificar(voTipoEstadoFormacion, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoEstadoFormacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoEstadoFormacion/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoEstadoFormacion> tiposEstadosFormacion = tipoEstadoFormacionDAO.listar(em);
			voResponse.setData(gson.toJson(tiposEstadosFormacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoEstadoFormacion/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoEstadoFormacionDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoEstadoFormacion/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoEstadoFormacion voTipoEstadoFormacion = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoEstadoFormacion = tipoEstadoFormacionDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoEstadoFormacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
