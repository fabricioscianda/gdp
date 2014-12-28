'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Provincias */
msegErpServices.factory('ProvinciaService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('provinciaService/provincia/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('provinciaService/provincia/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('provinciaService/provincia/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('provinciaService/provincia/editar', data,
					success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('provinciaService/provincia/borrar', data,
					success, error);
		}
	};
});