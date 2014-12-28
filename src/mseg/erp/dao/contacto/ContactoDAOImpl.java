package mseg.erp.dao.contacto;

import mseg.erp.model.Contacto;
import mseg.erp.vomodel.VOContacto;
import mseg.erp.dao.generic.GenericDAOImpl;

public class ContactoDAOImpl extends GenericDAOImpl<VOContacto, Contacto> implements IContactoDAO {

	public ContactoDAOImpl() {
		super(Contacto.class, VOContacto.class);
	}

	@Override
	public Long getId(VOContacto objetoVO) {
		return objetoVO.getId();
	}

}
