'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Tipos Situacion */
msegErpControllers.controller('TipoSituacionCtrl', [
		'$scope',
		'$filter',
		'TipoSituacionService',
		function($scope, $filter, TipoSituacionService) {

			$scope.modulo = 'Situacion Actual';
			$scope.nombreForm = 'Tipo de Situacion Actual';
			$scope.urlModulo = 'tiposSituacion';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.nuevo = {};
			$scope.tipos = {};

			$scope.colapsarFormulario = true;

			$scope.obj = {};
			
			var orderBy = $filter('orderBy');

			$scope.guardar = function(nuevo) {
				if (nuevo != null && nuevo.nombre != undefined) {
					TipoSituacionService.guardar({
						'nuevo' : nuevo
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nuevo.nombre + ', Guardado.';
							$scope.cerrarForm();
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
				TipoSituacionService.listar({}, function(response) {
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
				TipoSituacionService.borrar({
					'id' : tipo.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = tipo.nombre + ", Borrado.";
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

			$scope.editarElemento = function(tipo) {
				$scope.nuevo = {};
				$scope.nuevo.id = tipo.id;
				$scope.nuevo.nombre = tipo.nombre;
				$scope.colapsarFormulario = false;
			}

			$scope.cerrarForm = function() {
				$scope.limpiar();
				$scope.colapsarFormulario = true;
			}
			
			$scope.limpiar = function() {
				$scope.nuevo = {};
			}

			$scope.confirmarBorrar = function(tipo) {
				$scope.obj = tipo;
				$scope.textoConfirm = tipo.nombre;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(tipo) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.obj = {};
			}
			
		} ]);