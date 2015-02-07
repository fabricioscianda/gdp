'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Institutos */
msegErpServices.factory('InstitutoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('institutoService/instituto/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('institutoService/instituto/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('institutoService/instituto/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('institutoService/instituto/editar', data,
					success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('institutoService/instituto/borrar', data,
					success, error);
		}
	};
});