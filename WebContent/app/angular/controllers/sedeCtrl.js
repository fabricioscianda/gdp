'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Sede */
msegErpControllers.controller('SedeCtrl', ['$scope', '$filter', 'SedeService', 'LocalidadService', 'InstitutoService', 
		function($scope, $filter, SedeService, LocalidadService, InstitutoService) {

			$scope.modulo = 'Sedes';
			$scope.nombreForm = 'Sede';
			$scope.urlModulo = 'sedes';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;

			$scope.nueva = {};
			$scope.sede = {};
			$scope.sedes = {};
			$scope.localidad = {};
			$scope.localidadSel = {};
			$scope.localidades = {};
			$scope.instituto = {};
			$scope.institutoSel = {};
			$scope.institutos = {};

			var orderBy = $filter('orderBy');

			$scope.inicializarListados = function() {
				$scope.listarLocalidades();
				$scope.listarInstitutos();
			}
			
			$scope.listarLocalidades = function() {
				LocalidadService.listar({},
					function (response){
						$scope.success = response.ok;
						if (response.ok) {
							 $scope.localidades = angular.fromJson(response.data);
							 $scope.localidadSel = $scope.localidades[-1];
							 $scope.localidades = orderBy($scope.localidades, 'nombre');
						} else {
							$scope.msgError = 'No se pudieron obtener las localidades';
							console.log('No se pudieron obtener las localidades');
							$('#message-modal').modal('show');
						}
					},
					function(error) {
						alert(error);
					})
			};
			
			$scope.listarInstitutos = function() {
				InstitutoService.listar({},
						function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.institutos = angular.fromJson(response.data);
						$scope.institutoSel = $scope.institutos[-1];
						$scope.institutos = orderBy($scope.institutos, 'nombre');
					} else {
						$scope.msgError = 'No se pudieron obtener los institutos';
						console.log('No se pudieron obtener los institutos');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.guardar = function(nueva) {
				nueva.localidad = $scope.localidadSel; 
				nueva.instituto = $scope.institutoSel; 
				if (nueva != null && nueva.nombre != undefined && nueva.localidad!=null && nueva.instituto!=null) {
					SedeService.guardar({
						'nueva' : nueva
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nueva.nombre + ', Guardada.';
							$scope.localidadSel = $scope.localidades[-1];
							$scope.institutoSel = $scope.institutos[-1];
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
					$scope.msgError = 'Tanto el nombre como la localidad o la institucion no pueden ser vacios.';
					$('#message-modal').modal('show');
				}
			};

			$scope.listar = function() {
				SedeService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.sedes = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener las sedes.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(sede) {
				SedeService.borrar({
					'id' : sede.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = sede.nombre + ", Borrada.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo borrar el elemento.";
					}
					$('#confirm-modal').modal('hide');
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.editarElemento = function(sede) {
				$scope.nueva = {};
				$scope.nueva.id = sede.id;
				$scope.nueva.nombre = sede.nombre;
				var i = $scope.indiceDe($scope.localidades, sede.localidad.id, 'id');
				if (i!=-1) {
					$scope.localidadSel = $scope.localidades[i];
				} else {
					$scope.msgError = 'Error buscando la localidad de la sede a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				var i = $scope.indiceDe($scope.institutos, sede.instituto.id, 'id');
				if (i!=-1) {
					$scope.institutoSel = $scope.institutos[i];
				} else {
					$scope.msgError = 'Error buscando el instituto de la sede a editar, en el listado.';
					$('#message-modal').modal('show');
				}
			}

			$scope.editar = function(sede) {
				sede.localidad = $scope.localidadSel;
				sede.instituto = $scope.institutoSel;
				SedeService.editar({
					'sede' : sede
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = sede.nombre + ", Guardada.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + sede.nombre;
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
				$scope.localidadSel = $scope.localidades[-1];
				$scope.institutoSel = $scope.institutos[-1];
			}
			
			$scope.confirmarBorrar = function(sede) {
				$scope.sede = sede;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(sede) {
				$('#confirm-modal').modal('hide');
				$scope.sede = {};
			}
			
		} ]);