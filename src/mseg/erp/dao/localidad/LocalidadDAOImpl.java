package mseg.erp.dao.localidad;

import mseg.erp.model.Localidad;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOLocalidad;

public class LocalidadDAOImpl extends GenericDAOImpl<VOLocalidad, Localidad> implements ILocalidadDAO {

	public LocalidadDAOImpl() {
		super(Localidad.class, VOLocalidad.class);
	}

	@Override
	public Long getId(VOLocalidad objetoVO) {
		return objetoVO.getId();
	}

}
