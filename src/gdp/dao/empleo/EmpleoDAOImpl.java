package gdp.dao.empleo;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Empleo;
import gdp.vomodel.VOEmpleo;

public class EmpleoDAOImpl extends GenericDAOImpl<VOEmpleo, Empleo> implements IEmpleoDAO {

	public EmpleoDAOImpl(Class<Empleo> persistentClass, Class<VOEmpleo> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOEmpleo objetoVO) {
		return objetoVO.getId();
	}

}
