package mseg.erp.dao.instituto;

import mseg.erp.model.Instituto;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOInstituto;

public class InstitutoDAOImpl extends GenericDAOImpl<VOInstituto, Instituto> implements IInstitutoDAO {

	public InstitutoDAOImpl() {
		super(Instituto.class, VOInstituto.class);
	}

	@Override
	public Long getId(VOInstituto objetoVO) {
		return objetoVO.getId();
	}

}
