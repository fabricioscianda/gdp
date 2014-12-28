package mseg.erp.dao.tipoadministracion;

import mseg.erp.model.TipoAdministracion;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoAdministracion;

public class TipoAdministracionDAOImpl extends GenericDAOImpl<VOTipoAdministracion, TipoAdministracion> implements ITipoAdministracionDAO {

	public TipoAdministracionDAOImpl() {
		super(TipoAdministracion.class, VOTipoAdministracion.class);
	}

	@Override
	public Long getId(VOTipoAdministracion objetoVO) {
		return objetoVO.getId();
	}

}
