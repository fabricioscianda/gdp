package gdp.dao.domicilio;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Domicilio;
import gdp.vomodel.VODomicilio;

public class DomicilioDAOImpl extends GenericDAOImpl<VODomicilio, Domicilio> implements IDomicilioDAO {

	public DomicilioDAOImpl() {
		super(Domicilio.class, VODomicilio.class);
	}

	@Override
	public Long getId(VODomicilio objetoVO) {
		return objetoVO.getId();
	}

}
