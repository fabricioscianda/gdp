package gdp.dao.usuario;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Usuario;
import gdp.vomodel.VOUsuario;

public class UsuarioDAOImpl extends GenericDAOImpl<VOUsuario, Usuario> implements IUsuarioDAO {

	public UsuarioDAOImpl(Class<Usuario> persistentClass, Class<VOUsuario> VOClass) {
		super(persistentClass, VOClass);
	}

	@Override
	public Long getId(VOUsuario objetoVO) {
		return objetoVO.getId();
	}

}
