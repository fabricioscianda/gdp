package gdp.dao.tipoformacion;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoFormacion;
import gdp.vomodel.VOTipoFormacion;

public class TipoFormacionDAOImpl extends GenericDAOImpl<VOTipoFormacion, TipoFormacion> implements ITipoFormacionDAO {

	public TipoFormacionDAOImpl() {
		super(TipoFormacion.class, VOTipoFormacion.class);
	}

	@Override
	public Long getId(VOTipoFormacion objetoVO) {
		return objetoVO.getId();
	}

}
