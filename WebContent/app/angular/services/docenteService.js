'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Docentes */
msegErpServices.factory('DocenteService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('docenteService/docente/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('docenteService/docente/listar', data, success, error);
		},
		listarMinimo : function(data, success, error) {
			return srvHttp.authPost('docenteService/docente/listarMinimo', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('docenteService/docente/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('docenteService/docente/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('docenteService/docente/borrar', data, success, error);
		}
	};
});