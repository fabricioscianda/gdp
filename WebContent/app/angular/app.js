'use strict';

var msegErpApp = angular.module('msegErpApp', [ 'ngRoute', 'ngCookies', 'routes', 'msegErpControllers', 'msegErpServices', 'msegErpDirectives', 'msegErpFilters', 'ui.bootstrap']);

msegErpApp.config([ '$routeProvider', function($routeProvider) {} ]);
msegErpApp.run(function($rootScope, $location, $cookieStore) {
	
	$rootScope.sysVersion = "1.0";
	$rootScope.sysName = "GeDo";
	$rootScope.sysModule = "Formación";
	$rootScope.contactMail = "fabricio.scianda@gmail.com";
	$rootScope.contactName = "Fabricio Scianda";
	$rootScope.loginName = $cookieStore.get("loginName");
	$rootScope.esAdmin = $cookieStore.get("esAdmin");
	
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
	
	/* INDICE DE */
	$rootScope.indiceDe = function (array, cadena, propiedad) {
		for(var i = 0, len = array.length; i < len; i++) {
			if (array[i][propiedad] === cadena) 
				return i;
		}
		return -1;
	}
	
	/* LISTA CHECKEABLE */
	$rootScope.getCheckList = function (array) {
		for (var i = 0, len = array.length; i < len; i++) {
			array[i]['checked'] = false;
		}
		return array;
	}
	
});