'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Documento */
msegErpServices.factory('TipoDocumentoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post(
					'tipoDocumentoService/tipoDocumento/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post(
					'tipoDocumentoService/tipoDocumento/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post(
					'tipoDocumentoService/tipoDocumento/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post(
					'tipoDocumentoService/tipoDocumento/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post(
					'tipoDocumentoService/tipoDocumento/borrar',
					data, success, error);
		}
	};
});