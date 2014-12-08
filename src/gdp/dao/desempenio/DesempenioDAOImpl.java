package gdp.dao.desempenio;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Desempenio;
import gdp.vomodel.VODesempenio;

public class DesempenioDAOImpl extends GenericDAOImpl<VODesempenio, Desempenio> implements IDesempenioDAO {

	public DesempenioDAOImpl() {
		super(Desempenio.class, VODesempenio.class);
	}

	@Override
	public Long getId(VODesempenio objetoVO) {
		return objetoVO.getId();
	}

}
