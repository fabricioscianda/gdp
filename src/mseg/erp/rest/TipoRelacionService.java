package mseg.erp.rest;

import mseg.erp.dao.tiporelacion.ITipoRelacionDAO;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoRelacion;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/rest/tipoRelacionService")
@Scope("request")
public class TipoRelacionService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoRelacionDAO tipoRelacionDAO;

	@Inject
	private Gson gson;

	public TipoRelacionService() {
	}

	@RequestMapping(value = "/tipoRelacion/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoRelacion voTipoRelacion = gson.fromJson(object.get("nuevo"), VOTipoRelacion.class);
		try {
			VOTipoRelacion tipoRelacion = null;
			emfh.beginTransaction(em);
			voTipoRelacion.setNombre(StringUtils.capitalize(voTipoRelacion.getNombre()));
			if (voTipoRelacion.getId() != null && voTipoRelacion.getId() != 0) {
				tipoRelacion = tipoRelacionDAO.modificar(voTipoRelacion, em);
			} else {
				tipoRelacion = tipoRelacionDAO.guardar(voTipoRelacion, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoRelacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoRelacion/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoRelacion> tiposRelaciones = tipoRelacionDAO.listar(em);
			voResponse.setData(gson.toJson(tiposRelaciones));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoRelacion/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoRelacionDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoRelacion/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoRelacion voTipoRelacion = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoRelacion = tipoRelacionDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoRelacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
