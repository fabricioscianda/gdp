package gdp.dao.partido;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Partido;
import gdp.vomodel.VOPartido;

public class PartidoDAOImpl extends GenericDAOImpl<VOPartido, Partido> implements IPartidoDAO {

	public PartidoDAOImpl() {
		super(Partido.class, VOPartido.class);
	}

	@Override
	public Long getId(VOPartido objetoVO) {
		return objetoVO.getId();
	}

}
