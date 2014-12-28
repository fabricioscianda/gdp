package mseg.erp.dao.generic;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import mseg.erp.exceptions.DAOException;
import mseg.erp.spring.bootstrap.EntityManagerFactoryHolder;
import mseg.erp.utils.MapperUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * U = objeto VO T = entidad
 * **/
public class GenericDAOImpl<U, T> implements IGenericDAO<U, T> {

	protected Class<T> persistentClass;
	protected Class<U> VOClass;

	@Inject
	private EntityManagerFactoryHolder emfh;

	private Logger _logger = LoggerFactory.getLogger(GenericDAOImpl.class);

	public GenericDAOImpl(Class<T> persistentClass, Class<U> VOClass) {
		this.persistentClass = persistentClass;
		this.VOClass = VOClass;
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	public Class<U> getVOClass() {
		return VOClass;
	}

	public void setVOClass(Class<U> vOClass) {
		VOClass = vOClass;
	}

	@Override
	public U guardar(U objetoVO, EntityManager em) throws DAOException {

		U objetoU = null;

		try {
			_logger.info("Intentando persistir " + getPersistentClass().getSimpleName());

			T objeto = MapperUtils.map(objetoVO, persistentClass);

			objeto = em.merge(objeto);
			objetoU = MapperUtils.map(objeto, VOClass);

			_logger.info(getPersistentClass().getSimpleName() + " persistido correctamente.");
		} catch (Exception ex) {
			_logger.error("Error persistiendo " + getPersistentClass().getSimpleName(), ex);
			throw new DAOException(ex);
		}

		return objetoU;

	}

	@Override
	public void borrar(Long idObjeto, EntityManager em) throws DAOException {
		try {
			_logger.info("Intentando eliminar " + getPersistentClass().getSimpleName());

			T objeto = em.find(persistentClass, idObjeto);
			em.remove(objeto);

			_logger.info(getPersistentClass().getSimpleName() + " eliminado correctamente.");
		} catch (Exception ex) {
			_logger.error("Error eliminando " + getPersistentClass().getSimpleName(), ex);
			throw new DAOException(ex);
		}
	}

	@Override
	public U modificar(U objetoVO, EntityManager em) throws DAOException {
		try {
			_logger.info("Intentando modificar " + getPersistentClass().getSimpleName());

			T objeto = em.find(persistentClass, this.getId(objetoVO));

			objeto = MapperUtils.map(objetoVO, this.getPersistentClass());

			objeto = em.merge(objeto);

			objetoVO = MapperUtils.map(objeto, VOClass);

			_logger.info(getPersistentClass().getSimpleName() + " modificado correctamente.");
		} catch (Exception ex) {
			_logger.error("Error modificando " + getPersistentClass().getSimpleName(), ex);
			throw new DAOException(ex);
		}
		return objetoVO;
	}

	@Override
	public U encontrar(Long idObjeto, EntityManager em) throws DAOException {
		U objetoVO = null;
		try {
			_logger.info("Intentando encontrar " + getPersistentClass().getSimpleName());

			em = emfh.getEntityManager();
			T objeto = em.find(persistentClass, idObjeto);

			if (objeto != null) {
				objetoVO = MapperUtils.map(objeto, VOClass);
				_logger.info(getPersistentClass().getSimpleName() + " encontrado correctamente.");
			}

		} catch (Exception ex) {
			_logger.error("Error buscando " + getPersistentClass().getSimpleName(), ex);
			throw new DAOException(ex);
		} finally {
			em.close();
		}
		return objetoVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<U> listar(EntityManager em) throws DAOException {
		List<U> objetos = new ArrayList<U>();
		List<T> lista = new ArrayList<T>();

		try {
			_logger.info("Intentando listar " + persistentClass.getSimpleName());

			em = emfh.getEntityManager();
			Query consulta = em.createQuery("SELECT o FROM " + getPersistentClass().getSimpleName() + " as o");
			lista = consulta.getResultList();

			objetos = MapperUtils.map(lista, this.getVOClass());
			_logger.info(getPersistentClass().getSimpleName() + " listado correctamente.");
		} catch (Exception ex) {
			_logger.error("Error listando " + persistentClass.getSimpleName(), ex);
			throw new DAOException(ex);
		} finally {
			em.close();
		}
		return objetos;
	}

	@Override
	public Long getId(U objetoVO) {
		return null;
	}

	public EntityManagerFactoryHolder getEmfh() {
		return emfh;
	}

	public void setEmfh(EntityManagerFactoryHolder emfh) {
		this.emfh = emfh;
	}

}
