'use strict';
var gdpServices = angular.module('gdpServices');

/* Tipos Contacto */
gdpServices.factory('TipoContactoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('tipoContactoService/tipoContacto/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('tipoContactoService/tipoContacto/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('tipoContactoService/tipoContacto/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('tipoContactoService/tipoContacto/editar', data,
					success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('tipoContactoService/tipoContacto/borrar', data,
					success, error);
		}
	};
});