'use strict';
var msegErpServices = angular.module('msegErpServices');
//var msegErpServices = angular.module('msegErpServices', []);

msegErpServices.factory('srvHttp', function($http, $rootScope, $location) {

	var s = window.location.protocol + "//" + window.location.host;
	var serviceUrl = s + "/msegErp/rest/";

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