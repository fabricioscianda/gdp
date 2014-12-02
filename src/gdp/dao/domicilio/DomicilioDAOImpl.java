package gdp.dao.domicilio;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Domicilio;
import gdp.vomodel.VODomicilio;

public class DomicilioDAOImpl extends GenericDAOImpl<VODomicilio, Domicilio> implements IDomicilioDAO {

	public DomicilioDAOImpl(Class<Domicilio> persistentClass, Class<VODomicilio> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VODomicilio objetoVO) {
		return objetoVO.getId();
	}

}
