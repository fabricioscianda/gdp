'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Sedes */
msegErpServices.factory('SedeService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('sedeService/sede/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('sedeService/sede/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('sedeService/sede/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('sedeService/sede/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('sedeService/sede/borrar', data, success, error);
		}
	};
});