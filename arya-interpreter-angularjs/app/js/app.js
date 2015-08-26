'use strict';

/* App Module */

var aryaApp = angular.module('aryaApp', 
	[ // Dependent modules must be defined here:
	'ngRoute', 
	'aryaControllers',
	'aryaServices',
	'aryaDirectives'
	]);

aryaApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/main', {
		templateUrl : 'partials/arya-main-action.html',
		controller : 'AryaMainActionCtrl'
	}).when('/action/:actionId/requestType/:requestType', {
		templateUrl : 'partials/arya-detail-action.html',
		controller : 'AryaDetailActionCtrl'
	}).otherwise({
		redirectTo : '/main'
	});
} ]);
