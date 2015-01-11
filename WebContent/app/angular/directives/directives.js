'use strict';

angular.module('msegErpDirectives', [ 'ng' ])
.directive('modalAlert', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/layout/modal-alert.html'
	};
})
.directive('modalConfirm', function() {
	return {
		restrict : 'E',
		templateUrl : 'app/views/layout/modal-confirm.html'
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
});