'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Partidos */
msegErpServices.factory('PartidoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('partidoService/partido/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('partidoService/partido/listar', data, success,
					error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('partidoService/partido/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('partidoService/partido/editar', data, success,
					error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('partidoService/partido/borrar', data, success,
					error);
		}
	};
});