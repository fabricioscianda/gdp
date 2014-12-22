package gdp.rest;

import gdp.dao.partido.IPartidoDAO;
import gdp.spring.bootstrap.EntityManagerFactoryHolder;
import gdp.vomodel.VOPartido;
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
@RequestMapping(value = "/rest/partidoService")
@Scope("request")
public class PartidoService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private IPartidoDAO partidoDAO;
	
	@Inject
	private Gson gson;

	public PartidoService() {
	}

	@RequestMapping(value = "/partido/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOPartido voPartido = gson.fromJson(object.get("nuevo"), VOPartido.class);
		try {
			VOPartido partido = null;
			emfh.beginTransaction(em);
			if (voPartido.getId() != null && voPartido.getId() != 0) {
				partido = partidoDAO.modificar(voPartido, em);
			} else {
				partido = partidoDAO.guardar(voPartido, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(partido));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo partido.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/partido/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOPartido voPartido = gson.fromJson(object.get("nuevo"), VOPartido.class);
		try {
			emfh.beginTransaction(em);
			VOPartido partido = partidoDAO.modificar(voPartido, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(partido));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el partido.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/partido/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOPartido> partidos = partidoDAO.listar(em);
			voResponse.setData(gson.toJson(partidos));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los partidos.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/partido/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				partidoDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar el partido.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/partido/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOPartido voPartido = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voPartido = partidoDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voPartido));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el partido.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
