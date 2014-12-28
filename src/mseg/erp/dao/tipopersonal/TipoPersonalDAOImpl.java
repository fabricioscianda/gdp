package mseg.erp.dao.tipopersonal;

import mseg.erp.model.TipoPersonal;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoPersonal;

public class TipoPersonalDAOImpl extends GenericDAOImpl<VOTipoPersonal, TipoPersonal> implements ITipoPersonalDAO {

	public TipoPersonalDAOImpl() {
		super(TipoPersonal.class, VOTipoPersonal.class);
	}

	@Override
	public Long getId(VOTipoPersonal objetoVO) {
		return objetoVO.getId();
	}

}
