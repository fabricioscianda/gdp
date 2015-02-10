package mseg.erp.rest;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.planEstudio.IPlanEstudioDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VOPlanEstudio;
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
@RequestMapping(value = "/rest/planEstudioService")
@Scope("request")
public class PlanEstudioService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private IPlanEstudioDAO planEstudioDAO;
	
	@Inject
	private Gson gson;

	public PlanEstudioService() {
	}

	@RequestMapping(value = "/planEstudio/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOPlanEstudio voPlanEstudio = new VOPlanEstudio();
		
		String nombre = gson.fromJson(object.get("nombre"), String.class);
		Long anio = gson.fromJson(object.get("anio"), Long.class);
		Long id = gson.fromJson(object.get("id"), Long.class);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(anio);
		try {
			VOPlanEstudio planEstudio = null;
			voPlanEstudio.setId(id);
			voPlanEstudio.setNombre(StringUtils.capitalize(nombre));
			voPlanEstudio.setAnio(calendar.get(Calendar.YEAR));
			emfh.beginTransaction(em);
			if (id != null && id != 0) {
				planEstudio = planEstudioDAO.modificar(voPlanEstudio, em);
			} else {
				planEstudio = planEstudioDAO.guardar(voPlanEstudio, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(planEstudio));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo plan de estudio.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/planEstudio/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOPlanEstudio> planEstudios = planEstudioDAO.listar(em);
			voResponse.setData(gson.toJson(planEstudios));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los planes de estudios.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/planEstudio/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				planEstudioDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar el plan de estudio.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/planEstudio/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOPlanEstudio voPlanEstudio = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voPlanEstudio = planEstudioDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voPlanEstudio));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el plan de estudio.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}