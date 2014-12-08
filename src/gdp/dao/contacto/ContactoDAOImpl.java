package gdp.dao.contacto;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Contacto;
import gdp.vomodel.VOContacto;

public class ContactoDAOImpl extends GenericDAOImpl<VOContacto, Contacto> implements IContactoDAO {

	public ContactoDAOImpl() {
		super(Contacto.class, VOContacto.class);
	}

	@Override
	public Long getId(VOContacto objetoVO) {
		return objetoVO.getId();
	}

}
