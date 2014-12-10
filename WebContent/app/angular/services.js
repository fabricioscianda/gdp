'use strict';
var gdpServices = angular.module('gdpServices', []);

/* Tipos Administracion */
gdpServices.factory('TipoAdministracionService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post(
					'tipoAdministracionService/tipoAdministracion/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post(
					'tipoAdministracionService/tipoAdministracion/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post(
					'tipoAdministracionService/tipoAdministracion/encontrar',
					data, success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post(
					'tipoAdministracionService/tipoAdministracion/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post(
					'tipoAdministracionService/tipoAdministracion/borrar',
					data, success, error);
		}
	};
});

/* Tipos Cargo */
gdpServices.factory('TipoCargoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/editar', data,
					success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('tipoCargoService/tipoCargo/borrar', data,
					success, error);
		}
	};
});

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