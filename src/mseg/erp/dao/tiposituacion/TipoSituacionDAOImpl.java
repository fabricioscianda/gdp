package mseg.erp.dao.tiposituacion;

import mseg.erp.model.TipoSituacion;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoSituacion;

public class TipoSituacionDAOImpl extends GenericDAOImpl<VOTipoSituacion, TipoSituacion> implements ITipoSituacionDAO {

	public TipoSituacionDAOImpl() {
		super(TipoSituacion.class, VOTipoSituacion.class);
	}

	@Override
	public Long getId(VOTipoSituacion objetoVO) {
		return objetoVO.getId();
	}

}
