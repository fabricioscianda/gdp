package gdp.dao.formacionacademica;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.FormacionAcademica;
import gdp.vomodel.VOFormacionAcademica;

public class FormacionAcademicaDAOImpl extends GenericDAOImpl<VOFormacionAcademica, FormacionAcademica> implements IFormacionAcademicaDAO {

	public FormacionAcademicaDAOImpl(Class<FormacionAcademica> persistentClass, Class<VOFormacionAcademica> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOFormacionAcademica objetoVO) {
		return objetoVO.getId();
	}

}
