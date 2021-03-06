'use strict';

angular.module('routes', []).config(
		[ '$routeProvider', function($routeProvider) {

			var routes = [ {
				path : '/',
				template : 'app/views/layout/home.html',
				controller : "LoginCtrl"
			}, {
				path : '/desempenios',
				template : 'app/views/desempenios.html',
				controller : "DesempenioCtrl"
			}, {
				path : '/asignaturas',
				template : 'app/views/asignaturas.html',
				controller : "AsignaturaCtrl"
			}, {
				path : '/busquedas',
				template : 'app/views/busquedas.html',
				controller : "BusquedaCtrl"
			}, {
				path : '/carreras',
				template : 'app/views/carreras.html',
				controller : "CarreraCtrl"
			}, {
				path : '/docentes',
				template : 'app/views/docentes.html',
				controller : "DocenteCtrl"
			}, {
				path : '/institutos',
				template : 'app/views/institutos.html',
				controller : "InstitutoCtrl"
			}, {
				path : '/localidades',
				template : 'app/views/localidades.html',
				controller : "LocalidadCtrl"
			}, {
				path : '/login',
				template : 'app/views/login.html',
				controller : "LoginCtrl"
			}, {
				path : '/logout',
				template : 'app/views/login.html',
				controller : "LoginCtrl"
			}, {
				path : '/partidos',
				template : 'app/views/partidos.html',
				controller : "PartidoCtrl"
			}, {
				path : '/planesEstudio',
				template : 'app/views/planesEstudio.html',
				controller : "PlanEstudioCtrl"
			}, {
				path : '/provincias',
				template : 'app/views/provincias.html',
				controller : "ProvinciaCtrl"
			}, {
				path : '/sedes',
				template : 'app/views/sedes.html',
				controller : "SedeCtrl"
			}, {
				path : '/tiposAdministracion',
				template : 'app/views/formTipos.html',
				controller : "TipoAdministracionCtrl"
			}, {
				path : '/tiposCargo',
				template : 'app/views/formTipos.html',
				controller : "TipoCargoCtrl"
			}, {
				path : '/tiposContacto',
				template : 'app/views/formTipos.html',
				controller : "TipoContactoCtrl"
			}, {
				path : '/tiposDocumento',
				template : 'app/views/formTipos.html',
				controller : "TipoDocumentoCtrl"
			}, {
				path : '/tiposEstadoContractual',
				template : 'app/views/formTipos.html',
				controller : "TipoEstadoContractualCtrl"
			}, {
				path : '/tiposEstadoFormacion',
				template : 'app/views/formTipos.html',
				controller : "TipoEstadoFormacionCtrl"
			}, {
				path : '/tiposFormacion',
				template : 'app/views/formTipos.html',
				controller : "TipoFormacionCtrl"
			}, {
				path : '/tiposMotivo',
				template : 'app/views/formTipos.html',
				controller : "TipoMotivoCtrl"
			}, {
				path : '/tiposPersonal',
				template : 'app/views/formTipos.html',
				controller : "TipoPersonalCtrl"
			}, {
				path : '/tiposRelacion',
				template : 'app/views/formTipos.html',
				controller : "TipoRelacionCtrl"
			}, {
				path : '/tiposSituacion',
				template : 'app/views/formTipos.html',
				controller : "TipoSituacionCtrl"
			}, {
				path : '/tiposSituacionRevista',
				template : 'app/views/formTipos.html',
				controller : "TipoSituacionRevistaCtrl"
			}, {
				path : '/usuarios',
				template : 'app/views/usuarios.html',
				controller : "UsuarioCtrl"
			}];

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