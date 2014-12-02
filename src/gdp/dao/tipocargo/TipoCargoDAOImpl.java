package gdp.dao.tipocargo;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoCargo;
import gdp.vomodel.VOTipoCargo;

public class TipoCargoDAOImpl extends GenericDAOImpl<VOTipoCargo, TipoCargo> implements ITipoCargoDAO {

	public TipoCargoDAOImpl(Class<TipoCargo> persistentClass, Class<VOTipoCargo> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOTipoCargo objetoVO) {
		return objetoVO.getId();
	}

}
