'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Asignatura */
msegErpControllers.controller('AsignaturaCtrl', ['$scope', '$filter', 'AsignaturaService', 
		function($scope, $filter, AsignaturaService) {

			$scope.modulo = 'Asignaturas';
			$scope.nombreForm = 'Asignatura';
			$scope.urlModulo = 'asignaturas';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.nueva = {};
			$scope.asignatura = {};
			$scope.asignaturas = {};
			$scope.anioSel = {};
			$scope.anios = [ {
				'value' : 1
			}, {
				'value' : 2
			}, {
				'value' : 3
			} ];
			
			$scope.colapsarFormulario = true;
			
			$scope.obj = {};
			
			var orderBy = $filter('orderBy');

			$scope.guardar = function(nueva) {
				nueva.anio = $scope.anioSel;
				if (nueva != null && nueva.nombre!=undefined && nueva.anio!=undefined) {
					AsignaturaService.guardar({
						'nueva' : nueva
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nueva.nombre + ', Guardada.';
							$scope.anioSel = {};
							$scope.nueva = {};
							$scope.listar();
							$scope.colapsarFormulario = true;
						} else {
							$scope.msgError = response.errorMessage;
							console.log('No se pudo guardar el elemento.');
						}
						$('#message-modal').modal('show');
					}, function(error) {
						alert(error);
					})
				} else {
					$scope.success = false;
					$scope.msgError = 'El nombre y/o el año no pueden ser vacios.';
					$('#message-modal').modal('show');
				}
			};

			$scope.listar = function() {
				AsignaturaService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.asignaturas = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener las asignaturas.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(asignatura) {
				AsignaturaService.borrar({
					'id' : asignatura.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = asignatura.nombre + ", Borrada.";
						$scope.listar();
					} else {
						$scope.msgError = response.errorMessage;
					}
					$scope.textoConfirm = null;
					$('#confirm-modal').modal('hide');
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.editarElemento = function(asignatura) {
				$scope.nueva = {};
				$scope.nueva.id = asignatura.id;
				$scope.nueva.nombre = asignatura.nombre;
				$scope.nueva.anio = asignatura.anio;
				$scope.nueva.extra = asignatura.extra;
				$scope.anioSel = asignatura.anio;
				$scope.colapsarFormulario = false;
			}

			$scope.editar = function(asignatura) {
				AsignaturaService.editar({
					'asignatura' : asignatura
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = asignatura.nombre + ", Guardada.";
						$scope.listar();
					} else {
						$scope.msgError = response.errorMessage;
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
			}
			
			$scope.confirmarBorrar = function(asignatura) {
				$scope.obj = asignatura;
				$scope.textoConfirm = asignatura.nombre;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(asignatura) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.asignatura = {};
			}

			$scope.initAnios = function (index){
				if (index!=0) {
					$scope.anios = new Array(index);
					for (var i = 0; i < $scope.anios.length; i++) {
						$scope.anios[i] = i+1;
					}
				}
			}
			
		} ]);