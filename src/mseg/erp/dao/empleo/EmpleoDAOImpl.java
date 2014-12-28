package mseg.erp.dao.empleo;

import mseg.erp.model.Empleo;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOEmpleo;

public class EmpleoDAOImpl extends GenericDAOImpl<VOEmpleo, Empleo> implements IEmpleoDAO {

	public EmpleoDAOImpl() {
		super(Empleo.class, VOEmpleo.class);
	}

	@Override
	public Long getId(VOEmpleo objetoVO) {
		return objetoVO.getId();
	}

}
