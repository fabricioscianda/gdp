'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Login */
msegErpServices.factory('LoginService', function(srvHttp) {
	return {
		login : function(data, success, error) {
			return srvHttp.post('loginService/login', data, success, error);
		},
		logout : function(data, success, error) {
			return srvHttp.post('loginService/logout', data, success, error);
		},
		buscarDatos : function(data, success, error) {
			return srvHttp.authPost('loginService/datos', data, success, error);
		}
	};
});