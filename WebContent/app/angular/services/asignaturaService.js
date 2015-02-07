'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Asignaturas */
msegErpServices.factory('AsignaturaService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('asignaturaService/asignatura/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('asignaturaService/asignatura/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('asignaturaService/asignatura/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('asignaturaService/asignatura/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('asignaturaService/asignatura/borrar', data, success, error);
		}
	};
});