package mseg.erp.dao.sede;

import mseg.erp.model.Sede;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOSede;

public class SedeDAOImpl extends GenericDAOImpl<VOSede, Sede> implements ISedeDAO {

	public SedeDAOImpl() {
		super(Sede.class, VOSede.class);
	}
	
	@Override
	public Long getId(VOSede objetoVO) {
		return objetoVO.getId();
	}

}
