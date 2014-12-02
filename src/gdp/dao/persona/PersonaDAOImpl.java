package gdp.dao.persona;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Persona;
import gdp.vomodel.VOPersona;

public class PersonaDAOImpl extends GenericDAOImpl<VOPersona, Persona> implements IPersonaDAO {

	public PersonaDAOImpl(Class<Persona> persistentClass, Class<VOPersona> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOPersona objetoVO) {
		return objetoVO.getId();
	}

}
