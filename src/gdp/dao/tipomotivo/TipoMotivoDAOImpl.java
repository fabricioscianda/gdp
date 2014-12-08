package gdp.dao.tipomotivo;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoMotivo;
import gdp.vomodel.VOTipoMotivo;

public class TipoMotivoDAOImpl extends GenericDAOImpl<VOTipoMotivo, TipoMotivo> implements ITipoMotivoDAO {

	public TipoMotivoDAOImpl() {
		super(TipoMotivo.class, VOTipoMotivo.class);
	}

	@Override
	public Long getId(VOTipoMotivo objetoVO) {
		return objetoVO.getId();
	}

}
