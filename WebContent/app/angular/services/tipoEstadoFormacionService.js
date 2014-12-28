'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Estado Formacion */
msegErpServices.factory('TipoEstadoFormacionService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoFormacionService/tipoEstadoFormacion/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoFormacionService/tipoEstadoFormacion/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoFormacionService/tipoEstadoFormacion/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoFormacionService/tipoEstadoFormacion/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post(
					'tipoEstadoFormacionService/tipoEstadoFormacion/borrar',
					data, success, error);
		}
	};
});