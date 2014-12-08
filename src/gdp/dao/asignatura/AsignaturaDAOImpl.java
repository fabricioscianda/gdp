package gdp.dao.asignatura;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Asignatura;
import gdp.vomodel.VOAsignatura;

public class AsignaturaDAOImpl extends GenericDAOImpl<VOAsignatura, Asignatura> implements IAsignaturaDAO {

	public AsignaturaDAOImpl() {
		super(Asignatura.class, VOAsignatura.class);
	}

	@Override
	public Long getId(VOAsignatura objetoVO) {
		return objetoVO.getId();
	}

}
