package gdp.dao.tiposituacion;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoSituacion;
import gdp.vomodel.VOTipoSituacion;

public class TipoSituacionDAOImpl extends GenericDAOImpl<VOTipoSituacion, TipoSituacion> implements ITipoSituacionDAO {

	public TipoSituacionDAOImpl() {
		super(TipoSituacion.class, VOTipoSituacion.class);
	}

	@Override
	public Long getId(VOTipoSituacion objetoVO) {
		return objetoVO.getId();
	}

}
