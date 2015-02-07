'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Relacion */
msegErpServices.factory('TipoRelacionService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('tipoRelacionService/tipoRelacion/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('tipoRelacionService/tipoRelacion/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('tipoRelacionService/tipoRelacion/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('tipoRelacionService/tipoRelacion/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('tipoRelacionService/tipoRelacion/borrar',
					data, success, error);
		}
	};
});