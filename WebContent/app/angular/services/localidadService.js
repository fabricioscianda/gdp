'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Localidades */
msegErpServices.factory('LocalidadService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('localidadService/localidad/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('localidadService/localidad/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('localidadService/localidad/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('localidadService/localidad/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('localidadService/localidad/borrar', data, success, error);
		}
	};
});