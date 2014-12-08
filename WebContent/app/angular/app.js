'use strict';

var gdpApp = angular.module('gdpApp', [ 'ngRoute', 'gdpControllers',
		'gdpServices' ]);

gdpApp.config([ '$routeProvider', function($routeProvider) {

	$routeProvider
	
	.when('/tiposAdministracion', {
		templateUrl : 'app/views/formGenerico.html',
		controller : 'TipoAdministracionCtrl'
	})
	
	.when('/tiposCargo', {
		templateUrl : 'app/views/formGenerico.html',
		controller : 'TipoCargoCtrl'
	})
	
	.when('/tiposContacto', {
		templateUrl : 'app/views/formGenerico.html',
		controller : 'TipoContactoCtrl'
	})
	
	.otherwise({
		templateUrl : 'app/views/home.html'
	});
	
} ]);
