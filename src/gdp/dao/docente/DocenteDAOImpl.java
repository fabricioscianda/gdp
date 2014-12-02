package gdp.dao.docente;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Docente;
import gdp.vomodel.VODocente;

public class DocenteDAOImpl extends GenericDAOImpl<VODocente, Docente> implements IDocenteDAO {

	public DocenteDAOImpl(Class<Docente> persistentClass, Class<VODocente> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VODocente objetoVO) {
		return objetoVO.getId();
	}

}
