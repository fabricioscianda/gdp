'use strict';

var msegErpApp = angular.module('msegErpApp', [ 'ngRoute', 'routes', 'msegErpControllers', 'msegErpServices', 'msegErpDirectives', 'msegErpFilters', 'ui.bootstrap']);

msegErpApp.config([ '$routeProvider', function($routeProvider) {} ]);
msegErpApp.run(function($rootScope) {
	$rootScope.sysVersion = "1.0";
})