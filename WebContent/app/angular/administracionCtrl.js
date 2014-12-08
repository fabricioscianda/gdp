'use strict';
var gdpControllers = angular.module('gdpControllers', []);

gdpControllers.controller('AdministracionCtrl', [ '$scope', '$filter', 'AdministracionService',
		function($scope, $filter, AdministracionService) {

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;

			$scope.nuevoTipo = {};
			$scope.tiposAdmin = {};

			var orderBy = $filter('orderBy');

			$scope.guardar = function(nuevo) {
				if (nuevo != null && nuevo.nombre != undefined) {
					AdministracionService.guardar({
						'nuevo' : nuevo
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = 'Guardado.';
							$scope.nuevoTipo = {};
							$scope.listar();
						} else {
							$scope.msgError = 'No se pudo guardar.';
							console.log('No se pudo guardar el elemento.');
						}
					}, function(error) {
						alert(error);
					})
				} else {
					$scope.success = false;
					$scope.msgError = 'El nombre no puede ser vacio.';
				}
			};

			$scope.listar = function() {
				AdministracionService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposAdmin = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los tipos.';
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(tipo) {
				AdministracionService.borrar({
					'id' : tipo.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = "Borrado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo borrar el elemento.";
					}
				}, function(error) {
					alert(error);
				});
			}

			$scope.editarElemento = function(tipo) {
				$scope.nuevoTipo = {};
				$scope.nuevoTipo.id = tipo.id;
				$scope.nuevoTipo.nombre = tipo.nombre;
			}

			$scope.editar = function(tipo) {
				AdministracionService.editar({
					'tipo' : tipo
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = "Guardado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento.";
					}
				}, function(error) {
					alert(error);
				});
			}

		} ]);