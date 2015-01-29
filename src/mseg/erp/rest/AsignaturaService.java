package mseg.erp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.asignatura.IAsignaturaDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VOAsignatura;
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
@RequestMapping(value = "/rest/asignaturaService")
@Scope("request")
public class AsignaturaService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private IAsignaturaDAO asignaturaDAO;
	
	@Inject
	private Gson gson;

	public AsignaturaService() {
	}

	@RequestMapping(value = "/asignatura/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOAsignatura voAsignatura = gson.fromJson(object.get("nueva"), VOAsignatura.class);
		try {
			VOAsignatura asignatura = null;
			emfh.beginTransaction(em);
			voAsignatura.setNombre(StringUtils.capitalize(voAsignatura.getNombre()));
			if (voAsignatura.getId() != null && voAsignatura.getId() != 0) {
				asignatura = asignaturaDAO.modificar(voAsignatura, em);
			} else {
				asignatura = asignaturaDAO.guardar(voAsignatura, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(asignatura));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse la nueva asignatura.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/asignatura/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOAsignatura> asignaturas = asignaturaDAO.listar(em);
			voResponse.setData(gson.toJson(asignaturas));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar las asignaturas.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/asignatura/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				asignaturaDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar la asignatura.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/asignatura/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOAsignatura voAsignatura = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voAsignatura = asignaturaDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voAsignatura));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar la asignatura.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
