package mseg.erp.dao.carrera;

import mseg.erp.model.Carrera;
import mseg.erp.vomodel.VOCarrera;
import mseg.erp.dao.generic.GenericDAOImpl;

public class CarreraDAOImpl extends GenericDAOImpl<VOCarrera, Carrera> implements ICarreraDAO {

	public CarreraDAOImpl() {
		super(Carrera.class, VOCarrera.class);
	}

	@Override
	public Long getId(VOCarrera objetoVO) {
		return objetoVO.getId();
	}

}
