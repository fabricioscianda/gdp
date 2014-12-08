package gdp.dao.tipocontacto;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoContacto;
import gdp.vomodel.VOTipoContacto;

public class TipoContactoDAOImpl extends GenericDAOImpl<VOTipoContacto, TipoContacto> implements ITipoContactoDAO {

	public TipoContactoDAOImpl() {
		super(TipoContacto.class, VOTipoContacto.class);
	}

	@Override
	public Long getId(VOTipoContacto objetoVO) {
		return objetoVO.getId();
	}

}
