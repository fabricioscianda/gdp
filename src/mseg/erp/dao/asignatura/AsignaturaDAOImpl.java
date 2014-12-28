package mseg.erp.dao.asignatura;

import mseg.erp.model.Asignatura;
import mseg.erp.vomodel.VOAsignatura;
import mseg.erp.dao.generic.GenericDAOImpl;

public class AsignaturaDAOImpl extends GenericDAOImpl<VOAsignatura, Asignatura> implements IAsignaturaDAO {

	public AsignaturaDAOImpl() {
		super(Asignatura.class, VOAsignatura.class);
	}

	@Override
	public Long getId(VOAsignatura objetoVO) {
		return objetoVO.getId();
	}

}
