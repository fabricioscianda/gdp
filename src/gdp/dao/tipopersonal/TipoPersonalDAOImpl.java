package gdp.dao.tipopersonal;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoPersonal;
import gdp.vomodel.VOTipoPersonal;

public class TipoPersonalDAOImpl extends GenericDAOImpl<VOTipoPersonal, TipoPersonal> implements ITipoPersonalDAO {

	public TipoPersonalDAOImpl() {
		super(TipoPersonal.class, VOTipoPersonal.class);
	}

	@Override
	public Long getId(VOTipoPersonal objetoVO) {
		return objetoVO.getId();
	}

}
