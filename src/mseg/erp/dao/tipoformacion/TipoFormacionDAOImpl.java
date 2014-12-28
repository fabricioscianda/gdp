package mseg.erp.dao.tipoformacion;

import mseg.erp.model.TipoFormacion;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoFormacion;

public class TipoFormacionDAOImpl extends GenericDAOImpl<VOTipoFormacion, TipoFormacion> implements ITipoFormacionDAO {

	public TipoFormacionDAOImpl() {
		super(TipoFormacion.class, VOTipoFormacion.class);
	}

	@Override
	public Long getId(VOTipoFormacion objetoVO) {
		return objetoVO.getId();
	}

}
