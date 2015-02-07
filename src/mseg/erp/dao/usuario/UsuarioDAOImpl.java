package mseg.erp.dao.usuario;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.exceptions.DAOException;
import mseg.erp.model.Usuario;
import mseg.erp.model.Usuario_;
import mseg.erp.spring.interceptors.RequestHandlerInterceptor;
import mseg.erp.utils.MapperUtils;
import mseg.erp.vomodel.VOUsuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsuarioDAOImpl extends GenericDAOImpl<VOUsuario, Usuario> implements IUsuarioDAO {

	private static final Logger _logger = LoggerFactory.getLogger(RequestHandlerInterceptor.class);

	public UsuarioDAOImpl() {
		super(Usuario.class, VOUsuario.class);
	}

	@Override
	public Long getId(VOUsuario objetoVO) {
		return objetoVO.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public VOUsuario login(String username, String password, EntityManager em) throws DAOException {
		
		Usuario usuario = null;
		VOUsuario usuarioVO = null;

		try {
			em = getEmfh().getEntityManager();

			CriteriaBuilder qb = em.getCriteriaBuilder();
			CriteriaQuery<Usuario> usuarioQ = qb.createQuery(Usuario.class);
			Root<Usuario> root = usuarioQ.from(Usuario.class);
			usuarioQ.select(root).
					  where(qb.and(
							  qb.equal(root.get(Usuario_.username).as(String.class), username),
							  qb.equal(root.get(Usuario_.password).as(String.class), password)));
			Query consulta = em.createQuery(usuarioQ);
			List<Usuario> lista = consulta.getResultList();

			if (!lista.isEmpty()) {
				usuario = lista.get(0);
				usuarioVO = MapperUtils.map(usuario, VOUsuario.class);
			}

			return usuarioVO;

		} catch (Exception ex) {
			_logger.error("Error durante el login del interno" + getPersistentClass().getSimpleName(), ex);
			throw new DAOException(ex);
		} finally {
			em.close();
		}
	}

}
