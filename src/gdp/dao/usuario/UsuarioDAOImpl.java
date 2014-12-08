package gdp.dao.usuario;

import gdp.dao.generic.GenericDAOImpl;
import gdp.model.Usuario;
import gdp.vomodel.VOUsuario;

public class UsuarioDAOImpl extends GenericDAOImpl<VOUsuario, Usuario> implements IUsuarioDAO {

	public UsuarioDAOImpl() {
		super(Usuario.class, VOUsuario.class);
	}

	@Override
	public Long getId(VOUsuario objetoVO) {
		return objetoVO.getId();
	}

}
