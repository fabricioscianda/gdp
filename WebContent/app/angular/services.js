'use strict';
var gdpServices = angular.module('gdpServices');

/* Tipos Administracion */
gdpServices.factory('AdministracionService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('administracionService/administracion/guardar',
					data, success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('administracionService/administracion/listar',
					data, success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post(
					'administracionService/administracion/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('administracionService/administracion/editar',
					data, success, error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('administracionService/administracion/borrar',
					data, success, error);
		}
	};
});

/* Tipos Cargo */
gdpServices.factory('CargoService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post('cargoService/cargo/guardar', data, success,
					error);
		},
		listar : function(data, success, error) {
			return srvHttp.post('cargoService/cargo/listar', data, success,
					error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post('cargoService/cargo/encontrar', data, success,
					error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post('cargoService/cargo/editar', data, success,
					error);
		},
		borrar : function(data, success, error) {
			return srvHttp.post('cargoService/cargo/borrar', data, success,
					error);
		}
	};
});