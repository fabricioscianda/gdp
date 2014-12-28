package mseg.erp.dao.persona;

import mseg.erp.model.Persona;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOPersona;

public class PersonaDAOImpl extends GenericDAOImpl<VOPersona, Persona> implements IPersonaDAO {

	public PersonaDAOImpl() {
		super(Persona.class, VOPersona.class);
	}

	@Override
	public Long getId(VOPersona objetoVO) {
		return objetoVO.getId();
	}

}
