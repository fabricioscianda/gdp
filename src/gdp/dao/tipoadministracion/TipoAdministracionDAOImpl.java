package gdp.dao.tipoadministracion;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoAdministracion;
import gdp.vomodel.VOTipoAdministracion;

public class TipoAdministracionDAOImpl extends GenericDAOImpl<VOTipoAdministracion, TipoAdministracion> implements ITipoAdministracionDAO {

	public TipoAdministracionDAOImpl(Class<TipoAdministracion> persistentClass, Class<VOTipoAdministracion> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOTipoAdministracion objetoVO) {
		return objetoVO.getId();
	}

}
