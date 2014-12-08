package gdp.dao.instituto;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Instituto;
import gdp.vomodel.VOInstituto;

public class InstitutoDAOImpl extends GenericDAOImpl<VOInstituto, Instituto> implements IInstitutoDAO {

	public InstitutoDAOImpl() {
		super(Instituto.class, VOInstituto.class);
	}

	@Override
	public Long getId(VOInstituto objetoVO) {
		return objetoVO.getId();
	}

}
