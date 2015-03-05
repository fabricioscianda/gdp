package mseg.erp.dao.docente;

import java.util.List;

import javax.persistence.EntityManager;

import mseg.erp.dao.generic.IGenericDAO;
import mseg.erp.exceptions.DAOException;
import mseg.erp.model.Docente;
import mseg.erp.vomodel.VODocente;
import mseg.erp.vomodel.VODocenteMini;

public interface IDocenteDAO extends IGenericDAO<VODocente, Docente> {

	public List<VODocenteMini> listarMinimo(EntityManager em) throws DAOException;
	
}
