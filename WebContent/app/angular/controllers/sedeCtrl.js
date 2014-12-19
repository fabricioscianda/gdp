'use strict';
var gdpControllers = angular.module('gdpControllers');

/* Sede */
gdpControllers.controller('SedeCtrl', ['$scope', '$filter', 'SedeService', 'LocalidadService', 
		function($scope, $filter, SedeService, LocalidadService) {

			$scope.modulo = 'Sedes';
			$scope.nombreForm = 'Sede';
			$scope.urlModulo = 'sedes';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;

			$scope.nueva = {};
			$scope.sedes = {};
			$scope.localidadSel = {};
			$scope.localidad = {};
			$scope.localidades = {};

			var orderBy = $filter('orderBy');

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
			
			$scope.guardar = function(nueva) {
				nueva.localidad = $scope.localidadSel; 
				if (nueva != null && nueva.nombre != undefined && nueva.localidad!=null) {
					SedeService.guardar({
						'nueva' : nueva
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nueva.nombre + ', Guardada.';
							$scope.localidadSel = $scope.localidades[-1];
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
					$scope.msgError = 'El nombre no puede ser vacio.';
					$('#message-modal').modal('show');
				}
			};

			$scope.listar = function() {
				SedeService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.sedes = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los sedes.';
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
			}

			$scope.editar = function(sede) {
				sede.localidad = $scope.localidadSel;
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
			}
			
		} ]);