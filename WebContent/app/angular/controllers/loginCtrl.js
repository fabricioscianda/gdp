'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Login */
msegErpControllers.controller( 'LoginCtrl', [ '$route', '$location', '$scope', '$rootScope', '$cookieStore', '$filter', 'LoginService',

  function($route, $location, $scope, $rootScope, $cookieStore, $filter, LoginService) {

		$scope.usuario = {};
		$rootScope.logoutMessage = "Usuario no autenticado";

		$scope.login = function() {
			if ($scope.usuario.username != undefined 
			&& $scope.usuario.username != null
			&& $scope.usuario.password != undefined
			&& $scope.usuario.password != null) {

				$rootScope.error = false;

				var usuario = $scope.usuario;
				LoginService.login(usuario, function(response) {
					if (response.ok) {
						var jsonData = angular.fromJson(response.data);
						$rootScope.esAdmin = jsonData.esAdmin;
						delete jsonData.esAdmin;
						$rootScope.loginName = jsonData.loginName;
						delete jsonData.loginName;
						$rootScope.loggedUser = jsonData;
						jsonData = null;
						$cookieStore.put("loggedUser", $rootScope.loggedUser);
						$cookieStore.put("loginName", $rootScope.loginName);
						$cookieStore.put("esAdmin", $rootScope.esAdmin);
						$rootScope.logueado = true;
						$rootScope.logInfo = false;
						$location.path("/");
					} else {
						if (response.errorCode == "111") {
							$rootScope.error = true;
							$rootScope.errorMessage = response.errorMessage;
						} else {
							$rootScope.logInfo = true;
						}
					}
				}, function(error) {
					alert(error);
				});
			} else {
				$rootScope.error = true;
				$rootScope.errorMessage = "Datos incompletos. Debe ingresar usuario y contrasentilde;a.";
			}
		};

		$scope.logout = function() {
			LoginService.logout({}, function(response) {
				if (response.ok) {
					$cookieStore.remove("loggedUser");
					$cookieStore.remove("esAdmin");
					$cookieStore.remove("loginName");
					$rootScope.logueado = false;
					$rootScope.loggedUser = null;
					$scope.usuario = {};
					$rootScope.usuario = {};
					$rootScope.logInfo = true;
					$('#modal-logout').modal('hide');
					$location.path("#/login");
				} else {
					$rootScope.error = true;
					$rootScope.errorMessage = response.errorMessage;
				}
			}, function(error) {
				alert(error);
			});
		};

		$scope.confirmarLogout = function() {
			$('#modal-logout').modal('show');
		}
		
	} ]);