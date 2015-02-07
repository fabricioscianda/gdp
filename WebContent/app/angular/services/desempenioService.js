'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Desempeños */
msegErpServices.factory('DesempenioService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('desempenioService/desempenio/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('desempenioService/desempenio/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('desempenioService/desempenio/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('desempenioService/desempenio/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('desempenioService/desempenio/borrar', data, success, error);
		}
	};
});