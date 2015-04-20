package mseg.erp.dao.docente;

import java.text.Normalizer.Form;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import mseg.erp.dao.generic.GenericDAOImpl;
import mseg.erp.dao.localidad.ILocalidadDAO;
import mseg.erp.dao.partido.IPartidoDAO;
import mseg.erp.dao.provincia.IProvinciaDAO;
import mseg.erp.dao.tipoestadocontractual.ITipoEstadoContractualDAO;
import mseg.erp.dao.tipoformacion.ITipoFormacionDAO;
import mseg.erp.dao.tipomotivo.ITipoMotivoDAO;
import mseg.erp.dao.tipopersonal.ITipoPersonalDAO;
import mseg.erp.dao.tiposituacion.ITipoSituacionDAO;
import mseg.erp.dao.tiposituacionrevista.ITipoSituacionRevistaDAO;
import mseg.erp.exceptions.DAOException;
import mseg.erp.model.Docente;
import mseg.erp.model.Docente_;
import mseg.erp.model.FormacionAcademica;
import mseg.erp.model.FormacionAcademica_;
import mseg.erp.model.InfoAdministrativa;
import mseg.erp.model.InfoAdministrativa_;
import mseg.erp.model.Localidad;
import mseg.erp.model.Localidad_;
import mseg.erp.model.Partido;
import mseg.erp.model.Partido_;
import mseg.erp.model.Persona;
import mseg.erp.model.Persona_;
import mseg.erp.model.Provincia;
import mseg.erp.model.TipoEstadoContractual;
import mseg.erp.model.TipoFormacion;
import mseg.erp.model.TipoMotivo;
import mseg.erp.model.TipoPersonal;
import mseg.erp.model.TipoSituacion;
import mseg.erp.model.TipoSituacionRevista;
import mseg.erp.utils.MapperUtils;
import mseg.erp.vomodel.VOCheckbox;
import mseg.erp.vomodel.VODocente;
import mseg.erp.vomodel.VODocenteMini;
import mseg.erp.vomodel.VOLocalidad;
import mseg.erp.vomodel.VOPartido;
import mseg.erp.vomodel.VOProvincia;
import mseg.erp.vomodel.VOTipoEstadoContractual;
import mseg.erp.vomodel.VOTipoFormacion;
import mseg.erp.vomodel.VOTipoMotivo;
import mseg.erp.vomodel.VOTipoPersonal;
import mseg.erp.vomodel.VOTipoSituacion;
import mseg.erp.vomodel.VOTipoSituacionRevista;

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
	@Inject
	ITipoPersonalDAO tipoPersonalDAO;
	@Inject
	ITipoSituacionDAO tipoSituacionDAO;
	@Inject
	ITipoSituacionRevistaDAO tipoSituacionRevistaDAO;
	@Inject
	ITipoMotivoDAO tipoMotivoDAO;
	@Inject
	ITipoFormacionDAO tipoFormacionDAO;
	@Inject
	ITipoEstadoContractualDAO tipoEstadoContractualDAO;

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
			Join<Persona, FormacionAcademica> joinFormacionAcademica = null;
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
				joinFormacionAcademica = joinPersona.join(Persona_.formacionAcademica);
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
			predicados = crearPredicados(em, cb, joinPersona, joinInfoAdministrativa, joinFormacionAcademica, joinLocalidad, joinPartido,
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
	private List<Predicate> crearPredicados(EntityManager em, CriteriaBuilder cb, Join<Docente, Persona> joinPersona,
			Join<Persona, InfoAdministrativa> joinInfoAdministrativa, Join<Persona, FormacionAcademica> joinFormacionAcademica, Join<Persona, Localidad> joinLocalidad,
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
			predicadoEdad(cb, joinPersona, edad, predicados);
			predicadoAntiguedad(cb, joinInfoAdministrativa, antiguedad, predicados);
			predicadoLocalizacion(cb, joinPersona, joinLocalidad, joinPartido, voProvincia, voPartido, voLocalidad, predicados);
			predicadoTipoPersonal(em, joinInfoAdministrativa, tiposPersonal, predicados);
			predicadoTipoSituacionRevista(em, joinInfoAdministrativa, tiposSituacionRevista, predicados);
			predicadoTipoSituacion(em, joinInfoAdministrativa, tiposSituacionActual, predicados);
			predicadoTipoMotivo(em, joinInfoAdministrativa, tiposMotivo, predicados);
			predicadoTipoEstadoContractual(em, joinInfoAdministrativa, tiposEstadoContractual, predicados);
			predicadoTipoFormacion(em, joinFormacionAcademica, tiposFormacion, predicados);
		}

		return predicados;
	}

	/**
	 * 
	 * @param em
	 * @param joinInfoAdministrativa
	 * @param tiposEstadoContractual
	 * @param predicados
	 */
	private void predicadoTipoEstadoContractual(EntityManager em,
			Join<Persona, InfoAdministrativa> joinInfoAdministrativa, List<VOCheckbox> tiposEstadoContractual,
			List<Predicate> predicados) {
		VOCheckbox checkbox;
		List<TipoEstadoContractual> v_estadoContr = new ArrayList<TipoEstadoContractual>();
		for (Iterator<VOCheckbox> iterator = tiposEstadoContractual.iterator(); iterator.hasNext();) {
			checkbox = (VOCheckbox) iterator.next();
			if (checkbox.getChecked()) {
				try {
					VOTipoEstadoContractual vec = tipoEstadoContractualDAO.encontrar(checkbox.getId(), em);
					TipoEstadoContractual mo = MapperUtils.map(vec, TipoEstadoContractual.class);
					v_estadoContr.add(mo);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		}
		if (v_estadoContr.size() != 0) {
			Expression<TipoEstadoContractual> eTipoEstadoContractual = joinInfoAdministrativa
					.get(InfoAdministrativa_.tipoEstadoContractual);
			predicados.add(eTipoEstadoContractual.in(v_estadoContr));
		}
	}

	/**
	 * 
	 * @param em
	 * @param joinInfoAdministrativa
	 * @param tiposFormacion
	 * @param predicados
	 */
	private void predicadoTipoFormacion(EntityManager em, Join<Persona, FormacionAcademica> joinFormacionAcademica,
			List<VOCheckbox> tiposFormacion, List<Predicate> predicados) {
		VOCheckbox checkbox;
		List<TipoFormacion> v_formacion = new ArrayList<TipoFormacion>();
		for (Iterator<VOCheckbox> iterator = tiposFormacion.iterator(); iterator.hasNext();) {
			checkbox = (VOCheckbox) iterator.next();
			if (checkbox.getChecked()) {
				try {
					VOTipoFormacion vf = tipoFormacionDAO.encontrar(checkbox.getId(), em);
					TipoFormacion mo = MapperUtils.map(vf, TipoFormacion.class);
					v_formacion.add(mo);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		}
		if (v_formacion.size() != 0) {
			Expression<TipoFormacion> eTipoFormacion = joinFormacionAcademica.get(FormacionAcademica_.tipoFormacion);
			predicados.add(eTipoFormacion.in(v_formacion));
		}
	}

	/**
	 * 
	 * @param em
	 * @param joinInfoAdministrativa
	 * @param tiposMotivo
	 * @param predicados
	 */
	private void predicadoTipoMotivo(EntityManager em, Join<Persona, InfoAdministrativa> joinInfoAdministrativa,
			List<VOCheckbox> tiposMotivo, List<Predicate> predicados) {
		VOCheckbox checkbox;
		List<TipoMotivo> v_motivo = new ArrayList<TipoMotivo>();
		for (Iterator<VOCheckbox> iterator = tiposMotivo.iterator(); iterator.hasNext();) {
			checkbox = (VOCheckbox) iterator.next();
			if (checkbox.getChecked()) {
				try {
					VOTipoMotivo vm = tipoMotivoDAO.encontrar(checkbox.getId(), em);
					TipoMotivo mo = MapperUtils.map(vm, TipoMotivo.class);
					v_motivo.add(mo);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		}
		if (v_motivo.size() != 0) {
			Expression<TipoMotivo> eTipoMotivo = joinInfoAdministrativa.get(InfoAdministrativa_.tipoMotivo);
			predicados.add(eTipoMotivo.in(v_motivo));
		}
	}

	/**
	 * 
	 * @param em
	 * @param joinInfoAdministrativa
	 * @param tiposSituacionActual
	 * @param predicados
	 */
	private void predicadoTipoSituacion(EntityManager em, Join<Persona, InfoAdministrativa> joinInfoAdministrativa,
			List<VOCheckbox> tiposSituacionActual, List<Predicate> predicados) {
		VOCheckbox checkbox;
		List<TipoSituacion> v_situacion = new ArrayList<TipoSituacion>();
		for (Iterator<VOCheckbox> iterator = tiposSituacionActual.iterator(); iterator.hasNext();) {
			checkbox = (VOCheckbox) iterator.next();
			if (checkbox.getChecked()) {
				try {
					VOTipoSituacion vts = tipoSituacionDAO.encontrar(checkbox.getId(), em);
					TipoSituacion s = MapperUtils.map(vts, TipoSituacion.class);
					v_situacion.add(s);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		}
		if (v_situacion.size() != 0) {
			Expression<TipoSituacion> eTipoSituacion = joinInfoAdministrativa.get(InfoAdministrativa_.tipoSituacion);
			predicados.add(eTipoSituacion.in(v_situacion));
		}
	}

	/**
	 * 
	 * @param em
	 * @param joinInfoAdministrativa
	 * @param tiposSituacionRevista
	 * @param predicados
	 */
	private void predicadoTipoSituacionRevista(EntityManager em,
			Join<Persona, InfoAdministrativa> joinInfoAdministrativa, List<VOCheckbox> tiposSituacionRevista,
			List<Predicate> predicados) {
		VOCheckbox checkbox;
		List<TipoSituacionRevista> v_situacionRevistas = new ArrayList<TipoSituacionRevista>();
		for (Iterator<VOCheckbox> iterator = tiposSituacionRevista.iterator(); iterator.hasNext();) {
			checkbox = (VOCheckbox) iterator.next();
			if (checkbox.getChecked()) {
				try {
					VOTipoSituacionRevista vtsr = tipoSituacionRevistaDAO.encontrar(checkbox.getId(), em);
					TipoSituacionRevista sr = MapperUtils.map(vtsr, TipoSituacionRevista.class);
					v_situacionRevistas.add(sr);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		}
		if (v_situacionRevistas.size() != 0) {
			Expression<TipoSituacionRevista> eTipoSituacionRevista = joinInfoAdministrativa
					.get(InfoAdministrativa_.tipoSituacionRevista);
			predicados.add(eTipoSituacionRevista.in(v_situacionRevistas));
		}
	}

	/**
	 * 
	 * @param em
	 * @param joinInfoAdministrativa
	 * @param tiposPersonal
	 * @param predicados
	 */
	private void predicadoTipoPersonal(EntityManager em, Join<Persona, InfoAdministrativa> joinInfoAdministrativa,
			List<VOCheckbox> tiposPersonal, List<Predicate> predicados) {
		VOCheckbox checkbox = null;
		List<TipoPersonal> v_tiposPers = new ArrayList<TipoPersonal>();
		for (Iterator<VOCheckbox> iterator = tiposPersonal.iterator(); iterator.hasNext();) {
			checkbox = (VOCheckbox) iterator.next();
			if (checkbox.getChecked()) {
				try {
					VOTipoPersonal votp = tipoPersonalDAO.encontrar(checkbox.getId(), em);
					TipoPersonal tp = MapperUtils.map(votp, TipoPersonal.class);
					v_tiposPers.add(tp);
				} catch (DAOException e) {
					e.printStackTrace();
				}
			}
		}
		if (v_tiposPers.size() != 0) {
			Expression<TipoPersonal> eTipoPersonal = joinInfoAdministrativa.get(InfoAdministrativa_.tipoPersonal);
			predicados.add(eTipoPersonal.in(v_tiposPers));
		}
	}

	/**
	 * 
	 * @param cb
	 * @param joinPersona
	 * @param joinLocalidad
	 * @param joinPartido
	 * @param voProvincia
	 * @param voPartido
	 * @param voLocalidad
	 * @param predicados
	 */
	private void predicadoLocalizacion(CriteriaBuilder cb, Join<Docente, Persona> joinPersona,
			Join<Persona, Localidad> joinLocalidad, Join<Localidad, Partido> joinPartido, VOProvincia voProvincia,
			VOPartido voPartido, VOLocalidad voLocalidad, List<Predicate> predicados) {
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
	}

	/**
	 * 
	 * @param cb
	 * @param joinInfoAdministrativa
	 * @param antiguedad
	 * @param predicados
	 */
	private void predicadoAntiguedad(CriteriaBuilder cb, Join<Persona, InfoAdministrativa> joinInfoAdministrativa,
			Integer antiguedad, List<Predicate> predicados) {
		if (antiguedad != 0) {
			List<Long> fechasAntiguedad = calcularRangoFechasEdadAntiguedad(antiguedad);
			if (fechasAntiguedad != null) {
				predicados.add(cb.between(joinInfoAdministrativa.get(InfoAdministrativa_.fechaAlta),
						fechasAntiguedad.get(0), fechasAntiguedad.get(1)));
			}
		}
	}

	/**
	 * 
	 * @param cb
	 * @param joinPersona
	 * @param edad
	 * @param predicados
	 */
	private void predicadoEdad(CriteriaBuilder cb, Join<Docente, Persona> joinPersona, Integer edad,
			List<Predicate> predicados) {
		if (edad != 0) {
			List<Long> fechasEdad = calcularRangoFechasEdadAntiguedad(edad);
			if (fechasEdad != null) {
				predicados.add(cb.between(joinPersona.get(Persona_.fechaNac), fechasEdad.get(0), fechasEdad.get(1)));
			}
		}
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
