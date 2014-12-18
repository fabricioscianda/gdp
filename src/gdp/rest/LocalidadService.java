package gdp.rest;

import gdp.dao.localidad.ILocalidadDAO;
import gdp.spring.bootstrap.EntityManagerFactoryHolder;
import gdp.vomodel.VOLocalidad;
import gdp.vomodel.VOResponse;

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
@RequestMapping(value = "/rest/localidadService")
@Scope("request")
public class LocalidadService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ILocalidadDAO localidadDAO;
	
	@Inject
	private Gson gson;

	public LocalidadService() {
	}

	@RequestMapping(value = "/localidad/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOLocalidad voLocalidad = gson.fromJson(object.get("nueva"), VOLocalidad.class);
		try {
			VOLocalidad localidad = null;
			emfh.beginTransaction(em);
			if (voLocalidad.getId() != null && voLocalidad.getId() != 0) {
				localidad = localidadDAO.modificar(voLocalidad, em);
			} else {
				localidad = localidadDAO.guardar(voLocalidad, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(localidad));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse la nueva localidad.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/localidad/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOLocalidad voLocalidad = gson.fromJson(object.get("nueva"), VOLocalidad.class);
		try {
			emfh.beginTransaction(em);
			VOLocalidad localidad = localidadDAO.modificar(voLocalidad, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(localidad));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse la localidad.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/localidad/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOLocalidad> localidades = localidadDAO.listar(em);
			voResponse.setData(gson.toJson(localidades));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar las localidades.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/localidad/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				localidadDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar la localidad.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/localidad/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOLocalidad voLocalidad = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voLocalidad = localidadDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voLocalidad));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar la localidad.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
