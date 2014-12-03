'use strict';
var gdpServices = angular.module('gdpServices', []);

gdpServices.factory('srvHttp', function($http, $rootScope, $location) {

	var s = window.location.protocol + "//" + window.location.host;
	var serviceUrl = s + "/gdp/rest/";

	return {
		post : function(uri, params, successFn, errorFn) {
			$http({
				method : 'POST',
				url : serviceUrl + uri,
				data : params
			}).success(successFn).error(errorFn);
		}
	};

});