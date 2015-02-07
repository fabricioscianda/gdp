'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Contacto */
msegErpServices.factory('ContactoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('contactoService/contacto/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('contactoService/contacto/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('contactoService/contacto/encontrar', data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('contactoService/contacto/editar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('contactoService/contacto/borrar', data, success, error);
		}
	};
});