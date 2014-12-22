'use strict';
var gdpControllers = angular.module('gdpControllers');

/* Instituto */
gdpControllers.controller('InstitutoCtrl', ['$scope', '$filter', 'InstitutoService',
		function($scope, $filter, InstitutoService) {

			$scope.modulo = 'Institutos';
			$scope.nombreForm = 'Instituto';
			$scope.urlModulo = 'institutos';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;

			$scope.nuevo = {};
			$scope.instituto = {};
			$scope.institutos = {};

			var orderBy = $filter('orderBy');

			$scope.guardar = function(nuevo) {
				if (nuevo != null && nuevo.nombre != undefined) {
					InstitutoService.guardar({
						'nuevo' : nuevo
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nuevo.nombre + ', Guardado.';
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
				InstitutoService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.institutos = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los institutos.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(instituto) {
				InstitutoService.borrar({
					'id' : instituto.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = instituto.nombre + ", Borrado.";
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

			$scope.editarElemento = function(instituto) {
				$scope.nuevo = {};
				$scope.nuevo.id = instituto.id;
				$scope.nuevo.nombre = instituto.nombre;
			}

			$scope.editar = function(instituto) {
				InstitutoService.editar({
					'instituto' : instituto
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = instituto.nombre + ", Guardado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + instituto.nombre;
					}
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.limpiar = function() {
				$scope.nuevo = {};
			}
			
			$scope.confirmarBorrar = function(instituto) {
				$scope.instituto = instituto;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(instituto) {
				$('#confirm-modal').modal('hide');
				$scope.instituto = {};
			}
			
		} ]);