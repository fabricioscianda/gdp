'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Cargo */
msegErpServices.factory('TipoCargoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/editar', data,
					success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/borrar', data,
					success, error);
		}
	};
});