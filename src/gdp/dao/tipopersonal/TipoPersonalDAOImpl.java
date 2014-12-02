package gdp.dao.tipopersonal;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoPersonal;
import gdp.vomodel.VOTipoPersonal;

public class TipoPersonalDAOImpl extends GenericDAOImpl<VOTipoPersonal, TipoPersonal> implements ITipoPersonalDAO {

	public TipoPersonalDAOImpl(Class<TipoPersonal> persistentClass, Class<VOTipoPersonal> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOTipoPersonal objetoVO) {
		return objetoVO.getId();
	}

}
