'use strict';

angular.module('msegErpDirectives', [ 'ng' ])
.directive('modalAlert', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/layout/modal-alert.html'
	};
}).directive('modalConfirm', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/layout/modal-confirm.html'
	};
}).directive('modalLogout', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/layout/modal-logout.html'
	};
}).directive('pageHeader', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/layout/page-header.html'
	};
}).directive('pageFooter', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/layout/page-footer.html'
	};
}).directive('menu', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/layout/menu.html'
	};
}).directive('docenteInfoPersonal', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/docente/partialDocenteInfoPersonal.html'
	};
}).directive('docenteDomicilios', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/docente/partialDocenteDomicilios.html'
	};
}).directive('docenteMediosContacto', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/docente/partialDocenteMediosContacto.html'
	};
}).directive('docenteFormAcademica', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/docente/partialDocenteFormAcademica.html'
	};
}).directive('docenteEmpleos', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/docente/partialDocenteEmpleos.html'
	};
}).directive('docenteInfoAdministrativa', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/docente/partialDocenteInfoAdministrativa.html'
	};
}).directive('docenteListadoActuales', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/docente/partialDocenteListadoActuales.html'
	};
}).directive('docenteListadoActualesDetalle', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/docente/partialDocenteListadoActualesDetalle.html'
	};
}).directive('referencia', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/layout/referencias.html'
	};
}).directive('docenteListadoFiltrado', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/docente/partialDocenteListadoFiltro.html'
	};
}).directive('partialFiltroDocentes', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/partials/busquedas/partialFiltroDocentes.html'
	};
});