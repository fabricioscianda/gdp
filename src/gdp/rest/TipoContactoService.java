package gdp.rest;

import gdp.dao.tipocontacto.ITipoContactoDAO;
import gdp.spring.bootstrap.EntityManagerFactoryHolder;
import gdp.vomodel.VOResponse;
import gdp.vomodel.VOTipoContacto;

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
@RequestMapping(value = "/rest/tipoContactoService")
@Scope("request")
public class TipoContactoService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoContactoDAO tipoContactoDAO;

	@Inject
	private Gson gson;

	public TipoContactoService() {
	}

	@RequestMapping(value = "/tipoContacto/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoContacto voTipoContacto = gson.fromJson(object.get("nuevo"), VOTipoContacto.class);
		try {
			VOTipoContacto tipoContacto = null;
			emfh.beginTransaction(em);
			if (voTipoContacto.getId() != null && voTipoContacto.getId() != 0) {
				tipoContacto = tipoContactoDAO.modificar(voTipoContacto, em);
			} else {
				tipoContacto = tipoContactoDAO.guardar(voTipoContacto, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoContacto));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoContacto/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoContacto voTipoContacto = gson.fromJson(object.get("nuevo"), VOTipoContacto.class);
		try {
			emfh.beginTransaction(em);
			VOTipoContacto tipoContacto = tipoContactoDAO.modificar(voTipoContacto, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoContacto));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoContacto/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoContacto> tiposContacto = tipoContactoDAO.listar(em);
			voResponse.setData(gson.toJson(tiposContacto));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoContacto/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoContactoDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoContacto/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoContacto voTipoContacto = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoContacto = tipoContactoDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoContacto));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
