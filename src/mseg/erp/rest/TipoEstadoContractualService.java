package mseg.erp.rest;

import mseg.erp.dao.tipoestadocontractual.ITipoEstadoContractualDAO;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoEstadoContractual;

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
@RequestMapping(value = "/rest/tipoEstadoContractualService")
@Scope("request")
public class TipoEstadoContractualService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoEstadoContractualDAO tipoEstadoContractualDAO;

	@Inject
	private Gson gson;

	public TipoEstadoContractualService() {
	}

	@RequestMapping(value = "/tipoEstadoContractual/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoEstadoContractual voTipoEstadoContractual = gson.fromJson(object.get("nuevo"),
				VOTipoEstadoContractual.class);
		try {
			VOTipoEstadoContractual tipoEstadoContractual = null;
			emfh.beginTransaction(em);
			if (voTipoEstadoContractual.getId() != null && voTipoEstadoContractual.getId() != 0) {
				tipoEstadoContractual = tipoEstadoContractualDAO.modificar(voTipoEstadoContractual, em);
			} else {
				tipoEstadoContractual = tipoEstadoContractualDAO.guardar(voTipoEstadoContractual, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoEstadoContractual));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoEstadoContractual/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoEstadoContractual voTipoEstadoContractual = gson.fromJson(object.get("nuevo"),
				VOTipoEstadoContractual.class);
		try {
			emfh.beginTransaction(em);
			VOTipoEstadoContractual tipoEstadoContractual = tipoEstadoContractualDAO.modificar(voTipoEstadoContractual,
					em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoEstadoContractual));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoEstadoContractual/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoEstadoContractual> tiposEstadosContractuales = tipoEstadoContractualDAO.listar(em);
			voResponse.setData(gson.toJson(tiposEstadosContractuales));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoEstadoContractual/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoEstadoContractualDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoEstadoContractual/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoEstadoContractual voTipoEstadoContractual = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoEstadoContractual = tipoEstadoContractualDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoEstadoContractual));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
