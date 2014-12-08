package gdp.dao.documentacion;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Documentacion;
import gdp.vomodel.VODocumentacion;

public class DocumentacionDAOImpl extends GenericDAOImpl<VODocumentacion, Documentacion> implements IDocumentacionDAO {

	public DocumentacionDAOImpl() {
		super(Documentacion.class, VODocumentacion.class);
	}

	@Override
	public Long getId(VODocumentacion objetoVO) {
		return objetoVO.getId();
	}

}
