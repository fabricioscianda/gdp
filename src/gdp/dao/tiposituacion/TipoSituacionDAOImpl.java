package gdp.dao.tiposituacion;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoSituacion;
import gdp.vomodel.VOTipoSituacion;

public class TipoSituacionDAOImpl extends GenericDAOImpl<VOTipoSituacion, TipoSituacion> implements ITipoSituacionDAO {

	public TipoSituacionDAOImpl(Class<TipoSituacion> persistentClass, Class<VOTipoSituacion> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOTipoSituacion objetoVO) {
		return objetoVO.getId();
	}

}
