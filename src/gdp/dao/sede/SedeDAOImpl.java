package gdp.dao.sede;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Sede;
import gdp.vomodel.VOSede;

public class SedeDAOImpl extends GenericDAOImpl<VOSede, Sede> implements ISedeDAO {

	public SedeDAOImpl() {
		super(Sede.class, VOSede.class);
	}
	
	@Override
	public Long getId(VOSede objetoVO) {
		return objetoVO.getId();
	}

}
