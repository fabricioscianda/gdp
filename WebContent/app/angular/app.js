'use strict';

var gdpApp = angular.module('gdpApp', [ 'ngRoute', 'gdpControllers', 'gdpServices' ]);

gdpApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/administracion', {
		templateUrl : 'app/views/formGenerico.html',
		// templateUrl : 'app/views/administracion.html',
		controller : 'AdministracionCtrl'
	}).when('/cargos', {
		templateUrl : 'app/views/formGenerico.html',
		// templateUrl : 'app/views/cargos.html',
		controller : 'CargoCtrl'
	}).otherwise({
		templateUrl : 'app/views/home.html'
	});
} ]);
