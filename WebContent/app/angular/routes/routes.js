'use strict';

angular.module('routes', []).config([ '$routeProvider', function($routeProvider) {

			var routes = [ {
				path : '/partidos',
				template : 'app/views/partidos.html',
				controller : "PartidoCtrl"
			}, {
				path : '/provincias',
				template : 'app/views/provincias.html',
				controller : "ProvinciaCtrl"
			}, {
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
				path : '/tiposDocumento',
				template : 'app/views/formGenerico.html',
				controller : "TipoDocumentoCtrl"
			}, {
				path : '/tiposEstadoContractual',
				template : 'app/views/formGenerico.html',
				controller : "TipoEstadoContractualCtrl"
			}, {
				path : '/tiposEstadoFormacion',
				template : 'app/views/formGenerico.html',
				controller : "TipoEstadoFormacionCtrl"
			}, {
				path : '/tiposFormacion',
				template : 'app/views/formGenerico.html',
				controller : "TipoFormacionCtrl"
			}, {
				path : '/tiposMotivo',
				template : 'app/views/formGenerico.html',
				controller : "TipoMotivoCtrl"
			}, {
				path : '/tiposPersonal',
				template : 'app/views/formGenerico.html',
				controller : "TipoPersonalCtrl"
			}, {
				path : '/tiposRelacion',
				template : 'app/views/formGenerico.html',
				controller : "TipoRelacionCtrl"
			}, {
				path : '/tiposSituacion',
				template : 'app/views/formGenerico.html',
				controller : "TipoSituacionCtrl"
			}, {
				path : '/tiposSituacionRevista',
				template : 'app/views/formGenerico.html',
				controller : "TipoSituacionRevistaCtrl"
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