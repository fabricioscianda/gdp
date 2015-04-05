package mseg.erp.dao.docente;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Fetch;
import javax.persistence.criteria.Root;

import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.exceptions.DAOException;
import mseg.erp.model.Docente;
import mseg.erp.model.Docente_;
import mseg.erp.model.InfoAdministrativa;
import mseg.erp.model.Persona;
import mseg.erp.utils.MapperUtils;
import mseg.erp.vomodel.VOCheckbox;
import mseg.erp.vomodel.VODocente;
import mseg.erp.vomodel.VODocenteMini;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocenteDAOImpl extends GenericDAOImpl<VODocente, Docente> implements IDocenteDAO {

	private Logger _logger = LoggerFactory.getLogger(DocenteDAOImpl.class);

	public DocenteDAOImpl() {
		super(Docente.class, VODocente.class);
	}

	@Override
	public Long getId(VODocente objetoVO) {
		return objetoVO.getId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VODocenteMini> listarMinimo(EntityManager em) throws DAOException {
		List<VODocenteMini> voDocentesMini = new ArrayList<VODocenteMini>();
		List<Docente> docentes = new ArrayList<Docente>();

		try {
			_logger.info("Intentando listar " + persistentClass.getSimpleName());

			Query consulta = em.createQuery("SELECT d FROM " + Docente.class.getSimpleName() + " as d");
			docentes = consulta.getResultList();

			for (Iterator<Docente> iterator = docentes.iterator(); iterator.hasNext();) {
				Docente docente = (Docente) iterator.next();
				VODocenteMini docenteMini = new VODocenteMini();
				docenteMini.setId(docente.getId());
				docenteMini.setNombre(docente.getPersona().getNombre());
				docenteMini.setApellido(docente.getPersona().getApellido());
				InfoAdministrativa infoAdministrativa = docente.getPersona().getInfoAdministrativa();
				if (infoAdministrativa != null) {
					if (infoAdministrativa.getTipoEstadoContractual() != null) {
						docenteMini.setEstadoContractual(docente.getPersona().getInfoAdministrativa()
								.getTipoEstadoContractual().getNombre());
					}
					if (infoAdministrativa.getFechaAlta() != null) {
						docenteMini.setFechaAlta(docente.getPersona().getInfoAdministrativa().getFechaAlta());
					}
					if (infoAdministrativa.getTipoMotivo() != null) {
						docenteMini.setMotivo(docente.getPersona().getInfoAdministrativa().getTipoMotivo().getNombre());
					}
					if (infoAdministrativa.getNroLegajo() != null) {
						docenteMini.setNroLegajo(docente.getPersona().getInfoAdministrativa().getNroLegajo());
					}
					if (infoAdministrativa.getTipoPersonal() != null) {
						docenteMini.setPersonal(docente.getPersona().getInfoAdministrativa().getTipoPersonal()
								.getNombre());
					}
					if (infoAdministrativa.getTipoSituacion() != null) {
						docenteMini.setSituacion(docente.getPersona().getInfoAdministrativa().getTipoSituacion()
								.getNombre());
					}
					if (infoAdministrativa.getTipoSituacionRevista() != null) {
						docenteMini.setSituacionRevista(docente.getPersona().getInfoAdministrativa()
								.getTipoSituacionRevista().getNombre());
					}
				}
				voDocentesMini.add(docenteMini);
			}

			_logger.info(Docente.class.getSimpleName() + " listado correctamente.");
		} catch (Exception ex) {
			_logger.error("Error listando " + persistentClass.getSimpleName(), ex);
			throw new DAOException(ex);
		}
		return voDocentesMini;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<VODocente> encontrarFiltrado(EntityManager em, Integer edad, String legajo, Long fechaAlta,
			Integer antiguedad, Long provinciaID, Long partidoID, Long localidadID, List<VOCheckbox> tiposPersonal,
			List<VOCheckbox> tiposSituacionRevista, List<VOCheckbox> tiposSituacionActual,
			List<VOCheckbox> tiposMotivo, List<VOCheckbox> tiposFormacion, List<VOCheckbox> tiposEstadoContractual)
			throws DAOException {

		List<Docente> docentes = new ArrayList<Docente>();
		List<VODocente> voDocentes = new ArrayList<VODocente>();
		
		try {
			_logger.info("Intentando filtrar " + persistentClass.getSimpleName());

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Docente> cq = cb.createQuery(Docente.class);
			Root<Docente> from = cq.from(Docente.class);
			Fetch<Docente, Persona> persona = from.fetch(Docente_.persona);
			cq.select(from);
			Query query = em.createQuery(cq);
			docentes = query.getResultList();
			voDocentes = MapperUtils.map(docentes, VODocente.class);
			
			_logger.info("Listado filtrado " + persistentClass.getSimpleName());
			
		} catch (Exception ex) {
			_logger.error("Error filtrando " + persistentClass.getSimpleName(), ex);
			throw new DAOException(ex);
		}

		return voDocentes;

	}

}
