'use strict';
var msegErpServices = angular.module('msegErpServices');

/* Usuarios */
msegErpServices.factory('UsuarioService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.authPost('usuarioService/usuario/guardar', data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.authPost('usuarioService/usuario/listar', data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.authPost('usuarioService/usuario/encontrar', data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.authPost('usuarioService/usuario/borrar', data, success, error);
		}
	};
});