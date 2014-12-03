package gdp.rest;

import gdp.dao.tipoadministracion.ITipoAdministracionDAO;

import javax.inject.Inject;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping(value = "/rest/AdministracionService")
@Scope("request")
public class AdministracionService {

	@Inject
	private ITipoAdministracionDAO administracionDAO;

	@Inject
	private Gson gson;

	public AdministracionService() {
	}

}
