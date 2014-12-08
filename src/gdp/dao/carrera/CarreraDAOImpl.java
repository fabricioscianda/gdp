package gdp.dao.carrera;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Carrera;
import gdp.vomodel.VOCarrera;

public class CarreraDAOImpl extends GenericDAOImpl<VOCarrera, Carrera> implements ICarreraDAO {

	public CarreraDAOImpl() {
		super(Carrera.class, VOCarrera.class);
	}

	@Override
	public Long getId(VOCarrera objetoVO) {
		return objetoVO.getId();
	}

}
