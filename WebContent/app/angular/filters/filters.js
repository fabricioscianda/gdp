'use strict'

var msegErpFilters = angular.module('msegErpFilters', []);

msegErpFilters.filter('cuil', function() {
	return function(cuil) {
		return cuil.substring(0, 2).concat('-').concat(cuil.substring(2, 10)).concat('-').concat(cuil.substring(10, 11));
	}
});
msegErpFilters.filter('antiguedad', function() {
	return function(fecha) {
		var fechaActual = new Date();
		return (fechaActual.getFullYear() - (new Date(fecha)).getFullYear()); 
	}
});
msegErpFilters.filter('includeFromProvincia', function() {
		return function(inputArray, filterCriteria) {
		// if the value of filterCriteria is "falsy", retain the
		// inputArray as it is
		if (inputArray != null && inputArray != undefined) {
				if (filterCriteria != null && filterCriteria != undefined) {
				return inputArray.filter(function(item) {
					// then check if the currently checked item in the
					// inputArray is different from the filterCriteria,
					// if so, keep it in the filtered results
					return !filterCriteria || angular.equals(item.provincia.nombre, filterCriteria);
				});
			} else if ((filterCriteria == undefined || filterCriteria == null) && (inputArray.length != undefined) && (inputArray.length != 0)) {
				return inputArray.filter(function(item) {
					return false;
				});
			}
		}
		return true;
	};
});
msegErpFilters.filter('includeFromPartido', function() {
	return function(inputArray, filterCriteria) {
		// if the value of filterCriteria is "falsy", retain the
		// inputArray as it is
		if (inputArray != null && inputArray != undefined) {
				if (filterCriteria != null && filterCriteria != undefined) {
				return inputArray.filter(function(item) {
					// then check if the currently checked item in the
					// inputArray is different from the filterCriteria,
					// if so, keep it in the filtered results
					return !filterCriteria || angular.equals(item.partido.nombre, filterCriteria);
				});
			} else if ((filterCriteria == undefined || filterCriteria == null) && (inputArray.length != undefined) && (inputArray.length != 0)) {
				return inputArray.filter(function(item) {
					return false;
				});
			}
		}
		return true;
	};
});