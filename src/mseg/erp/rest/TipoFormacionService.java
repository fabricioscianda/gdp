package mseg.erp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.tipoformacion.ITipoFormacionDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoFormacion;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/rest/tipoFormacionService")
@Scope("request")
public class TipoFormacionService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoFormacionDAO tipoFormacionDAO;

	@Inject
	private Gson gson;

	public TipoFormacionService() {
	}

	@RequestMapping(value = "/tipoFormacion/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoFormacion voTipoFormacion = gson.fromJson(object.get("nuevo"), VOTipoFormacion.class);
		try {
			VOTipoFormacion tipoFormacion = null;
			emfh.beginTransaction(em);
			voTipoFormacion.setNombre(StringUtils.capitalize(voTipoFormacion.getNombre()));
			if (voTipoFormacion.getId() != null && voTipoFormacion.getId() != 0) {
				tipoFormacion = tipoFormacionDAO.modificar(voTipoFormacion, em);
			} else {
				tipoFormacion = tipoFormacionDAO.guardar(voTipoFormacion, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoFormacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoFormacion/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoFormacion> tiposFormacion = tipoFormacionDAO.listar(em);
			voResponse.setData(gson.toJson(tiposFormacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoFormacion/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoFormacionDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoFormacion/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoFormacion voTipoFormacion = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoFormacion = tipoFormacionDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoFormacion));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
