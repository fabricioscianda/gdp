package mseg.erp.rest;

import mseg.erp.dao.tipocargo.ITipoCargoDAO;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoCargo;

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
@RequestMapping(value = "/rest/tipoCargoService")
@Scope("request")
public class TipoCargoService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoCargoDAO tipoCargoDAO;

	@Inject
	private Gson gson;

	public TipoCargoService() {
	}

	@RequestMapping(value = "/tipoCargo/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoCargo voTipoCargo = gson.fromJson(object.get("nuevo"), VOTipoCargo.class);
		try {
			VOTipoCargo tipoCargo = null;
			emfh.beginTransaction(em);
			if (voTipoCargo.getId() != null && voTipoCargo.getId() != 0) {
				tipoCargo = tipoCargoDAO.modificar(voTipoCargo, em);
			} else {
				tipoCargo = tipoCargoDAO.guardar(voTipoCargo, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoCargo));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoCargo/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoCargo VOTipoCargo = gson.fromJson(object.get("nuevo"), VOTipoCargo.class);
		try {
			emfh.beginTransaction(em);
			VOTipoCargo tipoCargo = tipoCargoDAO.modificar(VOTipoCargo, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoCargo));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoCargo/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoCargo> tiposCargo = tipoCargoDAO.listar(em);
			voResponse.setData(gson.toJson(tiposCargo));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoCargo/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoCargoDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoCargo/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoCargo VOTipoCargo = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			VOTipoCargo = tipoCargoDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(VOTipoCargo));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
