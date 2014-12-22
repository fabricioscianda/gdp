'use strict';
var gdpServices = angular.module('gdpServices');

/* Institutos */
gdpServices.factory('InstitutoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('institutoService/instituto/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('institutoService/instituto/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('institutoService/instituto/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('institutoService/instituto/editar', data,
					success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('institutoService/instituto/borrar', data,
					success, error);
		}
	};
});