'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Docente */
msegErpControllers.controller('DocenteCtrl', ['$scope', '$filter', 'DocenteService', 'TipoDocumentoService', 'TipoContactoService', 'DomicilioService', 'LocalidadService', 'TipoFormacionService',  
                                              function($scope, $filter, DocenteService, TipoDocumentoService, TipoContactoService, DomicilioService, LocalidadService, TipoFormacionService) {

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
			
			$scope.nuevoDomicilio = {};
			$scope.nuevosDomicilios = new Array();
			$scope.domicilios = {};
			$scope.localidades = {};
			$scope.localidadSel = {};

			$scope.colapsarFormulario = true;
			
			$scope.obj = {};
			
			var orderBy = $filter('orderBy');
			
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

			$scope.dateOptions = {
				formatYear : 'yy',
				startingDay : 1
			};
			
			$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate', 'yyyy' ];
			
			$scope.format = $scope.formats[2];
			$scope.formatYearOnly = $scope.formats[4];
			
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
				$scope.nuevasFormacionesAcademicas.push($scope.nuevaFormacion);
				$scope.nuevaFormacion = {};
				$scope.nuevaFormacion.anio = null;
				$scope.tipoFormacionSel = null;
			}
			
			$scope.removeFormacion = function(formacion) {
				$scope.nuevasFormacionesAcademicas.splice($scope.nuevasFormacionesAcademicas.indexOf(formacion),1);
				$scope.nuevaFormacion = {};
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
				if ($scope.nombre!=undefined) {
					DocenteService.guardar({
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
				$scope.nuevosMediosContacto = docente.persona.mediosContacto;
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
			
			$scope.limpiar = function() {
				$scope.id = null;
				$scope.nombre = null;
				$scope.apellido = null;
				$scope.fechaNac = new Date(Date.now());
				$scope.tipoDocSel = null;
				$scope.numeroDoc = null;
				$scope.cuilTipoSel = null;
				$scope.numeroDocCuil = null;
				$scope.cuilValidadorSel = null;
				$scope.tipoDocSel = $scope.tiposDoc[-1];
				$scope.nuevoDomicilio = null;
				$scope.nuevoMedio = null;
				$scope.nuevosDomicilios = new Array();
				$scope.nuevosMediosContacto = new Array();
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