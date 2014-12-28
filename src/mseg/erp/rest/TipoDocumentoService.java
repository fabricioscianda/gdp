package mseg.erp.rest;

import mseg.erp.dao.tipodocumento.ITipoDocumentoDAO;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoDocumento;

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
@RequestMapping(value = "/rest/tipoDocumentoService")
@Scope("request")
public class TipoDocumentoService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoDocumentoDAO tipoDocumentoDAO;

	@Inject
	private Gson gson;

	public TipoDocumentoService() {
	}

	@RequestMapping(value = "/tipoDocumento/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoDocumento voTipoDocumento = gson.fromJson(object.get("nuevo"), VOTipoDocumento.class);
		try {
			VOTipoDocumento tipoDocumento = null;
			emfh.beginTransaction(em);
			if (voTipoDocumento.getId() != null && voTipoDocumento.getId() != 0) {
				tipoDocumento = tipoDocumentoDAO.modificar(voTipoDocumento, em);
			} else {
				tipoDocumento = tipoDocumentoDAO.guardar(voTipoDocumento, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoDocumento));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoDocumento/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoDocumento voTipoDocumento = gson.fromJson(object.get("nuevo"), VOTipoDocumento.class);
		try {
			emfh.beginTransaction(em);
			VOTipoDocumento tipoDocumento = tipoDocumentoDAO.modificar(voTipoDocumento, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoDocumento));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoDocumento/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoDocumento> tiposDocumento = tipoDocumentoDAO.listar(em);
			voResponse.setData(gson.toJson(tiposDocumento));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoDocumento/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoDocumentoDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoDocumento/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoDocumento voTipoDocumento = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoDocumento = tipoDocumentoDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoDocumento));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
