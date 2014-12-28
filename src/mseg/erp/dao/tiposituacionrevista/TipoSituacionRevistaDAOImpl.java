package mseg.erp.dao.tiposituacionrevista;

import mseg.erp.model.TipoSituacionRevista;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoSituacionRevista;

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
