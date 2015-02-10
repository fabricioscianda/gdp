'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Planes de Estudio */
msegErpServices.factory('PlanEstudioService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('planEstudioService/planEstudio/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('planEstudioService/planEstudio/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('planEstudioService/planEstudio/encontrar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('planEstudioService/planEstudio/borrar', data, success, error);
		}
	};
});