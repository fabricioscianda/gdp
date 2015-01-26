'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Desempeño */
msegErpControllers.controller('DesempenioCtrl', ['$scope', '$filter', 'DesempenioService', 'AsignaturaService', 'DocenteService',  
		function($scope, $filter, DesempenioService, AsignaturaService, DocenteService) {

			$scope.modulo = 'Desempenios';
			$scope.nombreForm = 'Desempeñoo';
			$scope.urlModulo = 'desempenios';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.nuevo = {};
			$scope.desempenio = {};
			$scope.desempenios = {};
			$scope.asignaturaSel = {};
			$scope.asignaturas = {};
			$scope.anios = {};
			$scope.anioSel = null;
			$scope.meses = {};
			$scope.mesSel = null;
			
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
			
			$scope.initListados = function() {
				$scope.listarAsignaturas();
				$scope.listarDocentes();
			}
			
			$scope.listarAsignaturas = function() {
				AsignaturaService.listar({},
					function (response){
						$scope.success = response.ok;
						if (response.ok) {
							 $scope.asignaturas = angular.fromJson(response.data);
							 $scope.asignaturaSel = $scope.asignaturas[-1];
							 $scope.asignaturas = orderBy($scope.asignaturas, 'nombre');
						} else {
							$scope.msgError = 'No se pudieron obtener las Asignaturas';
							console.log('No se pudieron obtener las Asignaturas');
							$('#message-modal').modal('show');
						}
					},
					function(error) {
						alert(error);
					})
			};
			
			$scope.listarDocentes = function() {
				DocenteService.listar({},
					function (response){
					$scope.success = response.ok;
					if (response.ok) {
						$scope.docentes = angular.fromJson(response.data);
						$scope.docenteSel = $scope.docentes[-1];
						$scope.docentes = orderBy($scope.docentes, ['nombre','apellido']);
					} else {
						$scope.msgError = 'No se pudieron obtener los Docentes';
						console.log('No se pudieron obtener los Docentes');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.guardar = function(nuevo) {
				nuevo.docente = $scope.docenteSel;
				nuevo.asignatura = $scope.asignaturaSel; 
				nuevo.anio = $scope.anioSel;
				nuevo.mes = $scope.mesSel;
				if (nuevo != null && nuevo.nombre!=undefined && nuevo.anio!=undefined && nuevo.asignatura!=null) {
					DesempenioService.guardar({
						'nuevo' : nuevo
					}, function(response) {
						$scope.success = response.ok;
						if (response.ok) {
							$scope.msgSuccess = 'Desempeño, Guardado.';
							$scope.asignaturaSel = $scope.asignaturas[-1];
							$scope.docenteSel = $scope.docentes[-1];
							$scope.anioSel = {};
							$scope.mesSel = {};
							$scope.nuevo = {};
							$scope.listar();
						} else {
							$scope.msgError = 'No se pudo guardar.';
							console.log('No se pudo guardar el elemento.');
						}
						$('#message-modal').modal('show');
					}, function(error) {
						alert(error);
					})
				} else {
					$scope.success = false;
					$scope.msgError = 'El nombre y/o el año no pueden ser vacios.';
					$('#message-modal').modal('show');
				}
			};

			$scope.listar = function() {
				DesempenioService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.desempenios = angular.fromJson(response.data);
					} else {
						$scope.msgError = 'No se pudieron obtener las desempenios.';
						$('#message-modal').modal('show');
					}
					;
				}, function(error) {
					alert(error);
				});
			};

			$scope.borrar = function(desempenio) {
				DesempenioService.borrar({
					'id' : desempenio.id
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = desempenio.nombre + ", Borrado.";
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

			$scope.editarElemento = function(desempenio) {
				$scope.nuevo = {};
				$scope.nuevo.id = desempenio.id;
				$scope.nuevo.nombre = desempenio.nombre;
				$scope.nuevo.anio = desempenio.anio;
				$scope.nuevo.mes = desempenio.mes;
				var i = $scope.indiceDe($scope.asignaturas, desempenio.asignatura.id, 'id');
				if (i!=-1) {
					$scope.asignaturaSel = $scope.asignaturas[i];
				} else {
					$scope.msgError = 'Error buscando la asignatura de la desempenio a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				var i = $scope.indiceDe($scope.docentes, desempenio.docente.id, 'id');
				if (i!=-1) {
					$scope.docenteSel = $scope.docentes[i];
				} else {
					$scope.msgError = 'Error buscando al docente del desempeño a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				$scope.colapsarFormulario = false;
			}

			$scope.editar = function(desempenio) {
				$nuevo.asignatura = $scope.asignaturaSel;
				$nuevo.docente = $scope.docenteSel;
				$nuevo.anio = $scope.anioSel;
				$nuevo.mes = $scope.mesSel;
				DesempenioService.editar({
					'nuevo' : nuevo
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = "Desempeño, Guardado.";
						$scope.listar();
					} else {
						$scope.msgError = "No se pudo editar el elemento, " + desempenio.nombre;
					}
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
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
				$scope.asignaturaSel = $scope.asignaturas[-1];
				$scope.docenteSel = $scope.docentes[-1];
			}
			
			$scope.confirmarBorrar = function(desempenio) {
				$scope.obj = desempenio;
				$scope.textoConfirm = "";
				$('#confirm-modal').modal('show');
			}

			$scope.cancelarBorrar = function(desempenio) {
				$('#confirm-modal').modal('hide');
				$scope.textoConfirm = null;
				$scope.desempenio = {};
			}

		} ]);