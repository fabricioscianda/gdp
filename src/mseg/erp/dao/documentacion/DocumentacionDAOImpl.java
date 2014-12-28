package mseg.erp.dao.documentacion;

import mseg.erp.model.Documentacion;
import mseg.erp.vomodel.VODocumentacion;
import mseg.erp.dao.generic.GenericDAOImpl;

public class DocumentacionDAOImpl extends GenericDAOImpl<VODocumentacion, Documentacion> implements IDocumentacionDAO {

	public DocumentacionDAOImpl() {
		super(Documentacion.class, VODocumentacion.class);
	}

	@Override
	public Long getId(VODocumentacion objetoVO) {
		return objetoVO.getId();
	}

}
