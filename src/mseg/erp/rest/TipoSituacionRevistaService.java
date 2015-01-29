package mseg.erp.rest;

import mseg.erp.dao.tiposituacionrevista.ITipoSituacionRevistaDAO;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoSituacionRevista;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/rest/tipoSituacionRevistaService")
@Scope("request")
public class TipoSituacionRevistaService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private ITipoSituacionRevistaDAO tipoSituacionRevistaDAO;

	@Inject
	private Gson gson;

	public TipoSituacionRevistaService() {
	}

	@RequestMapping(value = "/tipoSituacionRevista/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOTipoSituacionRevista voTipoSituacionRevista = gson.fromJson(object.get("nuevo"), VOTipoSituacionRevista.class);
		try {
			VOTipoSituacionRevista tipoSituacionRevista = null;
			emfh.beginTransaction(em);
			voTipoSituacionRevista.setNombre(StringUtils.capitalize(voTipoSituacionRevista.getNombre()));
			if (voTipoSituacionRevista.getId() != null && voTipoSituacionRevista.getId() != 0) {
				tipoSituacionRevista = tipoSituacionRevistaDAO.modificar(voTipoSituacionRevista, em);
			} else {
				tipoSituacionRevista = tipoSituacionRevistaDAO.guardar(voTipoSituacionRevista, em);
			}
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(tipoSituacionRevista));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoSituacionRevista/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOTipoSituacionRevista> tiposSituacionRevista = tipoSituacionRevistaDAO.listar(em);
			voResponse.setData(gson.toJson(tiposSituacionRevista));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los tipo.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/tipoSituacionRevista/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				tipoSituacionRevistaDAO.borrar(id, em);
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

	@RequestMapping(value = "/tipoSituacionRevista/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOTipoSituacionRevista voTipoSituacionRevista = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voTipoSituacionRevista = tipoSituacionRevistaDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voTipoSituacionRevista));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el elemento.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
