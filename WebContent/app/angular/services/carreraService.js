'use strict';
var gdpServices = angular.module('gdpServices');

/* Carreras */
gdpServices.factory('CarreraService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('carreraService/carrera/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('carreraService/carrera/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('carreraService/carrera/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('carreraService/carrera/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('carreraService/carrera/borrar', data, success, error);
		}
	};
});