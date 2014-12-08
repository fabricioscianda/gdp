package gdp.dao.tipoestadocontractual;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.TipoEstadoContractual;
import gdp.vomodel.VOTipoEstadoContractual;

public class TipoEstadoContractualDAOImpl extends GenericDAOImpl<VOTipoEstadoContractual, TipoEstadoContractual> implements ITipoEstadoContractualDAO {

	public TipoEstadoContractualDAOImpl() {
		super(TipoEstadoContractual.class, VOTipoEstadoContractual.class);
	}

	@Override
	public Long getId(VOTipoEstadoContractual objetoVO) {
		return objetoVO.getId();
	}

}
