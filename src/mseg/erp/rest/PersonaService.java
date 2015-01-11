package mseg.erp.rest;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.docente.IDocenteDAO;
import mseg.erp.dao.persona.IPersonaDAO;
import mseg.erp.dao.tipodocumento.ITipoDocumentoDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VODocente;
import mseg.erp.vomodel.VOPersona;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoDocumento;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping(value = "/rest/personaService")
@Scope("request")
public class PersonaService {

	@Inject
	private EntityManagerFactoryHolder emfh;

	@Inject
	private IPersonaDAO personaDAO;
	@Inject
	private IDocenteDAO docenteDAO;
	@Inject
	private ITipoDocumentoDAO tipoDocumentoDAO;
	
	@Inject
	private Gson gson;

	public PersonaService() {
	}

	@RequestMapping(value = "/persona/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		try {
			VOPersona voPersona = new VOPersona(),
					  persona = null;
			
			VODocente voDocente = null,
					  docente = null;
			
			Long id_tipoDoc = null,
				 fechaNac = null;
			
			String nombre = null, 
				   apellido = null, 
				   numeroDoc = null, 
				   numeroDocCuil = null, 
				   tipoCuil = null, 
				   validadorCuil = null,
				   tipoDoc_id = null,
				   cuil = null;
			
			nombre = gson.fromJson(object.get("nombre"), String.class);
			apellido = gson.fromJson(object.get("apellido"), String.class);
			numeroDoc = gson.fromJson(object.get("numeroDoc"), String.class);
			tipoDoc_id = gson.fromJson(object.get("id_tipoDoc"), String.class);
			fechaNac = gson.fromJson(object.get("fechaNac"), Long.class);
			
			numeroDocCuil = gson.fromJson(object.get("numeroDocCuil"), String.class);
			tipoCuil = gson.fromJson(object.get("tipoCuil"), String.class); 
			validadorCuil = gson.fromJson(object.get("validadorCuil"), String.class);
			
//			List<VOContacto> mediosContactoList = null;
//			List<VODomicilio> domiciliosList = null;
			
//			JsonArray mediosContactoJson = object.get("mediosContacto").getAsJsonArray();
//			if (!mediosContactoJson.isJsonNull()) {
//				Type listType = new TypeToken<List<TipoContacto>>() {}.getType();
//				mediosContactoList = gson.fromJson(mediosContactoJson, listType);
//				for (int i = 0; i < mediosContactoList.size(); i++) {
//					mediosContactoList.get(i);
//				}
//			}
			
//			JsonArray domiciliosJson = object.get("domicilios").getAsJsonArray();
//			if (!domiciliosJson.isJsonNull()) {
//				Type listType = new TypeToken<List<Domicilio>>() {}.getType();
//				domiciliosList = gson.fromJson(domiciliosJson, listType);
//				for (int i = 0; i < domiciliosList.size(); i++) {
//					domiciliosList.get(i);
//				}
//			}
			
			id_tipoDoc = Long.valueOf(tipoDoc_id);
			VOTipoDocumento voTipoDocumento = tipoDocumentoDAO.encontrar(id_tipoDoc, em);
			cuil = tipoCuil + numeroDocCuil + validadorCuil;
			
			voPersona.setNombre(nombre);
			voPersona.setApellido(apellido);
			voPersona.setNumeroDoc(numeroDoc);
			voPersona.setFechaNac(fechaNac);
			voPersona.setCuil(cuil);
			voPersona.setTipoDoc(voTipoDocumento);
			
//			voPersona.setMediosContacto(mediosContactoList);
//			voPersona.setDomicilios(domiciliosList);
			
			emfh.beginTransaction(em);
			if (voPersona.getId() != null && voPersona.getId() != 0) {
				persona = personaDAO.modificar(voPersona, em);
			} else {
				persona = personaDAO.guardar(voPersona, em);
			}
			emfh.commitTransaction(em);
			
			voResponse.setData(gson.toJson(persona));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse la nueva persona.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/persona/editar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String editar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		VOPersona voPersona = gson.fromJson(object.get("nueva"), VOPersona.class);
		try {
			emfh.beginTransaction(em);
			VOPersona persona = personaDAO.modificar(voPersona, em);
			emfh.commitTransaction(em);
			voResponse.setData(gson.toJson(persona));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse la persona.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/persona/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VOPersona> personas = personaDAO.listar(em);
			voResponse.setData(gson.toJson(personas));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar las personas.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/persona/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			if (id != null) {
				emfh.beginTransaction(em);
				personaDAO.borrar(id, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar la persona.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/persona/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VOPersona voPersona = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voPersona = personaDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voPersona));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar la persona.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
