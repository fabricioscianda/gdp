package mseg.erp.dao.domicilio;

import mseg.erp.model.Domicilio;
import mseg.erp.vomodel.VODomicilio;
import mseg.erp.dao.generic.GenericDAOImpl;

public class DomicilioDAOImpl extends GenericDAOImpl<VODomicilio, Domicilio> implements IDomicilioDAO {

	public DomicilioDAOImpl() {
		super(Domicilio.class, VODomicilio.class);
	}

	@Override
	public Long getId(VODomicilio objetoVO) {
		return objetoVO.getId();
	}

}
