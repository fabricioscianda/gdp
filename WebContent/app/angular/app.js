'use strict';

// var gdpApp = angular.module('gdpApp', [ 'ngRoute' ])
var gdpApp = angular.module('gdpApp', [ 'ngRoute', 'gdpControllers' ]);

gdpApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/administracion', {
		templateUrl : 'app/views/administracion.html',
		controller : 'AdministracionCtrl'
	}).when('/cargos', {
		templateUrl : 'app/views/cargos.html',
		controller : 'CargoCtrl'
	}).otherwise({
		templateUrl : 'app/views/home.html'
	});
} ]);
