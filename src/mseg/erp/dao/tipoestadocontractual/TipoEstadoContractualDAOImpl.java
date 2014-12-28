package mseg.erp.dao.tipoestadocontractual;

import mseg.erp.model.TipoEstadoContractual;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOTipoEstadoContractual;

public class TipoEstadoContractualDAOImpl extends GenericDAOImpl<VOTipoEstadoContractual, TipoEstadoContractual> implements ITipoEstadoContractualDAO {

	public TipoEstadoContractualDAOImpl() {
		super(TipoEstadoContractual.class, VOTipoEstadoContractual.class);
	}

	@Override
	public Long getId(VOTipoEstadoContractual objetoVO) {
		return objetoVO.getId();
	}

}
