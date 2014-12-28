package mseg.erp.dao.generic;

import java.util.List;

import javax.persistence.EntityManager;

import mseg.erp.exceptions.DAOException;

public interface IGenericDAO<U, T> {

	public Long getId(U objetoVO);

	public U guardar(U objetoVO, EntityManager em) throws DAOException;

	public void borrar(Long idObjeto, EntityManager em) throws DAOException;

	public U modificar(U objetoVO, EntityManager em) throws DAOException;

	public U encontrar(Long idObjeto, EntityManager em) throws DAOException;

	public List<U> listar(EntityManager em) throws DAOException;

}
