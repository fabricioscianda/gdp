package gdp.dao.tiposituacionrevista;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoSituacionRevista;
import gdp.vomodel.VOTipoSituacionRevista;

public class TipoSituacionRevistaDAOImpl extends GenericDAOImpl<VOTipoSituacionRevista, TipoSituacionRevista> implements
		ITipoSituacionRevistaDAO {

	public TipoSituacionRevistaDAOImpl() {
		super(TipoSituacionRevista.class, VOTipoSituacionRevista.class);
	}

	@Override
	public Long getId(VOTipoSituacionRevista objetoVO) {
		return objetoVO.getId();
	}

}
