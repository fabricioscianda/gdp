'use strict';

var msegErpApp = angular.module('msegErpApp', [ 'ngRoute', 'ngCookies', 'routes', 'msegErpControllers', 'msegErpServices', 'msegErpDirectives', 'msegErpFilters', 'ui.bootstrap']);

msegErpApp.config([ '$routeProvider', function($routeProvider) {} ]);
msegErpApp.run(function($rootScope, $location, $cookieStore) {
	
	$rootScope.sysVersion = "1.0";
	
	$rootScope.$on('$routeChangeStart', function(event, next, current) {
		var loggedUser = $cookieStore.get("loggedUser");
		if (loggedUser == undefined || loggedUser.token == null) {
			$rootScope.logueado = false;
			$rootScope.loggedUser = null;
			$location.path("/login");
		} else {
			$rootScope.logueado = true;
			$rootScope.logInfo = false;
			$rootScope.loggedUser = loggedUser;
			$rootScope.error = false;
		}
	});
	
})