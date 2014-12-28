package mseg.erp.dao.tipodocumento;

import mseg.erp.model.TipoDocumento;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoDocumento;

public class TipoDocumentoDAOImpl extends GenericDAOImpl<VOTipoDocumento, TipoDocumento> implements ITipoDocumentoDAO {

	public TipoDocumentoDAOImpl() {
		super(TipoDocumento.class, VOTipoDocumento.class);
	}

	@Override
	public Long getId(VOTipoDocumento objetoVO) {
		return objetoVO.getId();
	}

}
