'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Motivo */
msegErpServices.factory('TipoMotivoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('tipoMotivoService/tipoMotivo/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('tipoMotivoService/tipoMotivo/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('tipoMotivoService/tipoMotivo/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('tipoMotivoService/tipoMotivo/editar', data,
					success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('tipoMotivoService/tipoMotivo/borrar', data,
					success, error);
		}
	};
});