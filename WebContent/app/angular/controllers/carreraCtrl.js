'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Carrera */
msegErpControllers.controller( 'CarreraCtrl', [
						'$scope',
						'$filter',
						'CarreraService',
						function($scope, $filter, CarreraService) {

			$scope.modulo = 'Carreras';
			$scope.nombreForm = 'Carrera';
			$scope.urlModulo = 'carreras';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.nueva = {};
			$scope.carrera = {};
			$scope.carreras = {};
			
			$scope.anios = [ {
				'value' : 1
			}, {
				'value' : 2
			}, {
				'value' : 3
			} ];
			
			$scope.anioSel = {};

			$scope.colapsarFormulario = true;

			$scope.obj = {};
			
			var orderBy = $filter('orderBy');

			$scope.guardar = function(nueva) {
				nueva.cantAnios = $scope.anioSel;
				if (nueva != null && nueva.nombre != undefined) {
					CarreraService.guardar({
						'nueva' : nueva
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nueva.nombre + ', Guardada.';
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
					$scope.textoConfirm = null;
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
				$scope.colapsarFormulario = false;
			}

			$scope.editar = function(carrera) {
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

			$scope.cerrarForm = function() {
				$scope.limpiar();
				$scope.colapsarFormulario = true;
			}
			
			$scope.limpiar = function() {
				$scope.nueva = {};
				$scope.nueva.cantAnios = {};
				$scope.carrera.cantAnios = {};
				$scope.anioSel = {};
			}
			
			$scope.confirmarBorrar = function(carrera) {
				$scope.obj = carrera;
				$scope.textoConfirm = carrera.nombre;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(carrera) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.carrera = {};
			}
			
		} ]);