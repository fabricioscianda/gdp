package gdp.dao.instituto;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Instituto;
import gdp.vomodel.VOInstituto;

public class InstitutoDAOImpl extends GenericDAOImpl<VOInstituto, Instituto> implements IInstitutoDAO {

	public InstitutoDAOImpl(Class<Instituto> persistentClass, Class<VOInstituto> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOInstituto objetoVO) {
		return objetoVO.getId();
	}

}
