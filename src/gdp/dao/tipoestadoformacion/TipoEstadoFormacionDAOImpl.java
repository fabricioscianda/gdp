package gdp.dao.tipoestadoformacion;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoEstadoFormacion;
import gdp.vomodel.VOTipoEstadoFormacion;

public class TipoEstadoFormacionDAOImpl extends GenericDAOImpl<VOTipoEstadoFormacion, TipoEstadoFormacion> implements ITipoEstadoFormacionDAO {

	public TipoEstadoFormacionDAOImpl() {
		super(TipoEstadoFormacion.class, VOTipoEstadoFormacion.class);
	}

	@Override
	public Long getId(VOTipoEstadoFormacion objetoVO) {
		return objetoVO.getId();
	}

}
