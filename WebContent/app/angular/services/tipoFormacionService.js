'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Formacion */
msegErpServices.factory('TipoFormacionService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoFormacionService/tipoFormacion/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoFormacionService/tipoFormacion/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoFormacionService/tipoFormacion/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoFormacionService/tipoFormacion/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoFormacionService/tipoFormacion/borrar',
					data, success, error);
		}
	};
});