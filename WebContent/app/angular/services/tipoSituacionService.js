'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Situacion */
msegErpServices.factory('TipoSituacionService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('tipoSituacionService/tipoSituacion/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('tipoSituacionService/tipoSituacion/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('tipoSituacionService/tipoSituacion/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('tipoSituacionService/tipoSituacion/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('tipoSituacionService/tipoSituacion/borrar',
					data, success, error);
		}
	};
});