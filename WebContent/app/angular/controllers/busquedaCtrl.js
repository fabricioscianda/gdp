'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Busquedas */
msegErpControllers.controller('BusquedaCtrl', [
		'$scope',
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
		function($scope, $filter, DocenteService, TipoDocumentoService,
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
			$scope.tiposMotivo = null;
			$scope.tiposSituacionActual = null;
			$scope.tiposSituacionRevista = null;
			$scope.tiposFormacion = null;
			$scope.tiposEstadoContractual = null;
			$scope.localidades = null;
			$scope.localSel = null;
			$scope.partidos = null;
			$scope.partidoSel = null;
			$scope.provincias = null;
			$scope.provinciaSel = null;

			var orderBy = $filter('orderBy');
			
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
				TipoPersonalService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposPersonal = angular.fromJson(response.data);
						$scope.tiposPersonal = orderBy($scope.tiposPersonal, 'nombre');
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Personal');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposMotivo = function() {
				TipoMotivoService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposMotivo = angular.fromJson(response.data);
						$scope.tiposMotivo = orderBy($scope.tiposMotivo, 'nombre');
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Motivo');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposSituacionActual = function() {
				TipoSituacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposSituacionActual = angular.fromJson(response.data);
						$scope.tiposSituacionActual = orderBy($scope.tiposSituacionActual, 'nombre');
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Situacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposSituacionRevista = function() {
				TipoSituacionRevistaService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposSituacionRevista = angular.fromJson(response.data);
						$scope.tiposSituacionRevista = orderBy($scope.tiposSituacionRevista, 'nombre');
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Situacion de Revista');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposFormacion = function() {
				TipoFormacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposFormacion = angular.fromJson(response.data);
						$scope.tiposFormacion = orderBy($scope.tiposFormacion, 'nombre');
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Tipos de Formacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposEstadoFormacion = function() {
				TipoEstadoFormacionService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposEstadoFormacion = angular.fromJson(response.data);
						$scope.tiposEstadoFormacion = orderBy($scope.tiposEstadoFormacion, 'nombre');
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Estados de Formacion');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarTiposEstadoContractual = function() {
				TipoEstadoContractualService.listar({}, function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.tiposEstadoContractual = angular.fromJson(response.data);
						$scope.tiposEstadoContractual = orderBy($scope.tiposEstadoContractual, 'nombre');
					} else {
						$scope.msgError = response.errorMessage;
						console.log('No se pudieron obtener los Estados Contractuales');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.listarLocalidades = function() {
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
			};

			$scope.listarPartidos = function() {
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
			};
			
			$scope.listarProvincias = function() {
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
			};
			
		} ]);