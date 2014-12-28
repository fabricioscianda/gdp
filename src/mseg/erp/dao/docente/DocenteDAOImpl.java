package mseg.erp.dao.docente;

import mseg.erp.model.Docente;
import mseg.erp.vomodel.VODocente;
import mseg.erp.dao.generic.GenericDAOImpl;

public class DocenteDAOImpl extends GenericDAOImpl<VODocente, Docente> implements IDocenteDAO {

	public DocenteDAOImpl() {
		super(Docente.class, VODocente.class);
	}

	@Override
	public Long getId(VODocente objetoVO) {
		return objetoVO.getId();
	}

}
