'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Situacion Revista */
msegErpServices.factory('TipoSituacionRevistaService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoSituacionRevistaService/tipoSituacionRevista/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoSituacionRevistaService/tipoSituacionRevista/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoSituacionRevistaService/tipoSituacionRevista/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoSituacionRevistaService/tipoSituacionRevista/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost(
					'tipoSituacionRevistaService/tipoSituacionRevista/borrar',
					data, success, error);
		}
	};
});