package mseg.erp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.domicilio.IDomicilioDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VODomicilio;
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
@RequestMapping(value = "/rest/domicilioService")
@Scope("request")
public class DomicilioService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private IDomicilioDAO domicilioDAO;

	@Inject
	private Gson gson;

	public DomicilioService() {
	}

	@RequestMapping(value = "/domicilio/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VODomicilio voDomicilio = gson.fromJson(object.get("nuevo"), VODomicilio.class);
		try {
			VODomicilio domicilio = null;
			emfh.beginTransaction(em);
			voDomicilio.setDomicilio(StringUtils.capitalize(voDomicilio.getDomicilio()));
			if (voDomicilio.getId() != null && voDomicilio.getId() != 0) {
				domicilio = domicilioDAO.modificar(voDomicilio, em);
			} else {
				domicilio = domicilioDAO.guardar(voDomicilio, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(domicilio));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo domicilio.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/domicilio/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VODomicilio> domicilio = domicilioDAO.listar(em);
			voResponse.setData(gson.toJson(domicilio));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los domicilios.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/domicilio/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				domicilioDAO.borrar(id, em);
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

	@RequestMapping(value = "/domicilio/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VODomicilio voDomicilio = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voDomicilio = domicilioDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voDomicilio));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
