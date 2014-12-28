package mseg.erp.dao.tiporelacion;

import mseg.erp.model.TipoRelacion;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoRelacion;

public class TipoRelacionDAOImpl extends GenericDAOImpl<VOTipoRelacion, TipoRelacion> implements ITipoRelacionDAO {

	public TipoRelacionDAOImpl() {
		super(TipoRelacion.class, VOTipoRelacion.class);
	}

	@Override
	public Long getId(VOTipoRelacion objetoVO) {
		return objetoVO.getId();
	}

}
