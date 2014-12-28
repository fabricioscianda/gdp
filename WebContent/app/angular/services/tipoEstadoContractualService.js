'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Estado Contractual */
msegErpServices.factory('TipoEstadoContractualService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoContractualService/tipoEstadoContractual/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoContractualService/tipoEstadoContractual/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoContractualService/tipoEstadoContractual/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoContractualService/tipoEstadoContractual/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoContractualService/tipoEstadoContractual/borrar',
					data, success, error);
		}
	};
});