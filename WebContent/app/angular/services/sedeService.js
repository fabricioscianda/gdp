'use strict';
var gdpServices = angular.module('gdpServices');

/* Sedes */
gdpServices.factory('SedeService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('sedeService/sede/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('sedeService/sede/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('sedeService/sede/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('sedeService/sede/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('sedeService/sede/borrar', data, success, error);
		}
	};
});