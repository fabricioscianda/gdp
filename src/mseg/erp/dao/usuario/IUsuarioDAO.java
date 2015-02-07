package mseg.erp.dao.usuario;

import javax.persistence.EntityManager;

import mseg.erp.dao.generic.IGenericDAO;
import mseg.erp.exceptions.DAOException;
import mseg.erp.model.Usuario;
import mseg.erp.vomodel.VOUsuario;

public interface IUsuarioDAO extends IGenericDAO<VOUsuario, Usuario> {

	public VOUsuario login(String username, String password, EntityManager em) throws DAOException;

}
