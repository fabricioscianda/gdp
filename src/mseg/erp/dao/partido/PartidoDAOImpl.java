package mseg.erp.dao.partido;

import mseg.erp.model.Partido;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOPartido;

public class PartidoDAOImpl extends GenericDAOImpl<VOPartido, Partido> implements IPartidoDAO {

	public PartidoDAOImpl() {
		super(Partido.class, VOPartido.class);
	}

	@Override
	public Long getId(VOPartido objetoVO) {
		return objetoVO.getId();
	}

}
