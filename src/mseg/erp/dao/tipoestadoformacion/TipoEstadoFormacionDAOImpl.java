package mseg.erp.dao.tipoestadoformacion;

import mseg.erp.model.TipoEstadoFormacion;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoEstadoFormacion;

public class TipoEstadoFormacionDAOImpl extends GenericDAOImpl<VOTipoEstadoFormacion, TipoEstadoFormacion> implements ITipoEstadoFormacionDAO {

	public TipoEstadoFormacionDAOImpl() {
		super(TipoEstadoFormacion.class, VOTipoEstadoFormacion.class);
	}

	@Override
	public Long getId(VOTipoEstadoFormacion objetoVO) {
		return objetoVO.getId();
	}

}
