package gdp.dao.sede;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Sede;
import gdp.vomodel.VOSede;

public class SedeDAOImpl extends GenericDAOImpl<VOSede, Sede> implements ISedeDAO {

	public SedeDAOImpl(Class<Sede> persistentClass, Class<VOSede> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOSede objetoVO) {
		return objetoVO.getId();
	}

}
