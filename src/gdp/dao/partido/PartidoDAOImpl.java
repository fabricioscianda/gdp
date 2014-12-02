package gdp.dao.partido;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Partido;
import gdp.vomodel.VOPartido;

public class PartidoDAOImpl extends GenericDAOImpl<VOPartido, Partido> implements IPartidoDAO {

	public PartidoDAOImpl(Class<Partido> persistentClass, Class<VOPartido> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOPartido objetoVO) {
		return objetoVO.getId();
	}

}
