'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Personal */
msegErpServices.factory('TipoPersonalService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('tipoPersonalService/tipoPersonal/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('tipoPersonalService/tipoPersonal/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('tipoPersonalService/tipoPersonal/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('tipoPersonalService/tipoPersonal/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('tipoPersonalService/tipoPersonal/borrar',
					data, success, error);
		}
	};
});