'use strict';
var gdpControllers = angular.module('gdpControllers');

/* Partido */
gdpControllers.controller('PartidoCtrl', ['$scope', '$filter', 'PartidoService', 'ProvinciaService', 
		function($scope, $filter, PartidoService, ProvinciaService) {

			$scope.modulo = 'Partidos';
			$scope.nombreForm = 'Partido';
			$scope.urlModulo = 'partidos';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;

			$scope.nuevo = {};
			$scope.partidos = {};
			$scope.provinciaSel = {};
			$scope.provincias = {};

			var orderBy = $filter('orderBy');

			$scope.listarProvincias = function() {
				ProvinciaService.listar({},
					function (response){
						$scope.success = response.ok;
						if (response.ok) {
							 $scope.provincias = angular.fromJson(response.data);
							 $scope.provinciaSel = $scope.provincias[-1];
							 $scope.provincias = orderBy($scope.provincias, 'nombre');
						} else {
							$scope.msgError = 'No se pudieron obtener las Provincias';
							console.log('No se pudieron obtener las Provincias');
							$('#message-modal').modal('show');
						}
					},
					function(error) {
						alert(error);
					})
			};
			
			$scope.guardar = function(nuevo) {
				nuevo.provincia = $scope.provinciaSel; 
				if (nuevo != null && nuevo.nombre != undefined && nuevo.provincia!=null) {
					PartidoService.guardar({
						'nuevo' : nuevo
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nuevo.nombre + ', Guardado.';
							$scope.provinciaSel = $scope.provincias[-1];
							$scope.nuevo = {};
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
				PartidoService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.partidos = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los partidos.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(partido) {
				PartidoService.borrar({
					'id' : partido.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = partido.nombre + ", Borrado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo borrar el elemento.";
					}
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.editarElemento = function(partido) {
				$scope.nuevo = {};
				$scope.nuevo.id = partido.id;
				$scope.nuevo.nombre = partido.nombre;
				var i = $scope.indiceDe($scope.provincias, partido.provincia.id, 'id');
				if (i!=-1) {
					$scope.provinciaSel = $scope.provincias[i];
				} else {
					$scope.msgError = 'Error buscando la provincia del partido a editar, en el listado.';
					$('#message-modal').modal('show');
				}
			}

			$scope.editar = function(partido) {
				partido.provincia = $scope.provinciaSel;
				PartidoService.editar({
					'partido' : partido
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = partido.nombre + ", Guardado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + partido.nombre;
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
				$scope.nuevo = {};
				$scope.provinciaSel = $scope.provincias[-1];
			}
			
		} ]);