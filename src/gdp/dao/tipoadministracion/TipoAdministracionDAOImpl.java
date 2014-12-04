package gdp.dao.tipoadministracion;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoAdministracion;
import gdp.vomodel.VOTipoAdministracion;

public class TipoAdministracionDAOImpl extends GenericDAOImpl<VOTipoAdministracion, TipoAdministracion> implements ITipoAdministracionDAO {

	public TipoAdministracionDAOImpl() {
		super(TipoAdministracion.class, VOTipoAdministracion.class);
	}

	@Override
	public Long getId(VOTipoAdministracion objetoVO) {
		return objetoVO.getId();
	}

}
