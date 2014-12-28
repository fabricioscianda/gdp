package mseg.erp.rest;

import mseg.erp.dao.tipomotivo.ITipoMotivoDAO;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoMotivo;

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
@RequestMapping(value = "/rest/tipoMotivoService")
@Scope("request")
public class TipoMotivoService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoMotivoDAO tipoMotivoDAO;

	@Inject
	private Gson gson;

	public TipoMotivoService() {
	}

	@RequestMapping(value = "/tipoMotivo/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoMotivo voTipoMotivo = gson.fromJson(object.get("nuevo"), VOTipoMotivo.class);
		try {
			VOTipoMotivo tipoMotivo = null;
			emfh.beginTransaction(em);
			if (voTipoMotivo.getId() != null && voTipoMotivo.getId() != 0) {
				tipoMotivo = tipoMotivoDAO.modificar(voTipoMotivo, em);
			} else {
				tipoMotivo = tipoMotivoDAO.guardar(voTipoMotivo, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoMotivo));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoMotivo/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoMotivo voTipoMotivo = gson.fromJson(object.get("nuevo"), VOTipoMotivo.class);
		try {
			emfh.beginTransaction(em);
			VOTipoMotivo tipoMotivo = tipoMotivoDAO.modificar(voTipoMotivo, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoMotivo));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoMotivo/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoMotivo> tiposMotivos = tipoMotivoDAO.listar(em);
			voResponse.setData(gson.toJson(tiposMotivos));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoMotivo/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoMotivoDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoMotivo/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoMotivo voTipoMotivo = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoMotivo = tipoMotivoDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoMotivo));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
