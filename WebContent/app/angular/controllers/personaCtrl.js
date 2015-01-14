'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Persona */
msegErpControllers.controller('PersonaCtrl', ['$scope', '$filter', 'PersonaService', 'TipoDocumentoService', 'TipoContactoService', 'DomicilioService', 'LocalidadService', 
                                              function($scope, $filter, PersonaService, TipoDocumentoService, TipoContactoService, DomicilioService, LocalidadService) {

			$scope.modulo = 'Personas';
			$scope.nombreForm = 'Persona';
			$scope.urlModulo = 'personas';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.nueva = {};
			$scope.persona = {};
			$scope.personas = {};
			$scope.tiposDoc = {};
			$scope.tipoDocSel = {};
			$scope.fechaNac = new Date(Date.now());
			$scope.cuilTipoSel = {};
			$scope.numeroDocCuil = {};
			$scope.cuilValidadorSel = {};

			$scope.nuevoMedio = {};
			$scope.nuevosMediosContacto = new Array();
			$scope.mediosContacto = {};
			$scope.medioSel = {};
			
			$scope.nuevoDomicilio = {};
			$scope.nuevosDomicilios = new Array();
			$scope.domicilios = {};
			$scope.localidades = {};
			$scope.localidadSel = {};

			$scope.tiposCuil = [20,23,24,27,30,33];
			$scope.validadoresCuil = [0,1,2,3,4,5,6,7,8,9];
			
			$scope.colapsarFormulario = true;
			
			$scope.obj = {};
			
			var orderBy = $filter('orderBy');

			$scope.addMedio = function(nuevoMedio) {
				$scope.nuevoMedio.medio = $scope.medioSel;
				$scope.nuevosMediosContacto.push(nuevoMedio);
				$scope.nuevoMedio = {};
			}
			
			$scope.removeMedio = function(medio) {
				$scope.nuevosMediosContacto.splice($scope.nuevosMediosContacto.indexOf(medio),1);
				$scope.nuevoMedio = {};
			}
			
			$scope.addDomicilio = function(nuevoDomicilio) {
				$scope.nuevoDomicilio.localidad = $scope.localidadSel;
				$scope.nuevosDomicilios.push(nuevoDomicilio);
				$scope.nuevoDomicilio = {};
			}
			
			$scope.removeDomicilio = function(domicilio) {
				$scope.nuevosDomicilios.splice($scope.nuevosDomicilios.indexOf(domicilio),1);
				$scope.nuevoDomicilio = {};
			}
			
			$scope.agregarMedio = function(nuevoMedio) {
				if (nuevoMedio != undefined && nuevoMedio.persona!=undefined && nuevoMedio.medio != undefined && nuevoMedio.contacto != undefined) {
					ContactoService.guardar({
						'nuevo' : nuevoMedio
					}, function(response) {
						if (response.ok) {
							$scope.msgSuccess = nuevoMedio.medio + ' : ' + nuevoMedio.contacto + ', Guardado.';
							$scope.nuevoMedio = {};
						} else {
							$scope.msgError = 'No se pudo guardar el nuevo medio de contacto.';
						}
						$('#message-modal').modal('show');
					}, function(error) {
						alert(error);
					});
				}
			};

			$scope.listarMediosContacto = function() {
				TipoContactoService.listar({},
						function (response){
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
			
			$scope.listarDomicilios = function() {
				DomicilioService.listar({},
						function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.domicilios = angular.fromJson(response.data);
						$scope.domicilios = orderBy($scope.domicilios, ['domicilio','localidad.nombre']);
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
			
			$scope.guardar = function(nueva) {
				nueva.tipoDoc = $scope.tipoDocSel; 
				if (nueva != null && nueva.nombre!=undefined && nueva.anio!=undefined && nueva.tipoDoc!=null) {
					PersonaService.guardar({
						'nueva' : nueva
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nueva.apellido + ' ' + nueva.nombre + ', Guardada.';
							$scope.tipoDocSel = $scope.tiposDoc[-1];
							$scope.nueva = {};
							$scope.listar();
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
				PersonaService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.personas = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener las personas.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(persona) {
				PersonaService.borrar({
					'id' : persona.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = persona.nombre + ", Borrado.";
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

			$scope.editarElemento = function(persona) {
				$scope.nueva = {};
				$scope.nueva.id = persona.id;
				$scope.nueva.nombre = persona.nombre;
				var i = $scope.indiceDe($scope.tiposDoc, persona.tipoDoc.id, 'id');
				if (i!=-1) {
					$scope.tipoDocSel = $scope.tiposDoc[i];
				} else {
					$scope.msgError = 'Error buscando el tipo de documento de la persona a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				$scope.colapsarFormulario = false;
			}

			$scope.editar = function(persona) {
				persona.tipoDoc = $scope.tipoDocSel;
				PersonaService.editar({
					'persona' : persona
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = persona.nombre + ", Guardada.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + persona.nombre;
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
				$scope.nueva = {};
				$scope.tipoDocSel = $scope.tiposDoc[-1];
			}
			
			$scope.confirmarBorrar = function(persona) {
				$scope.obj = persona;
				$scope.textoConfirm = persona.apellido + " " + persona.nombre;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(persona) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.obj = {};
			}

			$scope.validarCuit = function(cuit) {

				if (cuit.length != 11) {
					return false;
				}

				var acumulado = 0;
				var digitos = cuit.split("");
				var digito = digitos.pop();

				for (var i = 0; i < digitos.length; i++) {
					acumulado += digitos[9 - i] * (2 + (i % 6));
				}

				var verif = 11 - (acumulado % 11);
				if (verif == 11) {
					verif = 0;
				} else if (verif == 10) {
					verif = 9;
				}

				return digito == verif;
			}
			
		} ]);