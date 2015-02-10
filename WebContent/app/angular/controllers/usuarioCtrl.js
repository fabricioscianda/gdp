'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Usuario */
msegErpControllers.controller('UsuarioCtrl', [
		'$scope',
		'$filter',
		'UsuarioService',
		function($scope, $filter, UsuarioService) {

			$scope.modulo = 'Usuarios';
			$scope.nombreForm = 'Usuario';
			$scope.urlModulo = 'usuarios';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.usuario = {};
			$scope.usuarios = {};

			$scope.colapsarFormulario = true;
			
			$scope.obj = {};
			
			var orderBy = $filter('orderBy');

			$scope.guardar = function(usuario) {
				if (usuario != null && usuario.nombre != undefined && usuario.apellido != undefined && usuario.username != undefined && usuario.password != undefined) {
					UsuarioService.guardar({
						'usuario' : usuario
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = usuario.nombre + ' ' + usuario.apellido + ', Guardado.';
							$scope.usuario = {};
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
					$scope.msgError = 'Todos los campos son obligatorios.';
					$('#message-modal').modal('show');
				}
			};

			$scope.listar = function() {
				UsuarioService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.usuarios = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los usuarios.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(usuario) {
				UsuarioService.borrar({
					'id' : usuario.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = usuario.nombre + ' ' + usuario.apellido + ", Borrado.";
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

			$scope.editarElemento = function(usuario) {
				$scope.usuario = {};
				$scope.usuario.id = usuario.id;
				$scope.usuario.nombre = usuario.nombre;
				$scope.usuario.apellido = usuario.apellido;
				$scope.usuario.username = usuario.username;
				$scope.usuario.password = usuario.password;
				$scope.usuario.esAdmin = usuario.esAdmin;
				$scope.colapsarFormulario = false;
			}

			$scope.cerrarForm = function() {
				$scope.limpiar();
				$scope.colapsarFormulario = true;
			}
			
			$scope.limpiar = function() {
				$scope.usuario = {};
			}
			
			$scope.confirmarBorrar = function(usuario) {
				$scope.obj = usuario;
				$scope.textoConfirm = (usuario.nombre + ' ' + usuario.apellido);
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(usuario) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.obj = {};
			}
			
		} ]);