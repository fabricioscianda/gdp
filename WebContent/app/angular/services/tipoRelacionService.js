'use strict';
var gdpServices = angular.module('gdpServices');

/* Tipos Relacion */
gdpServices.factory('TipoRelacionService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('tipoRelacionService/tipoRelacion/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('tipoRelacionService/tipoRelacion/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('tipoRelacionService/tipoRelacion/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('tipoRelacionService/tipoRelacion/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('tipoRelacionService/tipoRelacion/borrar',
					data, success, error);
		}
	};
});