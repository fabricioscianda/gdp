package mseg.erp.dao.desempenio;

import mseg.erp.model.Desempenio;
import mseg.erp.vomodel.VODesempenio;
import mseg.erp.dao.generic.GenericDAOImpl;

public class DesempenioDAOImpl extends GenericDAOImpl<VODesempenio, Desempenio> implements IDesempenioDAO {

	public DesempenioDAOImpl() {
		super(Desempenio.class, VODesempenio.class);
	}

	@Override
	public Long getId(VODesempenio objetoVO) {
		return objetoVO.getId();
	}

}
