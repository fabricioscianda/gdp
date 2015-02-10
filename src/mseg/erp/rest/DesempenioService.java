package mseg.erp.rest;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.asignatura.IAsignaturaDAO;
import mseg.erp.dao.desempenio.IDesempenioDAO;
import mseg.erp.dao.docente.IDocenteDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VOAsignatura;
import mseg.erp.vomodel.VODesempenio;
import mseg.erp.vomodel.VODocente;
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
	private IDocenteDAO docenteDAO;
	@Inject
	private IAsignaturaDAO asignaturaDAO;
	
	@Inject
	private Gson gson;

	public DesempenioService() {
	}

	@RequestMapping(value = "/desempenio/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VODesempenio voDesempenio = null;
		VODesempenio desempenio = null;
		VODocente docente = null;
		VOAsignatura asignatura = null;
		Integer mes = 0, hcs = 0, faltas = 0;
		Long anio = null, id_desempenio = null;
		
		try {
			id_desempenio = gson.fromJson(object.get("id_desempenio"), Long.class);
			docente = docenteDAO.encontrar(gson.fromJson(object.get("id_docente"), Long.class), em);
			asignatura = asignaturaDAO.encontrar(gson.fromJson(object.get("id_asignatura"), Long.class), em);
			anio = gson.fromJson(object.get("anio"), Long.class);
			mes = gson.fromJson(object.get("mes"), Integer.class);
			hcs = gson.fromJson(object.get("hcs"), Integer.class);
			faltas = gson.fromJson(object.get("faltas"), Integer.class);
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(anio);
			
			voDesempenio = new VODesempenio();
			voDesempenio.setId(id_desempenio);
			voDesempenio.setDocente(docente);
			voDesempenio.setAsignatura(asignatura);
			voDesempenio.setAnio(calendar.get(Calendar.YEAR));
			voDesempenio.setMes(mes);
			voDesempenio.setHcs(hcs);
			voDesempenio.setFaltas(faltas);
			
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
