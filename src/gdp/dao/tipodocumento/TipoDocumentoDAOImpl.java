package gdp.dao.tipodocumento;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoDocumento;
import gdp.vomodel.VOTipoDocumento;

public class TipoDocumentoDAOImpl extends GenericDAOImpl<VOTipoDocumento, TipoDocumento> implements ITipoDocumentoDAO {

	public TipoDocumentoDAOImpl(Class<TipoDocumento> persistentClass, Class<VOTipoDocumento> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOTipoDocumento objetoVO) {
		return objetoVO.getId();
	}

}
