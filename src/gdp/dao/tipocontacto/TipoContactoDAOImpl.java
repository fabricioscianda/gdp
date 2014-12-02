package gdp.dao.tipocontacto;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoContacto;
import gdp.vomodel.VOTipoContacto;

public class TipoContactoDAOImpl extends GenericDAOImpl<VOTipoContacto, TipoContacto> implements ITipoContactoDAO {

	public TipoContactoDAOImpl(Class<TipoContacto> persistentClass, Class<VOTipoContacto> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOTipoContacto objetoVO) {
		return objetoVO.getId();
	}

}
