package gdp.dao.asignatura;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Asignatura;
import gdp.vomodel.VOAsignatura;

public class AsignaturaDAOImpl extends GenericDAOImpl<VOAsignatura, Asignatura> implements IAsignaturaDAO {

	public AsignaturaDAOImpl(Class<Asignatura> persistentClass, Class<VOAsignatura> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOAsignatura objetoVO) {
		return objetoVO.getId();
	}

}
