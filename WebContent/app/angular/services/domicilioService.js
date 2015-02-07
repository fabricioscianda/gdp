'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Domicilio */
msegErpServices.factory('DomicilioService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('domicilioService/domicilio/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('domicilioService/domicilio/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('domicilioService/domicilio/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('domicilioService/domicilio/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('domicilioService/domicilio/borrar', data, success, error);
		}
	};
});