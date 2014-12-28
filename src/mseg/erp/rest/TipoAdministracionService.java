package mseg.erp.rest;

import mseg.erp.dao.tipoadministracion.ITipoAdministracionDAO;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoAdministracion;

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
@RequestMapping(value = "/rest/tipoAdministracionService")
@Scope("request")
public class TipoAdministracionService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoAdministracionDAO tipoAdministracionDAO;

	@Inject
	private Gson gson;

	public TipoAdministracionService() {
	}

	@RequestMapping(value = "/tipoAdministracion/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoAdministracion voTipoAdministracion = gson.fromJson(object.get("nuevo"), VOTipoAdministracion.class);
		try {
			VOTipoAdministracion tipoAdministracion = null;
			emfh.beginTransaction(em);
			if (voTipoAdministracion.getId()!=null && voTipoAdministracion.getId()!=0) {
				tipoAdministracion = tipoAdministracionDAO.modificar(voTipoAdministracion, em);
			} else {
				tipoAdministracion = tipoAdministracionDAO.guardar(voTipoAdministracion, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoAdministracion));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}
	
	@RequestMapping(value = "/tipoAdministracion/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoAdministracion voTipoAdministracion = gson.fromJson(object.get("nuevo"), VOTipoAdministracion.class);
		try {
			emfh.beginTransaction(em);
			VOTipoAdministracion tipoAdministracion = tipoAdministracionDAO.modificar(voTipoAdministracion, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoAdministracion));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoAdministracion/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoAdministracion> tiposAdministracion = tipoAdministracionDAO.listar(em);
			voResponse.setData(gson.toJson(tiposAdministracion));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}
	
	@RequestMapping(value = "/tipoAdministracion/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id!=null) {
				emfh.beginTransaction(em);
				tipoAdministracionDAO.borrar(id, em);
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
	
	@RequestMapping(value = "/tipoAdministracion/encontrar", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoAdministracion voTipoAdministracion = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoAdministracion = tipoAdministracionDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoAdministracion));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}
	
}
