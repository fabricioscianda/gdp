package gdp.dao.provincia;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Provincia;
import gdp.vomodel.VOProvincia;

public class ProvinciaDAOImpl extends GenericDAOImpl<VOProvincia, Provincia> implements IProvinciaDAO {

	public ProvinciaDAOImpl() {
		super(Provincia.class, VOProvincia.class);
	}

	@Override
	public Long getId(VOProvincia objetoVO) {
		return objetoVO.getId();
	}

}
