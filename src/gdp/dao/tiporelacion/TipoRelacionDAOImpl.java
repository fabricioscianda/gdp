package gdp.dao.tiporelacion;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoRelacion;
import gdp.vomodel.VOTipoRelacion;

public class TipoRelacionDAOImpl extends GenericDAOImpl<VOTipoRelacion, TipoRelacion> implements ITipoRelacionDAO {

	public TipoRelacionDAOImpl() {
		super(TipoRelacion.class, VOTipoRelacion.class);
	}

	@Override
	public Long getId(VOTipoRelacion objetoVO) {
		return objetoVO.getId();
	}

}
