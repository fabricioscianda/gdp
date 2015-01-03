'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Personas */
msegErpServices.factory('PersonaService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('personaService/persona/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('personaService/persona/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('personaService/persona/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('personaService/persona/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('personaService/persona/borrar', data, success, error);
		}
	};
});