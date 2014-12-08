package gdp.dao.empleo;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Empleo;
import gdp.vomodel.VOEmpleo;

public class EmpleoDAOImpl extends GenericDAOImpl<VOEmpleo, Empleo> implements IEmpleoDAO {

	public EmpleoDAOImpl() {
		super(Empleo.class, VOEmpleo.class);
	}

	@Override
	public Long getId(VOEmpleo objetoVO) {
		return objetoVO.getId();
	}

}
