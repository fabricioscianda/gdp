'use strict';
var gdpControllers = angular.module('gdpControllers');

/* Tipos Estado Contractual */
gdpControllers.controller('TipoEstadoContractualCtrl', [
		'$scope',
		'$filter',
		'TipoEstadoContractualService',
		function($scope, $filter, TipoEstadoContractualService) {

			$scope.modulo = 'Estados Contractuales';
			$scope.nombreForm = 'Tipo de Estado Contractual';
			$scope.urlModulo = 'tiposEstadoContractual';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;

			$scope.nuevo = {};
			$scope.tipos = {};

			var orderBy = $filter('orderBy');

			$scope.guardar = function(nuevo) {
				if (nuevo != null && nuevo.nombre != undefined) {
					TipoEstadoContractualService.guardar({
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
				TipoEstadoContractualService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tipos = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los tipos.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(tipo) {
				TipoEstadoContractualService.borrar({
					'id' : tipo.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = tipo.nombre + ", Borrado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo borrar el elemento.";
					}
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.editarElemento = function(tipo) {
				$scope.nuevo = {};
				$scope.nuevo.id = tipo.id;
				$scope.nuevo.nombre = tipo.nombre;
			}

			$scope.editar = function(tipo) {
				TipoEstadoContractualService.editar({
					'tipo' : tipo
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = tipo.nombre + ", Guardado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, "
								+ tipo.nombre;
					}
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

		} ]);