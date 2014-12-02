var gdpControllers = angular.module('gdpControllers', []);

gdpControllers.controller('AdministracionCtrl', [ '$scope', function($scope) {
	$scope.tiposAdmin = [ {
		'nombre' : 'Publica'
	}, {
		'nombre' : 'Privada'
	} ];

	$scope.nuevoTipo = {};

	$scope.guardar = function(nuevo) {
		$scope.tiposAdmin.push(nuevo);
		$scope.nuevoTipo = {};
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