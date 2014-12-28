package mseg.erp.dao.tipocargo;

import mseg.erp.model.TipoCargo;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoCargo;

public class TipoCargoDAOImpl extends GenericDAOImpl<VOTipoCargo, TipoCargo> implements ITipoCargoDAO {

	public TipoCargoDAOImpl() {
		super(TipoCargo.class, VOTipoCargo.class);
	}

	@Override
	public Long getId(VOTipoCargo objetoVO) {
		return objetoVO.getId();
	}

}
