package mseg.erp.dao.usuario;

import mseg.erp.model.Usuario;
import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.vomodel.VOUsuario;

public class UsuarioDAOImpl extends GenericDAOImpl<VOUsuario, Usuario> implements IUsuarioDAO {

	public UsuarioDAOImpl() {
		super(Usuario.class, VOUsuario.class);
	}

	@Override
	public Long getId(VOUsuario objetoVO) {
		return objetoVO.getId();
	}

}
