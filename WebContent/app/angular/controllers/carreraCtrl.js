'use strict';
var gdpControllers = angular.module('gdpControllers');

/* Carrera */
gdpControllers.controller('CarreraCtrl', ['$scope', '$filter', 'CarreraService', 'SedeService', 
		function($scope, $filter, CarreraService, SedeService) {

			$scope.modulo = 'Carreras';
			$scope.nombreForm = 'Carrera';
			$scope.urlModulo = 'carreras';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;

			$scope.nueva = {};
			$scope.carrera = {};
			$scope.carreras = {};
			$scope.sedeSel = {};
			$scope.sede = {};
			$scope.sedes = {};
			
			$scope.anios = [ {
				'value' : 1
			}, {
				'value' : 2
			}, {
				'value' : 3
			} ];
			
			$scope.anioSel = {};

			var orderBy = $filter('orderBy');

			$scope.listarSedes = function() {
				SedeService.listar({},
					function (response){
						$scope.success = response.ok;
						if (response.ok) {
							 $scope.sedes = angular.fromJson(response.data);
							 $scope.sedeSel = $scope.sedes[-1];
							 $scope.sedes = orderBy($scope.sedes, 'nombre');
						} else {
							$scope.msgError = 'No se pudieron obtener las sedes';
							console.log('No se pudieron obtener las sedes');
							$('#message-modal').modal('show');
						}
					},
					function(error) {
						alert(error);
					})
			};
			
			$scope.guardar = function(nueva) {
				nueva.sede = $scope.sedeSel; 
				nueva.cantAnios = $scope.anioSel;
				if (nueva != null && nueva.nombre != undefined && nueva.sede!=null) {
					CarreraService.guardar({
						'nueva' : nueva
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nueva.nombre + ', Guardada.';
							$scope.sedeSel = $scope.sedes[-1];
							$scope.nueva = {};
							$scope.anioSel = {};
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
				CarreraService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.carreras = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener las carreras.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(carrera) {
				CarreraService.borrar({
					'id' : carrera.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = carrera.nombre + ", Borrada.";
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

			$scope.editarElemento = function(carrera) {
				$scope.nueva = {};
				$scope.nueva.id = carrera.id;
				$scope.nueva.nombre = carrera.nombre;
				$scope.nueva.cantAnios = carrera.cantAnios;
				$scope.anioSel = carrera.cantAnios;
				var i = $scope.indiceDe($scope.sedes, carrera.sede.id, 'id');
				if (i!=-1) {
					$scope.sedeSel = $scope.sedes[i];
				} else {
					$scope.msgError = 'Error buscando la sede de la carrera a editar, en el listado.';
					$('#message-modal').modal('show');
				}
			}

			$scope.editar = function(carrera) {
				carrera.localidad = $scope.localidadSel;
				carrera.cantAnios = $scope.anioSel;
				CarreraService.editar({
					'carrera' : carrera
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = carrera.nombre + ", Guardada.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + carrera.nombre;
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
				$scope.sedeSel = $scope.sedes[-1];
				$scope.nueva.cantAnios = {};
				$scope.carrera.cantAnios = {};
				$scope.anioSel = {};
			}
			
			$scope.confirmarBorrar = function(carrera) {
				$scope.carrera = carrera;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(carrera) {
				$('#confirm-modal').modal('hide');
				$scope.carrera = {};
			}
			
		} ]);