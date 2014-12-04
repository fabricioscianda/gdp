'use strict';
var gdpControllers = angular.module('gdpControllers', []);

gdpControllers.controller('AdministracionCtrl', [ '$scope',
		'AdministracionService', function($scope, AdministracionService) {

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			
			$scope.nuevoTipo = {};
			$scope.tiposAdmin = {};

			$scope.guardar = function(nuevo) {
				AdministracionService.guardar({
					'nuevo' : nuevo
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = 'Guardado.';
						$scope.listar();
						console.log('Tipo Administracion Guardada.');
					} else {
						$scope.msgError = 'No se pudo guardar.';
						console.log('No se pudo guardar el elemento.');
					}
				}, function(error) {
					alert(error);
				})
			};

			$scope.listar = function() {
				AdministracionService.listar( {},
					function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.tiposAdmin = angular.fromJson(response.data);
						} else {
							$scope.msgError = 'No se pudieron obtener los tipos.';
						};
					}, 
					function(error) {
						alert(error);
					});
			};

			$scope.borrar = function(index) {
				$scope.tiposAdmin.splice(index, 1);
				$scope.nuevoTipo = {};
			}

			$scope.editar = function(tipo) {
				$scope.nuevoTipo = {};
				$scope.nuevoTipo.nombre = tipo.nombre;
			}

		} ]);

gdpControllers.controller('CargoCtrl', [ '$scope', function($scope) {
	$scope.cargos = [ {
		'nombre' : 'Teniente'
	}, {
		'nombre' : 'Director'
	}, {
		'nombre' : 'Sub Director'
	} ];

	$scope.nuevoCargo = {};

	$scope.guardar = function(nuevo) {
		$scope.cargos.push(nuevo);
		$scope.nuevoCargo = {};
	};

	$scope.borrar = function(index) {
		$scope.cargos.splice(index, 1);
		$scope.nuevoCargo = {};
	}

	$scope.editar = function(cargo) {
		$scope.nuevoCargo = {};
		$scope.nuevoCargo.nombre = cargo.nombre;
	}

} ]);