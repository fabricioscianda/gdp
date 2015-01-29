package mseg.erp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.provincia.IProvinciaDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VOProvincia;
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
@RequestMapping(value = "/rest/provinciaService")
@Scope("request")
public class ProvinciaService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private IProvinciaDAO provinciaDAO;

	@Inject
	private Gson gson;

	public ProvinciaService() {
	}

	@RequestMapping(value = "/provincia/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOProvincia voProvincia = gson.fromJson(object.get("nuevo"), VOProvincia.class);
		try {
			VOProvincia provincia = null;
			emfh.beginTransaction(em);
			voProvincia.setNombre(StringUtils.capitalize(voProvincia.getNombre()));
			if (voProvincia.getId() != null && voProvincia.getId() != 0) {
				provincia = provinciaDAO.modificar(voProvincia, em);
			} else {
				provincia = provinciaDAO.guardar(voProvincia, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(provincia));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse la nueva provincia.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/provincia/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOProvincia> provincias = provinciaDAO.listar(em);
			voResponse.setData(gson.toJson(provincias));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar las provincias.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/provincia/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				provinciaDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar la provincia.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/provincia/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOProvincia VOProvincia = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			VOProvincia = provinciaDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(VOProvincia));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar la provincia.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
