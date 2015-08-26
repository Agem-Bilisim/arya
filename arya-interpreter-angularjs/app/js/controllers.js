'use strict';

/* Controllers */

var aryaControllers = angular.module('aryaControllers', []);

aryaControllers.controller('AryaMainActionCtrl', [ '$scope', 'AryaService',
	function($scope, AryaService) {

		$scope.aryaRequest = {
			action : 'master',
			requestType : 'VIEW_ONLY'
		};

		AryaService.getMetadata($scope.aryaRequest, function(data) {
			$scope.aryaMetadata = data;
		});

	} ]);
	