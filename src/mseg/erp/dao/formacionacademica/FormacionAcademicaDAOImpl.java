package mseg.erp.dao.formacionacademica;

import mseg.erp.model.FormacionAcademica;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOFormacionAcademica;

public class FormacionAcademicaDAOImpl extends GenericDAOImpl<VOFormacionAcademica, FormacionAcademica> implements IFormacionAcademicaDAO {

	public FormacionAcademicaDAOImpl() {
		super(FormacionAcademica.class, VOFormacionAcademica.class);
	}

	@Override
	public Long getId(VOFormacionAcademica objetoVO) {
		return objetoVO.getId();
	}

}
