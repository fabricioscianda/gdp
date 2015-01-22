'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Contacto */
msegErpControllers.controller('ContactoCtrl', [
		'$scope',
		'$filter',
		'ContactoService',
		function($scope, $filter, ContactoService) {

			$scope.modulo = 'Contactos';
			$scope.nombreForm = 'Contacto';
			$scope.urlModulo = 'contacto';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;
			
			$scope.nuevo = {};
			$scope.contactos = {};

			$scope.colapsarFormulario = true;
			
			$scope.obj = {};
			
			var orderBy = $filter('orderBy');

			$scope.guardar = function(nuevo) {
				if (nuevo != null && nuevo.nombre != undefined) {
					ContactoService.guardar({
						'nuevo' : nuevo
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nuevo.nombre + ', Guardado.';
							$scope.nuevo = {};
							$scope.tipoContactoSel = {};
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
				ContactoService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.contactos = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los contactos.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(contacto) {
				ContactoService.borrar({
					'id' : contacto.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = contacto.nombre + ", Borrado.";
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

			$scope.editarElemento = function(contacto) {
				$scope.nuevo = {};
				$scope.nuevo.id = contacto.id;
				$scope.nuevo.tipoContacto = contacto.tipoContacto;
				$scope.nuevo.valor = contacto.valor;
				$scope.colapsarFormulario = false;
			}

			$scope.editar = function(contacto) {
				ContactoService.editar({
					'contacto' : contacto
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = contacto.nombre + ", Guardado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + contacto.nombre;
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
			
			$scope.confirmarBorrar = function(contacto) {
				$scope.obj = contacto;
				$scope.textoConfirm = contacto.nombre;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(contacto) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.contacto = {};
			}
			
		} ]);