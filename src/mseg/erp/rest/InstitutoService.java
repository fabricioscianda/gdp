package mseg.erp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.instituto.IInstitutoDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VOInstituto;
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
@RequestMapping(value = "/rest/institutoService")
@Scope("request")
public class InstitutoService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private IInstitutoDAO institutoDAO;

	@Inject
	private Gson gson;

	public InstitutoService() {
	}

	@RequestMapping(value = "/instituto/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOInstituto voInstituto = gson.fromJson(object.get("nuevo"), VOInstituto.class);
		try {
			VOInstituto instituto = null;
			emfh.beginTransaction(em);
			voInstituto.setNombre(StringUtils.capitalize(voInstituto.getNombre()));
			if (voInstituto.getId() != null && voInstituto.getId() != 0) {
				instituto = institutoDAO.modificar(voInstituto, em);
			} else {
				instituto = institutoDAO.guardar(voInstituto, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(instituto));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo instituto.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/instituto/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOInstituto> institutos = institutoDAO.listar(em);
			voResponse.setData(gson.toJson(institutos));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los institutos.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/instituto/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				institutoDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar el instituto.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/instituto/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOInstituto VOInstituto = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			VOInstituto = institutoDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(VOInstituto));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el instituto.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
