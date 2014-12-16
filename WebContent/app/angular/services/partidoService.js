'use strict';
var gdpServices = angular.module('gdpServices');

/* Partidos */
gdpServices.factory('PartidoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('partidoService/partido/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('partidoService/partido/listar', data, success,
					error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('partidoService/partido/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('partidoService/partido/editar', data, success,
					error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('partidoService/partido/borrar', data, success,
					error);
		}
	};
});