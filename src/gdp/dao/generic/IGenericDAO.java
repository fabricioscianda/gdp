package gdp.dao.generic;

import java.util.List;

import javax.persistence.EntityManager;

import gdp.exceptions.DAOException;


public interface IGenericDAO<U, T> {

	public Long getId(U objetoVO);

	public U guardar(U objetoVO,EntityManager em) throws DAOException;
	public void borrar(Long idObjeto,EntityManager em) throws DAOException;
	public U modificar(U objetoVO,EntityManager em) throws DAOException;
	
	public U encontrar(Long idObjeto) throws DAOException;
	public List<U> listar() throws DAOException;
	
}
