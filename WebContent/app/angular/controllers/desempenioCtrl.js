'use strict';
var msegErpControllers = angular.module('msegErpControllers');

/* Desempeño */
msegErpControllers.controller('DesempenioCtrl', ['$scope', '$filter', 'DesempenioService', 'AsignaturaService', 'DocenteService',  
		function($scope, $filter, DesempenioService, AsignaturaService, DocenteService) {

			$scope.modulo = 'Desempeños';
			$scope.nombreForm = 'Desempeño';
			$scope.urlModulo = 'desempenios';

			$scope.success = null;
			$scope.msgSuccess = null;
			$scope.msgError = null;
			$scope.textoConfirm = null;

			$scope.desempenio = {};
			$scope.desempenios = {};
			$scope.docentes = {};
			$scope.asignaturas = {};
			
			$scope.docenteSel = {};
			$scope.asignaturaSel = {};
			$scope.anioSel = null;
			$scope.mesSel = null;
			$scope.hcs = null;
			$scope.faltas = null;
			
			$scope.meses = [ {
				'value' : 1,
				'nombre' : 'Enero'
			}, {
				'value' : 2,
				'nombre' : 'Febrero'
			}, {
				'value' : 3,
				'nombre' : 'Marzo'
			}, {
				'value' : 4,
				'nombre' : 'Abril'
			}, {
				'value' : 5,
				'nombre' : 'Mayo'
			}, {
				'value' : 6,
				'nombre' : 'Junio'
			}, {
				'value' : 7,
				'nombre' : 'Julio'
			}, {
				'value' : 8,
				'nombre' : 'Agosto'
			}, {
				'value' : 9,
				'nombre' : 'Septiembre'
			}, {
				'value' : 10,
				'nombre' : 'Octubre'
			}, {
				'value' : 11,
				'nombre' : 'Noviembre'
			}, {
				'value' : 12,
				'nombre' : 'Diciembre'
			} ];

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
							$scope.msgError = response.errorMessage;
//							$scope.msgError = 'No se pudieron obtener las Asignaturas';
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
						$scope.msgError = response.errorMessage;
//						$scope.msgError = 'No se pudieron obtener los Docentes';
						console.log('No se pudieron obtener los Docentes');
						$('#message-modal').modal('show');
					}
				},
				function(error) {
					alert(error);
				})
			};
			
			$scope.guardar = function(desempenio) {
				$scope.id_desempenio = ((desempenio != undefined && desempenio != null) ? desempenio.id : null);
				DesempenioService.guardar({
					'id_desempenio' : $scope.id_desempenio,
					'id_docente' : $scope.docenteSel.id,
					'id_asignatura' : $scope.asignaturaSel.id,
					'anio' : $scope.anioSel.getFullYear(),
					'mes' : $scope.mesSel.value,
					'hcs' : $scope.hcs,
					'faltas' : $scope.faltas
				}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.msgSuccess = 'Desempeño, Guardado.';
						$scope.asignaturaSel = $scope.asignaturas[-1];
						$scope.docenteSel = $scope.docentes[-1];
						$scope.anioSel = null;
						$scope.mesSel = null;
						$scope.hcs = null;
						$scope.faltas = null;
						$scope.listar();
					} else {
						$scope.msgError = response.errorMessage;
//						$scope.msgError = 'No se pudo guardar.';
						console.log('No se pudo guardar el elemento.');
					}
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				})
			};

			$scope.listar = function() {
				DesempenioService.listar({}, function(response) {
					$scope.success = response.ok;
					if (response.ok) {
						$scope.desempenios = angular.fromJson(response.data);
					} else {
						$scope.msgError = response.errorMessage;
//						$scope.msgError = 'No se pudieron obtener las desempenios.';
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
						$scope.msgError = response.errorMessage;
//						$scope.msgError = "No se pudo borrar el elemento.";
					}
					$scope.textoConfirm = null;
					$('#confirm-modal').modal('hide');
					$('#message-modal').modal('show');
				}, function(error) {
					alert(error);
				});
			}

			$scope.editarElemento = function(desempenio) {
				$scope.desempenio = desempenio;
				$scope.anioSel = (new Date()).setFullYear(desempenio.anio);
				var i = $scope.indiceDe($scope.meses, desempenio.mes, 'value');
				if (i!=-1) {
					$scope.mesSel = $scope.meses[i];
				} else {
					$scope.msgError = 'Error buscando el mes del desempeño a editar, en el listado.';
					$('#message-modal').modal('show');
				}
				$scope.hcs = desempenio.hcs;
				$scope.faltas = desempenio.faltas;
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
				$scope.desempenio = null;
				$scope.anioSel = null;
				$scope.mesSel = null;
				$scope.hcs = null;
				$scope.faltas = null;
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