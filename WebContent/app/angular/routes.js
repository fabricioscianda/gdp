'use strict';

angular.module('routes', []).config(
		[ '$routeProvider', function($routeProvider) {

			var routes = [ {
				path : '/tiposAdministracion',
				template : 'app/views/formGenerico.html',
				controller : "TipoAdministracionCtrl"
			}, {
				path : '/tiposCargo',
				template : 'app/views/formGenerico.html',
				controller : "TipoCargoCtrl"
			}, {
				path : '/tiposContacto',
				template : 'app/views/formGenerico.html',
				controller : "TipoContactoCtrl"
			}, {
				path : '/',
				template : 'app/views/home.html',
				controller : null
			} ];

			// for every route definition in the array, creates an Angular route
			// definition
			$.each(routes, function() {
				$routeProvider.when(this.path, {
					templateUrl : this.template,
					controller : this.controller
				});
			});

			// in any other case, redirects to the main view
			$routeProvider.otherwise({
				redirectTo : '/'
			});

		} ]);