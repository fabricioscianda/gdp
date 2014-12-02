package gdp.dao.tiposituacionrevista;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoSituacionRevista;
import gdp.vomodel.VOTipoSituacionRevista;

public class TipoSituacionRevistaDAOImpl extends GenericDAOImpl<VOTipoSituacionRevista, TipoSituacionRevista> implements ITipoSituacionRevistaDAO {

	public TipoSituacionRevistaDAOImpl(Class<TipoSituacionRevista> persistentClass, Class<VOTipoSituacionRevista> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOTipoSituacionRevista objetoVO) {
		return objetoVO.getId();
	}

}
