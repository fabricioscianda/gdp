package gdp.dao.localidad;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Localidad;
import gdp.vomodel.VOLocalidad;

public class LocalidadDAOImpl extends GenericDAOImpl<VOLocalidad, Localidad> implements ILocalidadDAO {

	public LocalidadDAOImpl() {
		super(Localidad.class, VOLocalidad.class);
	}

	@Override
	public Long getId(VOLocalidad objetoVO) {
		return objetoVO.getId();
	}

}
