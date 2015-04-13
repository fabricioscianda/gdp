package mseg.erp.dao.docente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.dao.localidad.ILocalidadDAO;
import mseg.erp.dao.partido.IPartidoDAO;
import mseg.erp.dao.provincia.IProvinciaDAO;
import mseg.erp.exceptions.DAOException;
import mseg.erp.model.Docente;
import mseg.erp.model.Docente_;
import mseg.erp.model.InfoAdministrativa;
import mseg.erp.model.InfoAdministrativa_;
import mseg.erp.model.Localidad;
import mseg.erp.model.Localidad_;
import mseg.erp.model.Partido;
import mseg.erp.model.Partido_;
import mseg.erp.model.Persona;
import mseg.erp.model.Persona_;
import mseg.erp.model.Provincia;
import mseg.erp.utils.MapperUtils;
import mseg.erp.vomodel.VOCheckbox;
import mseg.erp.vomodel.VODocente;
import mseg.erp.vomodel.VODocenteMini;
import mseg.erp.vomodel.VOLocalidad;
import mseg.erp.vomodel.VOPartido;
import mseg.erp.vomodel.VOProvincia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DocenteDAOImpl extends GenericDAOImpl<VODocente, Docente> implements IDocenteDAO {

	private Logger _logger = LoggerFactory.getLogger(DocenteDAOImpl.class);

	@Inject
	ILocalidadDAO localidadDAO;

	@Inject
	IPartidoDAO partidoDAO;

	@Inject
	IProvinciaDAO provinciaDAO;

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
			Join<Docente, Persona> joinPersona = null;
			Join<Persona, InfoAdministrativa> joinInfoAdministrativa = null;
			Join<Persona, Localidad> joinLocalidad = null;
			Join<Localidad, Partido> joinPartido = null;
			Join<Partido, Provincia> joinProvincia = null;
			List<Predicate> predicados = new ArrayList<Predicate>();

			VOLocalidad voLocalidad = null;
			VOPartido voPartido = null;
			VOProvincia voProvincia = null;

			// join con persona
			joinPersona = from.join(Docente_.persona);
			// join con info administrativa
			if (!legajo.isEmpty() || !fechaAlta.equals(0) || antiguedad != 0 || !provinciaID.equals(0)
					|| !partidoID.equals(0) || !localidadID.equals(0) || contieneElementosChequeados(tiposPersonal)
					|| contieneElementosChequeados(tiposSituacionRevista)
					|| contieneElementosChequeados(tiposSituacionActual) || contieneElementosChequeados(tiposMotivo)
					|| contieneElementosChequeados(tiposFormacion)
					|| contieneElementosChequeados(tiposEstadoContractual)) {
				joinInfoAdministrativa = joinPersona.join(Persona_.infoAdministrativa);
				if (provinciaID != 0) {
					voProvincia = provinciaDAO.encontrar(provinciaID, em);
					joinLocalidad = joinPersona.join(Persona_.localidad);
					joinPartido = joinLocalidad.join(Localidad_.partido);
					joinProvincia = joinPartido.join(Partido_.provincia);
				}
				if (partidoID != 0) {
					voPartido = partidoDAO.encontrar(partidoID, em);
				}
				if (localidadID != 0) {
					voLocalidad = localidadDAO.encontrar(localidadID, em);
				}
			}
			cq.select(from);
			predicados = crearPredicados(cb, joinPersona, joinInfoAdministrativa, joinLocalidad, joinPartido,
					joinProvincia, edad, legajo, fechaAlta, antiguedad, voProvincia, voPartido, voLocalidad,
					tiposPersonal, tiposSituacionRevista, tiposSituacionActual, tiposMotivo, tiposFormacion,
					tiposEstadoContractual);
			cq.where(cb.and(predicados.toArray(new Predicate[predicados.size()])));
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

	/**
	 * 
	 * @param joinInfoAdministrativa
	 * @param joinPersona
	 * @param cb
	 * @param joinPartido
	 * @param joinLocalidad
	 * @param joinProvincia
	 * @param edad
	 * @param legajo
	 * @param fechaAlta
	 * @param antiguedad
	 * @param voProvincia
	 * @param voPartido
	 * @param voLocalidad
	 * @param tiposPersonal
	 * @param tiposSituacionRevista
	 * @param tiposSituacionActual
	 * @param tiposMotivo
	 * @param tiposFormacion
	 * @param tiposEstadoContractual
	 * @return
	 */
	private List<Predicate> crearPredicados(CriteriaBuilder cb, Join<Docente, Persona> joinPersona,
			Join<Persona, InfoAdministrativa> joinInfoAdministrativa, Join<Persona, Localidad> joinLocalidad,
			Join<Localidad, Partido> joinPartido, Join<Partido, Provincia> joinProvincia, Integer edad, String legajo,
			Long fechaAlta, Integer antiguedad, VOProvincia voProvincia, VOPartido voPartido, VOLocalidad voLocalidad,
			List<VOCheckbox> tiposPersonal, List<VOCheckbox> tiposSituacionRevista,
			List<VOCheckbox> tiposSituacionActual, List<VOCheckbox> tiposMotivo, List<VOCheckbox> tiposFormacion,
			List<VOCheckbox> tiposEstadoContractual) {

		List<Predicate> predicados = new ArrayList<Predicate>();

		// si se filtra por legajo deberia haber solo una coincidencia, por eso
		// el resto de los filtros van por el "else"
		// where legajo.
		if (!legajo.isEmpty()) {
			predicados.add(cb.equal(joinInfoAdministrativa.get(InfoAdministrativa_.nroLegajo), legajo));
		} else {
			// where edad
			if (edad != 0) {
				List<Long> fechasEdad = calcularRangoFechasEdadAntiguedad(edad);
				if (fechasEdad != null) {
					predicados
							.add(cb.between(joinPersona.get(Persona_.fechaNac), fechasEdad.get(0), fechasEdad.get(1)));
				}
			}
			// where antiguedad
			if (antiguedad != 0) {
				List<Long> fechasAntiguedad = calcularRangoFechasEdadAntiguedad(antiguedad);
				if (fechasAntiguedad != null) {
					predicados.add(cb.between(joinInfoAdministrativa.get(InfoAdministrativa_.fechaAlta),
							fechasAntiguedad.get(0), fechasAntiguedad.get(1)));
				}
			}
			// where provincia
			if (voProvincia != null) {
				Provincia provincia = MapperUtils.map(voProvincia, Provincia.class);
				predicados.add(cb.equal(joinPartido.get(Partido_.provincia), provincia));
				// where partido
				if (voPartido != null) {
					Partido partido = MapperUtils.map(voPartido, Partido.class);
					predicados.add(cb.equal(joinLocalidad.get(Localidad_.partido), partido));
					// where localidad
					if (voLocalidad != null) {
						Localidad localidad = MapperUtils.map(voLocalidad, Localidad.class);
						predicados.add(cb.equal(joinPersona.get(Persona_.localidad), localidad));
					}
				}
			}
			/*
			 * falta filtrar por List<VOCheckbox> tiposPersonal,
			 * List<VOCheckbox> tiposSituacionRevista, List<VOCheckbox>
			 * tiposSituacionActual, List<VOCheckbox> tiposMotivo,
			 * List<VOCheckbox> tiposFormacion, List<VOCheckbox>
			 * tiposEstadoContractual
			 */
		}

		return predicados;
	}

	/**
	 * Retorna true si alguno de los elementos de la lista de {@link VOCheckbox}
	 * esta chequeado
	 * 
	 * @param lista
	 * @return
	 */
	private boolean contieneElementosChequeados(List<VOCheckbox> lista) {
		boolean ok = false;
		Iterator<VOCheckbox> ite = lista.iterator();
		while (ite.hasNext() && !ok) {
			VOCheckbox voCheckbox = (VOCheckbox) ite.next();
			ok = voCheckbox.getChecked();
		}
		return ok;
	}

	/**
	 * 
	 * @param cant
	 * @return
	 */
	private List<Long> calcularRangoFechasEdadAntiguedad(Integer cant) {
		List<Long> fechas = null;
		if (cant != 0) {
			Calendar cal = Calendar.getInstance();
			Integer anioActual = cal.get(Calendar.YEAR);
			Integer anioMaximo = anioActual - cant;
			Integer anioMinimo = anioMaximo - 1;
			cal.set(Calendar.YEAR, anioMaximo);
			// fecha maxima obtenida
			Long fechaMaxima = cal.getTimeInMillis();
			cal.set(Calendar.YEAR, anioMinimo);
			cal.add(Calendar.DATE, 1);
			// fecha minima obtenida
			Long fechaMinima = cal.getTimeInMillis();
			// retorna la fecha
			fechas = new ArrayList<Long>(2);
			fechas.add(fechaMinima);
			fechas.add(fechaMaxima);
		}
		return fechas;
	}

}
