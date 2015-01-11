'use strict'

angular.module('msegErpFilters', []).filter(
		'cuil',
		function() {
			return function(cuil) {
				return cuil.substring(0, 2).concat('-').concat(
						cuil.substring(2, 10)).concat('-').concat(
						cuil.substring(10, 11));
			}
		})