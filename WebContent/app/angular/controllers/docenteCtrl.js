'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Docente */
msegErpControllers.controller('DocenteCtrl', [
						'$scope',
						'$filter',
						'DocenteService',
						'TipoDocumentoService',
						'TipoContactoService',
						'DomicilioService',
						'LocalidadService',
						'TipoFormacionService',
						'TipoCargoService',
						'TipoAdministracionService',
						'TipoRelacionService',
						'TipoEstadoFormacionService',
						'TipoPersonalService',
						'TipoEstadoContractualService',
						'TipoSituacionService',
						'TipoSituacionRevistaService',
						'TipoMotivoService',
						function($scope, $filter, DocenteService,
								TipoDocumentoService, TipoContactoService,
								DomicilioService, LocalidadService,
								TipoFormacionService, TipoCargoService,
								TipoAdministracionService, TipoRelacionService,
								TipoEstadoFormacionService, TipoPersonalService,
								TipoEstadoContractualService, TipoSituacionService,
								TipoSituacionRevistaService, TipoMotivoService) {

			$scope.modulo = 'Docentes';
			$scope.nombreForm = 'Docente';
			$scope.urlModulo = 'docentes';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.docente = {};
			$scope.docentes = {};
			
			$scope.id = null;
			$scope.nombre = null;
			$scope.apellido = null;
			$scope.tiposDoc = {};
			$scope.tipoDocSel = {};
			$scope.fechaNac = null;
			$scope.cuilTipoSel = {};
			$scope.numeroDoc = null;
			$scope.numeroDocCuil = null;
			$scope.cuilValidadorSel = {};

			$scope.tiposCuil = [20,23,24,27,30,33];
			$scope.validadoresCuil = [0,1,2,3,4,5,6,7,8,9];
			
			$scope.nuevoMedio = {};
			$scope.nuevosMediosContacto = new Array();
			$scope.mediosContacto = {};
			$scope.medioSel = {};
			
			$scope.nuevaFormacion = {};
			$scope.nuevasFormacionesAcademicas = new Array();
			$scope.tiposFormacion = {};
			$scope.tipoFormacionSel = {};
			
			$scope.tiposPersonal = {};
			$scope.tipoPersonalSel = {};
			
			$scope.tiposMotivo = {};
			$scope.tipoMotivoSel = {};
			
			$scope.tiposSituacionActual = {};
			$scope.tipoSituacionActualSel = {};
			
			$scope.tiposSituacionRevista = {};
			$scope.tipoSituacionRevistaSel = {};
			
			$scope.tipoEstadoFormacionSel = {};
			$scope.tiposEstadoFormacion = {};
			
			$scope.tipoEstadoContractualSel = {};
			$scope.tiposEstadoContractual = {};
			
			$scope.nuevoEmpleo = {};
			$scope.nuevosEmpleos = new Array();
			$scope.tiposCargo = {};
			$scope.tipoCargoSel = {};
			$scope.tiposAdmin = {};
			$scope.tipoAdminSel = {};
			$scope.tiposRelacion = {};
			$scope.tipoRelacionSel = {};
			
			$scope.nuevoDomicilio = {};
			$scope.nuevosDomicilios = new Array();
			$scope.domicilios = {};
			$scope.localidades = {};
			$scope.localidadSel = {};

			$scope.colapsarFormulario = true;
			
			$scope.obj = {};
			
			var orderBy = $filter('orderBy');
			
			/*
			 * datetime picker
			 */
			$scope.today = function() {
				$scope.fechaNac = new Date();
			};
			
			$scope.clear = function() {
				$scope.fechaNac = null;
			};

			// Disable weekend selection
			$scope.disabled = function(date, mode) {
				return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
			};

			$scope.toggleMax = function() {
				$scope.maxDate = $scope.maxDate ? null : new Date();
			};
			
			$scope.toggleMax();

			$scope.open = function($event) {
				$event.preventDefault();
				$event.stopPropagation();
				$scope.opened = true;
			};
			
			$scope.openAnio = function($event) {
				$event.preventDefault();
				$event.stopPropagation();
				$scope.openedAnio = true;
			};
			
			$scope.openFechaAlta = function($event) {
				$event.preventDefault();
				$event.stopPropagation();
				$scope.openedFechaAlta = true;
			};
			
			$scope.openFechaMotivo = function($event) {
				$event.preventDefault();
				$event.stopPropagation();
				$scope.openedFechaMotivo = true;
			};

			$scope.dateOptions = {
				formatYear : 'yy',
				startingDay : 1
			};
			
			$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate', 'yyyy' ];
			
			$scope.format = $scope.formats[2];
			$scope.formatYearOnly = $scope.formats[4];
			
			/*
			 * acciones sobre sub-forms 
			 */
			$scope.addMedio = function() {
				$scope.nuevoMedio.tipoContacto = $scope.medioSel;
				$scope.nuevosMediosContacto.push($scope.nuevoMedio);
				$scope.nuevoMedio = {};
			}
			
			$scope.removeMedio = function(medio) {
				$scope.nuevosMediosContacto.splice($scope.nuevosMediosContacto.indexOf(medio),1);
				$scope.nuevoMedio = {};
			}
			
			$scope.addDomicilio = function() {
				$scope.nuevoDomicilio.localidad = $scope.localidadSel;
				if ($scope.nuevoDomicilio.actual) {
					for (var i = 0; i < $scope.nuevosDomicilios.length; i++) {
						$scope.nuevosDomicilios[i].actual = false;
					}
				}
				$scope.nuevosDomicilios.push($scope.nuevoDomicilio);
				$scope.nuevoDomicilio = {};
				$scope.localidadSel = null;
			}
			
			$scope.removeDomicilio = function(domicilio) {
				$scope.nuevosDomicilios.splice($scope.nuevosDomicilios.indexOf(domicilio),1);
				$scope.nuevoDomicilio = {};
			}
			
			$scope.addFormacion = function() {
				$scope.nuevaFormacion.tipoFormacion = $scope.tipoFormacionSel;
				$scope.nuevaFormacion.tipoEstadoFormacion = $scope.tipoEstadoFormacionSel;
				$scope.nuevaFormacion.anio = $scope.nuevaFormacion.anio.getTime(); 
				$scope.nuevasFormacionesAcademicas.push($scope.nuevaFormacion);
				$scope.nuevaFormacion = {};
				$scope.nuevaFormacion.anio = null;
				$scope.tipoFormacionSel = null;
				$scope.tipoEstadoFormacionSel = null;
			}
			
			$scope.removeFormacion = function(formacion) {
				$scope.nuevasFormacionesAcademicas.splice($scope.nuevasFormacionesAcademicas.indexOf(formacion),1);
				$scope.nuevaFormacion = {};
			}
			
			$scope.addEmpleo = function() {
				$scope.nuevoEmpleo.tipoAdmin = $scope.tipoAdminSel;
				$scope.nuevoEmpleo.tipoCargo = $scope.tipoCargoSel;
				$scope.nuevoEmpleo.tipoRelacion = $scope.tipoRelacionSel;
				$scope.nuevosEmpleos.push($scope.nuevoEmpleo);
				$scope.nuevoEmpleo = {};
				$scope.tipoAdminSel = null;
				$scope.tipoCargoSel = null;
				$scope.tipoRelacionSel = null;
			}
			
			$scope.removeEmpleo = function(empleo) {
				$scope.nuevosEmpleos.splice($scope.nuevosEmpleos.indexOf(empleo),1);
				$scope.nuevoEmpleo = {};
			}

			$scope.listarMediosContacto = function() {
				TipoContactoService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.mediosContacto = angular.fromJson(response.data);
						$scope.mediosContacto = orderBy($scope.mediosContacto, 'tipoContacto');
					} else {
						$scope.msgError = 'No se pudieron obtener los Medios de Contacto';
						console.log('No se pudieron obtener los Medios de Contacto');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposPersonal = function() {
				TipoPersonalService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposPersonal = angular.fromJson(response.data);
						$scope.tiposPersonal = orderBy($scope.tiposPersonal, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Tipos de Personal';
						console.log('No se pudieron obtener los Tipos de Personal');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposMotivo = function() {
				TipoMotivoService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposMotivo = angular.fromJson(response.data);
						$scope.tiposMotivo = orderBy($scope.tiposMotivo, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Tipos de Motivo';
						console.log('No se pudieron obtener los Tipos de Motivo');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposSituacionActual = function() {
				TipoSituacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposSituacionActual = angular.fromJson(response.data);
						$scope.tiposSituacionActual = orderBy($scope.tiposSituacionActual, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Tipos de Situacion';
						console.log('No se pudieron obtener los Tipos de Situacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposSituacionRevista = function() {
				TipoSituacionRevistaService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposSituacionRevista = angular.fromJson(response.data);
						$scope.tiposSituacionRevista = orderBy($scope.tiposSituacionRevista, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Tipos de Situacion de Revista';
						console.log('No se pudieron obtener los Tipos de Situacion de Revista');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposFormacion = function() {
				TipoFormacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposFormacion = angular.fromJson(response.data);
						$scope.tiposFormacion = orderBy($scope.tiposFormacion, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Tipos de Formacion';
						console.log('No se pudieron obtener los Tipos de Formacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposEstadoFormacion = function() {
				TipoEstadoFormacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposEstadoFormacion = angular.fromJson(response.data);
						$scope.tiposEstadoFormacion = orderBy($scope.tiposEstadoFormacion, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Estados de Formacion';
						console.log('No se pudieron obtener los Estados de Formacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposEstadoContractual = function() {
				TipoEstadoContractualService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposEstadoContractual = angular.fromJson(response.data);
						$scope.tiposEstadoContractual = orderBy($scope.tiposEstadoContractual, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Estados Contractuales';
						console.log('No se pudieron obtener los Estados Contractuales');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposCargo = function() {
				TipoCargoService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposCargo = angular.fromJson(response.data);
						$scope.tiposCargo = orderBy($scope.tiposCargo, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Tipos de Cargo';
						console.log('No se pudieron obtener los Tipos de Cargo');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposAdmin = function() {
				TipoAdministracionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposAdmin = angular.fromJson(response.data);
						$scope.tiposAdmin = orderBy($scope.tiposAdmin, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Tipos de Administracion';
						console.log('No se pudieron obtener los Tipos de Administracion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposRelacion = function() {
				TipoRelacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposRelacion = angular.fromJson(response.data);
						$scope.tiposRelacion = orderBy($scope.tiposRelacion, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los Tipos de Relacion';
						console.log('No se pudieron obtener los Tipos de Relacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarLocalidades = function() {
				LocalidadService.listar({}, 
					function (response){
						$scope.success = response.ok;
						if (response.ok) {
							$scope.localidades = angular.fromJson(response.data);
							$scope.localidades = orderBy($scope.localidades, ['nombre']);
						} else {
							$scope.msgError = 'No se pudieron obtener las Localidades';
							console.log('No se pudieron obtener las Localidades');
							$('#message-modal').modal('show');
						}
					},
					function(error) {
						alert(error);
					})
			};
			
			$scope.listarTiposDoc = function() {
				TipoDocumentoService.listar({},
					function (response){
						$scope.success = response.ok;
						if (response.ok) {
							 $scope.tiposDoc = angular.fromJson(response.data);
							 $scope.tipoDocSel = $scope.tiposDoc[-1];
							 $scope.tiposDoc = orderBy($scope.tiposDoc, 'nombre');
						} else {
							$scope.msgError = 'No se pudieron obtener los Tipos de Documento';
							console.log('No se pudieron obtener los Tipos de Documento');
							$('#message-modal').modal('show');
						}
					},
					function(error) {
						alert(error);
					})
			};
			
			$scope.guardar = function() {
				$scope.infoAdministrativa.fechaAlta = $scope.infoAdministrativa.fechaAlta.getTime(); 
				$scope.infoAdministrativa.fechaMotivo = $scope.infoAdministrativa.fechaMotivo.getTime(); 
				$scope.infoAdministrativa.tipoPersonal = $scope.tipoPersonalSel;
				$scope.infoAdministrativa.tipoEstadoContractual = $scope.tipoEstadoContractualSel;
				$scope.infoAdministrativa.tipoSituacion = $scope.tipoSituacionActualSel;
				$scope.infoAdministrativa.tipoSituacionRevista = $scope.tipoSituacionRevistaSel;
				$scope.infoAdministrativa.tipoMotivo = $scope.tipoMotivoSel;
				if ($scope.nombre!=undefined) {
					DocenteService.guardar({
						'id' : $scope.id,
						'nombre' : $scope.nombre,
						'apellido' : $scope.apellido,
						'fechaNac' : $scope.fechaNac.getTime(),
						'id_tipoDoc' : $scope.tipoDocSel,
						'numeroDoc' : $scope.numeroDoc,
						'tipoCuil' : $scope.cuilTipoSel,
						'numeroDocCuil' : $scope.numeroDocCuil,
						'validadorCuil' : $scope.cuilValidadorSel,
						'mediosContacto' : $scope.nuevosMediosContacto,
						'domicilios' : $scope.nuevosDomicilios,
						'empleos' : $scope.nuevosEmpleos,
						'formacionAcademica' : $scope.nuevasFormacionesAcademicas,
						'infoAdministrativa' : $scope.infoAdministrativa,
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = $scope.apellido + ' ' + $scope.nombre + ', Guardado.';
							$scope.limpiar();
							$scope.listar();
							$scope.colapsarFormulario = true;
						} else {
							$scope.msgError = 'No se pudo guardar.';
							console.log('No se pudo guardar el elemento.');
						}
						$('#message-modal').modal('show');
					}, function(error) {
						alert(error);
					})
				} else {
					$scope.success = false;
					$scope.msgError = 'El nombre puede ser vacio.';
					$('#message-modal').modal('show');
				}
			};

			$scope.listar = function() {
				DocenteService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.docentes = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los docentes.';
						$('#message-modal').modal('show');
					};
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(docente) {
				DocenteService.borrar({
					'id' : docente.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = docente.persona.nombre + " " + docente.persona.apellido + ", Borrado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo borrar el elemento.";
					}
					$scope.textoConfirm = null;
					$('#confirm-modal').modal('hide');
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.editarElemento = function(docente) {
				$scope.id = docente.id;
				$scope.nombre = docente.persona.nombre;
				$scope.apellido = docente.persona.apellido;
				$scope.fechaNac = new Date(docente.persona.fechaNac);
				$scope.numeroDoc = docente.persona.numeroDoc;
				$scope.numeroDocCuil = docente.persona.cuil.substring(2,10);
				var i = $scope.indiceDe($scope.tiposDoc, docente.persona.tipoDoc.id, 'id');
				if (i!=-1) {
					$scope.tipoDocSel = $scope.tiposDoc[i].id;
				} else {
					$scope.msgError = 'Error buscando el tipo de documento del docente a editar, en el listado.';
					$('#message-modal').modal('show');
					$scope.success = false;
				}
				$scope.cuilTipoSel = parseInt(docente.persona.cuil.substring(0,2));
				$scope.cuilValidadorSel = parseInt(docente.persona.cuil.substring(10,11));
				$scope.nuevosDomicilios = docente.persona.domicilios;
				$scope.nuevosEmpleos = docente.persona.empleos;
				$scope.nuevosMediosContacto = docente.persona.mediosContacto;
				$scope.nuevasFormacionesAcademicas = docente.persona.formacionAcademica;
				$scope.infoAdministrativa = docente.persona.infoAdministrativa;
				
				var i = $scope.indiceDe($scope.tiposPersonal, docente.persona.infoAdministrativa.tipoPersonal.id, 'id');
				if (i!=-1) {
					$scope.tipoPersonalSel = $scope.tiposPersonal[i];
				} else {
					$scope.msgError = 'Error buscando el Tipo de Personal a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				$scope.colapsarFormulario = false;
				
				var i = $scope.indiceDe($scope.tiposEstadoContractual, docente.persona.infoAdministrativa.tipoEstadoContractual.id, 'id');
				if (i!=-1) {
					$scope.tipoEstadoContractualSel = $scope.tiposEstadoContractual[i];
				} else {
					$scope.msgError = 'Error buscando Tipo de Estado Contractual a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				$scope.colapsarFormulario = false;

				var i = $scope.indiceDe($scope.tiposSituacionActual, docente.persona.infoAdministrativa.tipoSituacion.id, 'id');
				if (i!=-1) {
					$scope.tipoSituacionActualSel = $scope.tiposSituacionActual[i];
				} else {
					$scope.msgError = 'Error buscando el Tipo de Situacion Actual a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				$scope.colapsarFormulario = false;

				var i = $scope.indiceDe($scope.tiposSituacionRevista, docente.persona.infoAdministrativa.tipoSituacionRevista.id, 'id');
				if (i!=-1) {
					$scope.tipoSituacionRevistaSel = $scope.tiposSituacionRevista[i];
				} else {
					$scope.msgError = 'Error buscando el Tipo de Situacion de Revista a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				$scope.colapsarFormulario = false;
				
				var i = $scope.indiceDe($scope.tiposMotivo, docente.persona.infoAdministrativa.tipoMotivo.id, 'id');
				if (i!=-1) {
					$scope.tipoMotivoSel = $scope.tiposMotivo[i];
				} else {
					$scope.msgError = 'Error buscando el Tipo de Motivo a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				$scope.colapsarFormulario = false;
			}

			$scope.editar = function() {
				DocenteService.editar({
					'id' : $scope.id,
					'nombre' : $scope.nombre,
					'apellido' : $scope.apellido,
					'fechaNac' : $scope.fechaNac.getTime(),
					'id_tipoDoc' : $scope.tipoDocSel,
					'numeroDoc' : $scope.numeroDoc,
					'tipoCuil' : $scope.cuilTipoSel,
					'numeroDocCuil' : $scope.numeroDocCuil,
					'validadorCuil' : $scope.cuilValidadorSel,
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = docente.persona.nombre + ", Guardada.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + docente.persona.nombre;
					}
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.indiceDe = function (array, cadena, propiedad) {
			    for(var i = 0, len = array.length; i < len; i++) {
			        if (array[i][propiedad] === cadena) 
			        	return i;
			    }
			    return -1;
			}

			$scope.cerrarForm = function() {
				$scope.limpiar();
				$scope.colapsarFormulario = true;
			}
			
			$scope.limpiar = function() {
				$scope.id = null;
				$scope.nombre = null;
				$scope.apellido = null;
				$scope.fechaNac = null;
				$scope.tipoDocSel = null;
				$scope.numeroDoc = null;
				$scope.cuilTipoSel = null;
				$scope.numeroDocCuil = null;
				$scope.cuilValidadorSel = null;
				$scope.infoAdministrativa = null;
				$scope.tipoDocSel = $scope.tiposDoc[-1];
				$scope.tipoPersonalSel = null;
				$scope.tipoEstadoContractualSel = null;
				$scope.tipoSituacionActualSel = null;
				$scope.tipoSituacionRevistaSel = null;
				$scope.tipoMotivoSel = null;
				$scope.nuevoDomicilio = null;
				$scope.nuevoMedio = null;
				$scope.nuevosDomicilios = new Array();
				$scope.nuevosEmpleos = new Array();
				$scope.nuevosMediosContacto = new Array();
				$scope.nuevasFormacionesAcademicas = new Array();
			}
			
			$scope.confirmarBorrar = function(docente) {
				$scope.obj = docente;
				$scope.textoConfirm = docente.persona.apellido + " " + docente.persona.nombre; 
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(docente) {
				$('#confirm-modal').modal('hide');
				$scope.obj = {};
				$scope.textoConfirm = null;
			}

			$scope.rellenarDNICuil = function(){
				if ($scope.numeroDoc!=undefined && $scope.numeroDoc!=null && $scope.numeroDoc!="") {
					if ($scope.tipoDocSel==82) {
						$scope.numeroDocCuil = $scope.numeroDoc;
					} else {
						$scope.numeroDocCuil = null;
					}
				}
			}
			
			$scope.validarCuit = function(tipo, cuil){
				
				if (cuil!=null) {
				
					if (cuil.length == 7) {
						cuil = "0" + cuil;
						$scope.numeroDocCuil = "0" + $scope.numeroDocCuil; 
					}
					
					var cuit = tipo + "" + cuil;
					var mult = [5, 4, 3, 2, 7, 6, 5, 4, 3, 2];
					var total = 0;
					var digitos = cuit.split("");
					
				    for (var i = 0; i < mult.length; i++) {
				      total += digitos[i] * mult[i];
				    }
				 
				    var mod = total % 11;
				    var digito;
				 
				    if (mod == 0) {
				      digito = 0;
				    } else if (mod == 1) {
				      digito = 9;
				    } else {
				      digito = 11 - mod;
				    }
				 
				    $scope.cuilValidadorSel = digito;
				
				}
			    
			}
			
		} ]);