'use strict';
var gdpServices = angular.module('gdpServices');

/* Tipos Personal */
gdpServices.factory('TipoPersonalService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('tipoPersonalService/tipoPersonal/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('tipoPersonalService/tipoPersonal/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('tipoPersonalService/tipoPersonal/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('tipoPersonalService/tipoPersonal/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('tipoPersonalService/tipoPersonal/borrar',
					data, success, error);
		}
	};
});