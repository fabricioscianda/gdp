'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Localidad */
msegErpControllers.controller('LocalidadCtrl', ['$scope', '$filter', 'LocalidadService', 'PartidoService', 'ProvinciaService', 
		function($scope, $filter, LocalidadService, PartidoService, ProvinciaService) {

			$scope.modulo = 'Localidades';
			$scope.nombreForm = 'Localidad';
			$scope.urlModulo = 'localidades';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.nueva = {};
			$scope.localidad = {};
			$scope.localidades = {};
			$scope.partidoSel = {};
			$scope.partidos = {};
			$scope.provinciaSel = {};
			$scope.provincias = {};

			$scope.colapsarFormulario = true;
			
			$scope.obj = {};
			
			var orderBy = $filter('orderBy');

			$scope.listarProvincias = function() {
				ProvinciaService.listar({}, 
					function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.provincias = angular.fromJson(response.data);
							$scope.provincias = orderBy($scope.provincias, 'nombre');
						} else {
							$scope.msgError = response.errorMessage;
							console.log('No se pudieron obtener las Provincias');
							$('#message-modal').modal('show');
						}
					}, function(error) {
						alert(error);
					});
			};
			
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
							$scope.nueva = {};
							$scope.limpiar();
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
					$scope.textoConfirm = null;
					$('#confirm-modal').modal('hide');
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
				var i = $scope.indiceDe($scope.provincias, localidad.partido.provincia.id, 'id');
				if (i!=-1) {
					$scope.provinciaSel = $scope.provincias[i];
				} else {
					$scope.msgError = 'Error buscando la provincia del partido a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				i = $scope.indiceDe($scope.partidos, localidad.partido.id, 'id');
				if (i!=-1) {
					$scope.partidoSel = $scope.partidos[i];
				} else {
					$scope.msgError = 'Error buscando el partido a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				$scope.colapsarFormulario = false;
			}

			$scope.editar = function(localidad) {
				localidad.partido = $scope.partidoSel;
				LocalidadService.editar({
					'localidad' : localidad
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = localidad.nombre + ", Guardada.";
						$scope.limpiar();
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + localidad.nombre;
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
				$scope.partidoSel = $scope.partidos[-1];
				$scope.provinciaSel = $scope.provincias[-1];
			}
			
			$scope.confirmarBorrar = function(localidad) {
				$scope.obj = localidad;
				$scope.textoConfirm = localidad.nombre;
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(localidad) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.localidad = {};
			}
			
		} ]);