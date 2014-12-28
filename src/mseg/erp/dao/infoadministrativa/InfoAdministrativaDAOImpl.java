package mseg.erp.dao.infoadministrativa;

import mseg.erp.model.InfoAdministrativa;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOInfoAdministrativa;

public class InfoAdministrativaDAOImpl extends GenericDAOImpl<VOInfoAdministrativa, InfoAdministrativa> implements IInfoAdministrativaDAO {

	public InfoAdministrativaDAOImpl() {
		super(InfoAdministrativa.class, VOInfoAdministrativa.class);
	}

	@Override
	public Long getId(VOInfoAdministrativa objetoVO) {
		return objetoVO.getId();
	}

}
