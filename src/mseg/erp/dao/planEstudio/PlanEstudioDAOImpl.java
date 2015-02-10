package mseg.erp.dao.planEstudio;

import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.model.PlanEstudio;
import mseg.erp.vomodel.VOPlanEstudio;

public class PlanEstudioDAOImpl extends GenericDAOImpl<VOPlanEstudio, PlanEstudio> implements IPlanEstudioDAO {
	
	public PlanEstudioDAOImpl() {
		super(PlanEstudio.class, VOPlanEstudio.class);
	}

	@Override
	public Long getId(VOPlanEstudio objetoVO) {
		return objetoVO.getId();
	}

}
