package mseg.erp.dao.tipomotivo;

import mseg.erp.model.TipoMotivo;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoMotivo;

public class TipoMotivoDAOImpl extends GenericDAOImpl<VOTipoMotivo, TipoMotivo> implements ITipoMotivoDAO {

	public TipoMotivoDAOImpl() {
		super(TipoMotivo.class, VOTipoMotivo.class);
	}

	@Override
	public Long getId(VOTipoMotivo objetoVO) {
		return objetoVO.getId();
	}

}
