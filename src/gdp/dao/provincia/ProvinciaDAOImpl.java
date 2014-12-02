package gdp.dao.provincia;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Provincia;
import gdp.vomodel.VOProvincia;

public class ProvinciaDAOImpl extends GenericDAOImpl<VOProvincia, Provincia> implements IProvinciaDAO {

	public ProvinciaDAOImpl(Class<Provincia> persistentClass, Class<VOProvincia> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOProvincia objetoVO) {
		return objetoVO.getId();
	}

}
