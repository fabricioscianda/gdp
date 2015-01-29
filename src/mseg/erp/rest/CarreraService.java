package mseg.erp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.carrera.ICarreraDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VOCarrera;
import mseg.erp.vomodel.VOResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/rest/carreraService")
@Scope("request")
public class CarreraService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ICarreraDAO carreraDAO;
	
	@Inject
	private Gson gson;

	public CarreraService() {
	}

	@RequestMapping(value = "/carrera/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOCarrera voCarrera = gson.fromJson(object.get("nueva"), VOCarrera.class);
		try {
			VOCarrera carrera = null;
			emfh.beginTransaction(em);
			voCarrera.setNombre(StringUtils.capitalize(voCarrera.getNombre()));
			if (voCarrera.getId() != null && voCarrera.getId() != 0) {
				carrera = carreraDAO.modificar(voCarrera, em);
			} else {
				carrera = carreraDAO.guardar(voCarrera, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(carrera));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse la nueva carrera.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/carrera/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOCarrera> carreras = carreraDAO.listar(em);
			voResponse.setData(gson.toJson(carreras));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar las carreras.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/carrera/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				carreraDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar la carrera.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/carrera/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOCarrera voCarrera = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voCarrera = carreraDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voCarrera));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar la carrera.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
