'use strict';
var gdpControllers = angular.module('gdpControllers');

/* Localidad */
gdpControllers.controller('LocalidadCtrl', ['$scope', '$filter', 'LocalidadService', 'PartidoService', 
		function($scope, $filter, LocalidadService, PartidoService) {

			$scope.modulo = 'Localidades';
			$scope.nombreForm = 'Localidad';
			$scope.urlModulo = 'localidades';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;

			$scope.nueva = {};
			$scope.localidades = {};
			$scope.partidoSel = {};
			$scope.partidos = {};

			var orderBy = $filter('orderBy');

			$scope.listarPartidos = function() {
				PartidoService.listar({},
					function (response){
						$scope.success = response.ok;
						if (response.ok) {
							 $scope.partidos = angular.fromJson(response.data);
							 $scope.partidoSel = $scope.partidos[-1];
							 $scope.partidos = orderBy($scope.partidos, 'nombre');
						} else {
							$scope.msgError = 'No se pudieron obtener los Partidos';
							console.log('No se pudieron obtener los Partidos');
							$('#message-modal').modal('show');
						}
					},
					function(error) {
						alert(error);
					})
			};
			
			$scope.guardar = function(nueva) {
				nueva.partido = $scope.partidoSel; 
				if (nueva != null && nueva.nombre != undefined && nueva.partido!=null) {
					LocalidadService.guardar({
						'nueva' : nueva
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nueva.nombre + ', Guardada.';
							$scope.partidoSel = $scope.partidos[-1];
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
				LocalidadService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.localidades = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener las localidades.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(localidad) {
				LocalidadService.borrar({
					'id' : localidad.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = localidad.nombre + ", Borrado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo borrar el elemento.";
					}
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.editarElemento = function(localidad) {
				$scope.nueva = {};
				$scope.nueva.id = localidad.id;
				$scope.nueva.cp = localidad.cp;
				$scope.nueva.nombre = localidad.nombre;
				var i = $scope.indiceDe($scope.partidos, localidad.partido.id, 'id');
				if (i!=-1) {
					$scope.partidoSel = $scope.partidos[i];
				} else {
					$scope.msgError = 'Error buscando el partido de la localidad a editar, en el listado.';
					$('#message-modal').modal('show');
				}
			}

			$scope.editar = function(localidad) {
				localidad.partido = $scope.partidoSel;
				LocalidadService.editar({
					'localidad' : localidad
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = localidad.nombre + ", Guardada.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + localidad.nombre;
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
				$scope.partidoSel = $scope.partidos[-1];
			}
			
		} ]);