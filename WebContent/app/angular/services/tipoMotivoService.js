'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Motivo */
msegErpServices.factory('TipoMotivoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('tipoMotivoService/tipoMotivo/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('tipoMotivoService/tipoMotivo/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('tipoMotivoService/tipoMotivo/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('tipoMotivoService/tipoMotivo/editar', data,
					success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('tipoMotivoService/tipoMotivo/borrar', data,
					success, error);
		}
	};
});