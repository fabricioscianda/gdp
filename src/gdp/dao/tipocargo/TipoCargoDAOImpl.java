package gdp.dao.tipocargo;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoCargo;
import gdp.vomodel.VOTipoCargo;

public class TipoCargoDAOImpl extends GenericDAOImpl<VOTipoCargo, TipoCargo> implements ITipoCargoDAO {

	public TipoCargoDAOImpl() {
		super(TipoCargo.class, VOTipoCargo.class);
	}

	@Override
	public Long getId(VOTipoCargo objetoVO) {
		return objetoVO.getId();
	}

}
