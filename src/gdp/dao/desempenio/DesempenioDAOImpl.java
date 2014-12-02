package gdp.dao.desempenio;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Desempenio;
import gdp.vomodel.VODesempenio;

public class DesempenioDAOImpl extends GenericDAOImpl<VODesempenio, Desempenio> implements IDesempenioDAO {

	public DesempenioDAOImpl(Class<Desempenio> persistentClass, Class<VODesempenio> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VODesempenio objetoVO) {
		return objetoVO.getId();
	}

}
