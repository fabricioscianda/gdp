'use strict';
var msegErpServices = angular.module('msegErpServices');

msegErpServices.factory('srvHttp', function($http, $rootScope, $location, $cookieStore) {

	var s = window.location.protocol + "//" + window.location.host;
	var serviceUrl = s + "/gedo/rest/";
	// var serviceUrl = s + "/rest/";

	var wrapped = function(originalFn) {
		return function(response) {
			if (response.ok == false && response.errorCode == "2222") {
				// error auth.
				$cookieStore.remove("loggedUser");
				$location.path('#/');
				$rootScope.logueado = false;
				$rootScope.error = true;
				$rootScope.errorMessage = response.errorMessage;
			} else {
				// error by otherwise or success.
				originalFn(response);
			}
		};
	};

	return {

		authPost : function(uri, params, successFn, errorFn) {
			if ($rootScope.loggedUser == undefined)
				$rootScope.token = null;
			else
				$rootScope.token = $rootScope.loggedUser.token;
			$http({
				method : 'POST',
				url : serviceUrl + uri,
				data : params,
				headers : {
					'authorization' : $rootScope.token
				}
			}).success(wrapped(successFn)).error(wrapped(errorFn));
		},

		post : function(uri, params, successFn, errorFn) {
			$http({
				method : 'POST',
				url : serviceUrl + uri,
				data : params
			}).success(successFn).error(errorFn);
		},

		authFilePost : function(uri, params, successFn, errorFn) {
			if ($rootScope.loggedUser == undefined)
				$rootScope.token = null;
			else
				$rootScope.token = $rootScope.loggedUser.token;
			$http({
				method : 'POST',
				url : serviceUrl + uri,
				data : params,
				headers : {
					'authorization' : $rootScope.token,
					'responseType' : 'arraybuffer'
				}
			}).success(wrapped(successFn)).error(wrapped(errorFn));
		}
	};

});
