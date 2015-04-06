'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Busquedas */
msegErpControllers.controller('BusquedaCtrl', [
		'$scope',
		'$rootScope',
		'$filter',
		'DocenteService',
		'TipoDocumentoService',
		'TipoContactoService',
		'DomicilioService',
		'LocalidadService',
		'PartidoService',
		'ProvinciaService',
		'TipoFormacionService',
		'TipoCargoService',
		'TipoAdministracionService',
		'TipoRelacionService',
		'TipoEstadoFormacionService',
		'TipoPersonalService',
		'TipoEstadoContractualService',
		'TipoSituacionService',
		'TipoSituacionRevistaService',
		'TipoMotivoService',
		function(
				$scope, $rootScope, $filter, DocenteService, TipoDocumentoService,
				TipoContactoService, DomicilioService, LocalidadService,
				PartidoService, ProvinciaService, TipoFormacionService,
				TipoCargoService, TipoAdministracionService,
				TipoRelacionService, TipoEstadoFormacionService,
				TipoPersonalService, TipoEstadoContractualService,
				TipoSituacionService, TipoSituacionRevistaService,
				TipoMotivoService) {

			$scope.modulo = 'Búsquedas';
			$scope.urlModulo = 'busquedas';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.edad = null;
			$scope.legajo = null;
			$scope.fechaAlta = null;
			$scope.antiguedad = null;
			$scope.tiposPersonal = null; 
			$scope.tiposPersonalSel = {}; 
			$scope.tiposMotivo = null;
			$scope.tiposMotivoSel = {};
			$scope.tiposSituacionActual = null;
			$scope.tiposSituacionActualSel = {};
			$scope.tiposSituacionRevista = null;
			$scope.tiposSituacionRevistaSel = {};
			$scope.tiposFormacion = null;
			$scope.tiposFormacionSel = {};
			$scope.tiposEstadoContractual = null;
			$scope.tiposEstadoContractualSel = {};
			$scope.localidades = null;
			$scope.localSel = null;
			$scope.partidos = null;
			$scope.partidoSel = null;
			$scope.provincias = null;
			$scope.provinciaSel = null;

			var orderBy = $filter('orderBy');

			/* configuracion para el spinner */
			var opts = {
				lines : 7, // The number of lines to draw
				length : 1, // The length of each line
				width : 9, // The line thickness
				radius : 9, // The radius of the inner circle
				corners : 1, // Corner roundness (0..1)
				rotate : 0, // The rotation offset
				direction : 1, // 1: clockwise, -1: counterclockwise
				color : '#000', // #rgb or #rrggbb or array of colors
				speed : 1.0, // Rounds per second
				trail : 60, // Afterglow percentage
				shadow : false, // Whether to render a shadow
				hwaccel : false, // Whether to use hardware acceleration
				className : 'spinner', // The CSS class to assign to the spinner
				zIndex : 2e9, // The z-index (defaults to 2000000000)
				top : '50%', // Top position relative to parent
				left : '50%' // Left position relative to parent
			};
			
			/*
			 * datetime picker
			 */
			$scope.today = function() {
				$scope.fechaNac = new Date();
			};
			
			$scope.clear = function() {
				$scope.fechaNac = null;
			};

			// Disable weekend selection
			$scope.disabled = function(date, mode) {
				return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
			};

			$scope.toggleMax = function() {
				$scope.maxDate = $scope.maxDate ? null : new Date();
			};
			
			$scope.toggleMax();

			$scope.openFechaAlta = function($event) {
				$event.preventDefault();
				$event.stopPropagation();
				$scope.openedFechaAlta = true;
			};
			
			$scope.dateOptions = {
				formatYear : 'yy',
				startingDay : 1
			};
			
			$scope.formats = [ 'dd-MMMM-yyyy', 'dd/MM/yyyy', 'dd.MM.yyyy', 'shortDate', 'yyyy' ];
			$scope.format = $scope.formats[1];
			
			$scope.listarTiposPersonal = function() {
				var target = document.getElementById('spinTP');
				var spinner = new Spinner(opts).spin(target);
				TipoPersonalService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposPersonal = angular.fromJson(response.data);
						$scope.tiposPersonal = orderBy($scope.tiposPersonal, 'nombre');
						$scope.tiposPersonal = $rootScope.getCheckList($scope.tiposPersonal);
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Personal');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
				spinner.stop();
			};
			
			$scope.listarTiposMotivo = function() {
				var target = document.getElementById('spinTM');
				var spinner = new Spinner(opts).spin(target);
				TipoMotivoService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposMotivo = angular.fromJson(response.data);
						$scope.tiposMotivo = orderBy($scope.tiposMotivo, 'nombre');
						$scope.tiposMotivo = $rootScope.getCheckList($scope.tiposMotivo);
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Motivo');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
				spinner.stop();
			};
			
			$scope.listarTiposSituacionActual = function() {
				var target = document.getElementById('spinTSA');
				var spinner = new Spinner(opts).spin(target);
				TipoSituacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposSituacionActual = angular.fromJson(response.data);
						$scope.tiposSituacionActual = orderBy($scope.tiposSituacionActual, 'nombre');
						$scope.tiposSituacionActual = $rootScope.getCheckList($scope.tiposSituacionActual);
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Situacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
				spinner.stop();
			};
			
			$scope.listarTiposSituacionRevista = function() {
				var target = document.getElementById('spinTSR');
				var spinner = new Spinner(opts).spin(target);
				TipoSituacionRevistaService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposSituacionRevista = angular.fromJson(response.data);
						$scope.tiposSituacionRevista = orderBy($scope.tiposSituacionRevista, 'nombre');
						$scope.tiposSituacionRevista = $rootScope.getCheckList($scope.tiposSituacionRevista);
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Situacion de Revista');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
				spinner.stop();
			};
			
			$scope.listarTiposFormacion = function() {
				var target = document.getElementById('spinTF');
				var spinner = new Spinner(opts).spin(target);
				TipoFormacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposFormacion = angular.fromJson(response.data);
						$scope.tiposFormacion = orderBy($scope.tiposFormacion, 'nombre');
						$scope.tiposFormacion = $rootScope.getCheckList($scope.tiposFormacion);
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Formacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
				spinner.stop();
			};
			
			$scope.listarTiposEstadoFormacion = function() {
				var target = document.getElementById('spinTEF');
				var spinner = new Spinner(opts).spin(target);
				TipoEstadoFormacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposEstadoFormacion = angular.fromJson(response.data);
						$scope.tiposEstadoFormacion = orderBy($scope.tiposEstadoFormacion, 'nombre');
						$scope.tiposEstadoFormacion = $rootScope.getCheckList($scope.tiposEstadoFormacion);
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Estados de Formacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
				spinner.stop();
			};
			
			$scope.listarTiposEstadoContractual = function() {
				var target = document.getElementById('spinTEC');
				var spinner = new Spinner(opts).spin(target);
				TipoEstadoContractualService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposEstadoContractual = angular.fromJson(response.data);
						$scope.tiposEstadoContractual = orderBy($scope.tiposEstadoContractual, 'nombre');
						$scope.tiposEstadoContractual = $rootScope.getCheckList($scope.tiposEstadoContractual);
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Estados Contractuales');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
				spinner.stop();
			};
			
			$scope.listarLocalidades = function() {
				var target = document.getElementById('spinL');
				var spinner = new Spinner(opts).spin(target);
				LocalidadService.listar({}, 
					function (response){
						$scope.success = response.ok;
						if (response.ok) {
							$scope.localidades = angular.fromJson(response.data);
							$scope.localidades = orderBy($scope.localidades, ['nombre']);
						} else {
							$scope.msgError = response.errorMessage;
							console.log('No se pudieron obtener las Localidades');
							$('#message-modal').modal('show');
						}
					},
					function(error) {
						alert(error);
					})
					spinner.stop();
			};

			$scope.listarPartidos = function() {
				var target = document.getElementById('spinPa');
				var spinner = new Spinner(opts).spin(target);
				PartidoService.listar({}, 
					function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.partidos = angular.fromJson(response.data);
							$scope.partidos = orderBy($scope.partidos, 'nombre');
						} else {
							$scope.msgError = response.errorMessage;
							console.log('No se pudieron obtener las Partidos');
							$('#message-modal').modal('show');
						}
					}, function(error) {
						alert(error);
					});
				spinner.stop();
			};
			
			$scope.listarProvincias = function() {
				var target = document.getElementById('spinPr');
				var spinner = new Spinner(opts).spin(target);
				ProvinciaService.listar({}, 
					function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.provincias = angular.fromJson(response.data);
							$scope.provincias = orderBy($scope.provincias, 'nombre');
						} else {
							$scope.msgError = response.errorMessage;
							console.log('No se pudieron obtener las Provincias');
							$('#message-modal').modal('show');
						}
					}, function(error) {
						alert(error);
					});
				spinner.stop();
			};
			
			$scope.filtrarTabDocente = function() {
				var target = document.getElementById('spinDocentesListadoFiltro');
				var spinner = new Spinner(opts).spin(target);
				var fa = 0;
				if ($scope.fechaAlta != null) {
					fa = $scope.fechaAlta.getTime();
				}
				DocenteService.encontrarFiltrado({
						'edad' : ($scope.edad != null && $scope.edad != undefined) ? $scope.edad : 0,
						'legajo' : ($scope.legajo != null && $scope.legajo != undefined) ? $scope.legajo : '',
						'fechaAlta' : fa,
						'antiguedad' : ($scope.antiguedad != null && $scope.antiguedad != undefined) ? $scope.antiguedad : 0,
						'provinciaSel' : ($scope.provinciaSel != undefined && $scope.provinciaSel != null) ? $scope.provinciaSel.id : 0,
						'partidoSel' : ($scope.partidoSel != undefined && $scope.partidoSel != null) ? $scope.partidoSel.id : 0,
						'localSel' : ($scope.localSel != undefined && $scope.localSel != null) ? $scope.localSel.id : 0,
						'tiposPersonal' : $scope.tiposPersonal,
						'tiposSituacionRevista' : $scope.tiposSituacionRevista,
						'tiposSituacionActual' : $scope.tiposSituacionActual,
						'tiposMotivo' : $scope.tiposMotivo,
						'tiposFormacion' : $scope.tiposFormacion,
						'tiposEstadoContractual' : $scope.tiposEstadoContractual
					},
					function(response){
						$scope.success = response.ok;
						if (response.ok) {
							$scope.docentes = angular.fromJson(response.data);
						} else {
							$scope.msgError = response.errorMessage;
							console.log(response.errorMessage);
							$('#message-modal').modal('show');
						}
					},
					function(error) {
						alert(error);
					});
				spinner.stop();
			};
			
		} ]);