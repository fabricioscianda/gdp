package mseg.erp.dao.tipocontacto;

import mseg.erp.model.TipoContacto;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoContacto;

public class TipoContactoDAOImpl extends GenericDAOImpl<VOTipoContacto, TipoContacto> implements ITipoContactoDAO {

	public TipoContactoDAOImpl() {
		super(TipoContacto.class, VOTipoContacto.class);
	}

	@Override
	public Long getId(VOTipoContacto objetoVO) {
		return objetoVO.getId();
	}

}
