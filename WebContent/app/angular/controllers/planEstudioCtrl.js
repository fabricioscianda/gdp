'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Plan Estudio */
msegErpControllers.controller('PlanEstudioCtrl', ['$scope', '$filter', 'PlanEstudioService',  
		function($scope, $filter, PlanEstudioService) {

			$scope.modulo = 'Planes de Estudio';
			$scope.nombreForm = 'Plan de Estudio';
			$scope.urlModulo = 'planesEstudio';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.nuevo = {};
			$scope.planEstudio = {};
			$scope.planesEstudio = {};
			$scope.anioSel = null;

			$scope.colapsarFormulario = true;

			$scope.obj = {};
			
			var orderBy = $filter('orderBy');

			/*
			 * datetime picker
			 */
			$scope.today = function() {
				$scope.anioSel = new Date();
			};
			
			$scope.clear = function() {
				$scope.anioSel = null;
			};

			// Disable weekend selection
			$scope.disabled = function(date, mode) {
				return (mode === 'day' && (date.getDay() === 0 || date.getDay() === 6));
			};

			$scope.toggleMax = function() {
				$scope.maxDate = $scope.maxDate ? null : new Date();
			};
			
			$scope.toggleMax();

			$scope.openAnio = function($event) {
				$event.preventDefault();
				$event.stopPropagation();
				$scope.openedAnio = true;
			};
			
			$scope.dateOptions = {
				formatYear : 'yy',
				startingDay : 1
			};
			
			$scope.formats = [ 'dd-MMMM-yyyy', 'yyyy/MM/dd', 'dd.MM.yyyy', 'shortDate', 'yyyy' ];
			
			$scope.formatYearOnly = $scope.formats[4];
			
			$scope.guardar = function(nuevo) {
				if (nuevo != null && nuevo.nombre != undefined) {
					PlanEstudioService.guardar({
						'id' : nuevo.id, 
						'nombre' : nuevo.nombre,
						'anio' : $scope.anioSel.getTime(),
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = nuevo.nombre + ', Guardado.';
							$scope.listar();
							$scope.cerrarForm();
						} else {
							$scope.msgError = response.errorMessage;
							console.log('No se pudo guardar el elemento.');
						}
						$('#message-modal').modal('show');
					}, function(error) {
						alert(error);
					})
				} else {
					$scope.success = false;
					$scope.msgError = 'El nombre no puede ser vacio.';
					$('#message-modal').modal('show');
				}
			};

			$scope.listar = function() {
				PlanEstudioService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.planesEstudio = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener los planes de estudio.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(planEstudio) {
				PlanEstudioService.borrar({
					'id' : planEstudio.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = planEstudio.nombre + " de " + planEstudio.anio + ", Borrado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo borrar el elemento.";
					}
					$scope.textoConfirm = null;
					$('#confirm-modal').modal('hide');
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.editarElemento = function(planEstudio) {
				$scope.nuevo = {};
				$scope.nuevo.id = planEstudio.id;
				$scope.nuevo.nombre = planEstudio.nombre;
				$scope.anioSel = new Date();
				$scope.anioSel.setFullYear(planEstudio.anio);
				$scope.colapsarFormulario = false;
			}

			$scope.indiceDe = function (array, cadena, propiedad) {
			    for(var i = 0, len = array.length; i < len; i++) {
			        if (array[i][propiedad] === cadena) 
			        	return i;
			    }
			    return -1;
			}
			
			$scope.cerrarForm = function() {
				$scope.limpiar();
				$scope.colapsarFormulario = true;
			}
			
			$scope.limpiar = function() {
				$scope.nuevo = {};
				$scope.planEstudio.anio = {};
				$scope.anioSel = null;
			}
			
			$scope.confirmarBorrar = function(planEstudio) {
				$scope.obj = planEstudio;
				$scope.textoConfirm = (planEstudio.nombre + " de " + planEstudio.anio);
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(planEstudio) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.planEstudio = {};
			}
			
		} ]);