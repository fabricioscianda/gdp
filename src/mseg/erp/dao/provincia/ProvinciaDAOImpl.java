package mseg.erp.dao.provincia;

import mseg.erp.model.Provincia;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOProvincia;

public class ProvinciaDAOImpl extends GenericDAOImpl<VOProvincia, Provincia> implements IProvinciaDAO {

	public ProvinciaDAOImpl() {
		super(Provincia.class, VOProvincia.class);
	}

	@Override
	public Long getId(VOProvincia objetoVO) {
		return objetoVO.getId();
	}

}
