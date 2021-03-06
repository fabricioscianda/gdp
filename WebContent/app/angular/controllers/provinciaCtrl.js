'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Provincia */
msegErpControllers.controller('ProvinciaCtrl', ['$scope', '$filter', 'ProvinciaService',
		function($scope, $filter, ProvinciaService) {

			$scope.modulo = 'Provincias';
			$scope.nombreForm = 'Provincia';
			$scope.urlModulo = 'provincias';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.nuevo = {};
			$scope.provincia = {};
			$scope.provincias = {};

			$scope.colapsarFormulario = true;
			
			$scope.obj = {};
			
			var orderBy = $filter('orderBy');

			$scope.guardar = function(nuevo) {
				if (nuevo != null && nuevo.nombre != undefined) {
					ProvinciaService.guardar({
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
				ProvinciaService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.provincias = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los provincias.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(provincia) {
				ProvinciaService.borrar({
					'id' : provincia.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = provincia.nombre + ", Borrado.";
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

			$scope.editarElemento = function(provincia) {
				$scope.nuevo = {};
				$scope.nuevo.id = provincia.id;
				$scope.nuevo.nombre = provincia.nombre;
				$scope.colapsarFormulario = false;
			}

			$scope.editar = function(provincia) {
				ProvinciaService.editar({
					'provincia' : provincia
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = provincia.nombre + ", Guardado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + provincia.nombre;
					}
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.cerrarForm = function() {
				$scope.limpiar();
				$scope.colapsarFormulario = true;
			}
			
			$scope.limpiar = function() {
				$scope.nuevo = {};
			}
			
			$scope.confirmarBorrar = function(provincia) {
				$scope.obj = provincia;
				$scope.textoConfirm = provincia.nombre;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(provincia) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.obj = {};
			}
			
		} ]);