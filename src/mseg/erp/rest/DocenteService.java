package mseg.erp.rest;

import java.lang.reflect.Type;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import mseg.erp.dao.docente.IDocenteDAO;
import mseg.erp.dao.persona.IPersonaDAO;
import mseg.erp.dao.tipodocumento.ITipoDocumentoDAO;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.vomodel.VOContacto;
import mseg.erp.vomodel.VODocente;
import mseg.erp.vomodel.VODomicilio;
import mseg.erp.vomodel.VOEmpleo;
import mseg.erp.vomodel.VOFormacionAcademica;
import mseg.erp.vomodel.VOInfoAdministrativa;
import mseg.erp.vomodel.VOLocalidad;
import mseg.erp.vomodel.VOPersona;
import mseg.erp.vomodel.VOResponse;
import mseg.erp.vomodel.VOTipoDocumento;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

@RestController
@RequestMapping(value = "/rest/docenteService")
@Scope("request")
public class DocenteService {

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

	public DocenteService() {
	}

	@RequestMapping(value = "/docente/guardar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String guardar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		JsonObject object = gson.fromJson(data, JsonObject.class);
		try {
			VOPersona voPersona = new VOPersona(),
					  persona = null;
			
			VODocente voDocente = null,
					  docente = null;

			VOInfoAdministrativa voInfoAdministrativa = null;
			
			Long id = null,
				 id_tipoDoc = null,
				 fechaNac = null;
			
			String nombre = null, 
				   apellido = null, 
				   numeroDoc = null, 
				   numeroDocCuil = null, 
				   tipoCuil = null, 
				   validadorCuil = null,
				   tipoDoc_id = null,
				   cuil = null,
				   cp = null;
			
			VOLocalidad localidad = null;
			
			id = gson.fromJson(object.get("id"), Long.class);
			nombre = gson.fromJson(object.get("nombre"), String.class);
			apellido = gson.fromJson(object.get("apellido"), String.class);
			numeroDoc = gson.fromJson(object.get("numeroDoc"), String.class);
			tipoDoc_id = gson.fromJson(object.get("id_tipoDoc"), String.class);
			fechaNac = gson.fromJson(object.get("fechaNac"), Long.class);
			cp = gson.fromJson(object.get("cp"), String.class);
			localidad = gson.fromJson(object.get("localidad"), VOLocalidad.class);
			
			numeroDocCuil = gson.fromJson(object.get("numeroDocCuil"), String.class);
			tipoCuil = gson.fromJson(object.get("tipoCuil"), String.class); 
			validadorCuil = gson.fromJson(object.get("validadorCuil"), String.class);
			
			List<VOContacto> mediosContactoList = null;
			JsonArray mediosContactoJson = object.get("mediosContacto").getAsJsonArray();
			if (!mediosContactoJson.isJsonNull()) {
				Type listType = new TypeToken<List<VOContacto>>() {}.getType();
				mediosContactoList = gson.fromJson(mediosContactoJson, listType);
			}
			
			List<VODomicilio> domiciliosList = null;
			JsonArray domiciliosJson = object.get("domicilios").getAsJsonArray();
			if (!domiciliosJson.isJsonNull()) {
				Type listType = new TypeToken<List<VODomicilio>>() {}.getType();
				domiciliosList = gson.fromJson(domiciliosJson, listType);
			}
			
			List<VOFormacionAcademica> formacionAcademicas = null;
			JsonArray formacionesJson = object.get("formacionAcademica").getAsJsonArray();
			if (!formacionesJson.isJsonNull()) {
				Type listType = new TypeToken<List<VOFormacionAcademica>>() {}.getType();
				formacionAcademicas = gson.fromJson(formacionesJson, listType);
			}
			
			List<VOEmpleo> empleos = null;
			JsonArray empleosJson = object.get("empleos").getAsJsonArray();
			if (!empleosJson.isJsonNull()) {
				Type listType = new TypeToken<List<VOEmpleo>>() {}.getType();
				empleos = gson.fromJson(empleosJson, listType);
			}

			voInfoAdministrativa = gson.fromJson(object.get("infoAdministrativa"), VOInfoAdministrativa.class);
			
			id_tipoDoc = Long.valueOf(tipoDoc_id);
			VOTipoDocumento voTipoDocumento = tipoDocumentoDAO.encontrar(id_tipoDoc, em);
			cuil = tipoCuil + numeroDocCuil + validadorCuil;
			
			voPersona.setNombre(StringUtils.capitalize(nombre));
			voPersona.setApellido(StringUtils.capitalize(apellido));
			voPersona.setNumeroDoc(numeroDoc);
			voPersona.setFechaNac(fechaNac);
			voPersona.setCuil(cuil);
			voPersona.setTipoDoc(voTipoDocumento);
			voPersona.setLocalidad(localidad);
			voPersona.setCp(cp);
			
			for (int i = 0; i < formacionAcademicas.size(); i++) {
				formacionAcademicas.get(i).setPersona(voPersona);
			}
			voPersona.setFormacionAcademica(formacionAcademicas);
			
			for (int i = 0; i < domiciliosList.size(); i++) {
				domiciliosList.get(i).setPersona(voPersona);
			}
			voPersona.setDomicilios(domiciliosList);
			
			for (int i = 0; i < mediosContactoList.size(); i++) {
				mediosContactoList.get(i).setPersona(voPersona);
			}
			voPersona.setMediosContacto(mediosContactoList);
			
			for (int i = 0; i < empleos.size(); i++) {
				empleos.get(i).setPersona(voPersona);
			}
			voPersona.setEmpleos(empleos);
			
			if (id != null && !id.equals(new Long("0"))) {
				voDocente = docenteDAO.encontrar(id, em);
				voPersona.setId(voDocente.getPersona().getId());
			} else {
				voDocente = new VODocente();
			}
			
			voDocente.setPersona(voPersona);
			voInfoAdministrativa.setPersona(voPersona);
			voPersona.setInfoAdministrativa(voInfoAdministrativa);
			
			emfh.beginTransaction(em);
			
			if (id != null && !id.equals(new Long("0"))) {
				persona = personaDAO.modificar(voPersona, em);
				docente = voDocente;
				docente.setPersona(persona);
			} else {
				docente = docenteDAO.guardar(voDocente, em);
			}
			
			emfh.commitTransaction(em);
			
			voResponse.setData(gson.toJson(docente));
			voResponse.setOk(true);
		} catch (Exception e) {
			emfh.rollbackTransaction(em);
			voResponse.setErrorMessage("No pudo guardarse el nuevo docente.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/docente/listar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String listar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			List<VODocente> docentes = docenteDAO.listar(em);
			voResponse.setData(gson.toJson(docentes));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudieron listar los docentes.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/docente/borrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String borrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id_docente = object.get("id").getAsLong(),
				 id_persona = null;
			if (id_docente != null) {
				VODocente docente = docenteDAO.encontrar(id_docente, em);
				id_persona = docente.getPersona().getId();
				emfh.beginTransaction(em);
				docenteDAO.borrar(id_docente, em);
				personaDAO.borrar(id_persona, em);
				emfh.commitTransaction(em);
			}
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo borrar el docente. Verifique que no tenga vinculos.");
			voResponse.setOk(false);
			emfh.rollbackTransaction(em);
		}
		return gson.toJson(voResponse);
	}

	@RequestMapping(value = "/docente/encontrar", method = RequestMethod.POST, produces = { "application/json; charset=UTF-8" })
	public String encontrar(@RequestBody String data) {
		VOResponse voResponse = new VOResponse();
		EntityManager em = emfh.getEntityManager();
		try {
			VODocente voDocente = null;
			JsonObject object = gson.fromJson(data, JsonObject.class);
			Long id = object.get("id").getAsLong();
			voDocente = docenteDAO.encontrar(id, em);
			voResponse.setData(gson.toJson(voDocente));
			voResponse.setOk(true);
		} catch (Exception e) {
			voResponse.setErrorMessage("No se pudo encontrar el docente.");
			voResponse.setOk(false);
		}
		return gson.toJson(voResponse);
	}

}
