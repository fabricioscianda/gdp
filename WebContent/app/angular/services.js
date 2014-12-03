'use strict';
var gdpServices = angular.module('gdpServices');

gdpServices.factory('AdministracionService', function(srvHttp) {
	return {
		guardar : function(data, success, error) {
			return srvHttp.post(
					'administracionService/administracion/guardar', data,
					success, error);
		},
		listar : function(data, success, error) {
			return srvHttp.post(
					'administracionService/administracion/listar', data,
					success, error);
		},
		encontrar : function(data, success, error) {
			return srvHttp.post(
					'administracionService/administracion/encontrar', data,
					success, error);
		},
		modificar : function(data, success, error) {
			return srvHttp.post(
					'administracionService/administracion/modificar', data,
					success, error);
		},
		eliminar : function(data, success, error) {
			return srvHttp.post(
					'administracionService/administracion/eliminar', data,
					success, error);
		}
	};
});