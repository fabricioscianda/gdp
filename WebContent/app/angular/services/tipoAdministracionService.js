'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Administracion */
msegErpServices.factory('TipoAdministracionService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoAdministracionService/tipoAdministracion/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoAdministracionService/tipoAdministracion/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoAdministracionService/tipoAdministracion/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoAdministracionService/tipoAdministracion/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoAdministracionService/tipoAdministracion/borrar',
					data, success, error);
		}
	};
});