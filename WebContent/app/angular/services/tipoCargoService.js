'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Cargo */
msegErpServices.factory('TipoCargoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('tipoCargoService/tipoCargo/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('tipoCargoService/tipoCargo/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('tipoCargoService/tipoCargo/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('tipoCargoService/tipoCargo/editar', data,
					success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('tipoCargoService/tipoCargo/borrar', data,
					success, error);
		}
	};
});