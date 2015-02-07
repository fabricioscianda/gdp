'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Tipos Contacto */
msegErpServices.factory('TipoContactoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('tipoContactoService/tipoContacto/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('tipoContactoService/tipoContacto/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('tipoContactoService/tipoContacto/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.authPost('tipoContactoService/tipoContacto/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('tipoContactoService/tipoContacto/borrar',
					data, success, error);
		}
	};
});