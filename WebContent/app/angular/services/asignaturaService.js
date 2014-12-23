'use strict';
var gdpServices = angular.module('gdpServices');

/* Asignaturas */
gdpServices.factory('AsignaturaService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('asignaturaService/asignatura/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('asignaturaService/asignatura/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('asignaturaService/asignatura/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('asignaturaService/asignatura/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('asignaturaService/asignatura/borrar', data, success, error);
		}
	};
});