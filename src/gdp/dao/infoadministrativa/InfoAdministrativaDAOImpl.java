package gdp.dao.infoadministrativa;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.InfoAdministrativa;
import gdp.vomodel.VOInfoAdministrativa;

public class InfoAdministrativaDAOImpl extends GenericDAOImpl<VOInfoAdministrativa, InfoAdministrativa> implements IInfoAdministrativaDAO {

	public InfoAdministrativaDAOImpl() {
		super(InfoAdministrativa.class, VOInfoAdministrativa.class);
	}

	@Override
	public Long getId(VOInfoAdministrativa objetoVO) {
		return objetoVO.getId();
	}

}
