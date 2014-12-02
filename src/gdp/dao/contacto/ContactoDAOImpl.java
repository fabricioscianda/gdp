package gdp.dao.contacto;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Contacto;
import gdp.vomodel.VOContacto;

public class ContactoDAOImpl extends GenericDAOImpl<VOContacto, Contacto> implements IContactoDAO {

	public ContactoDAOImpl(Class<Contacto> persistentClass, Class<VOContacto> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOContacto objetoVO) {
		return objetoVO.getId();
	}

}
