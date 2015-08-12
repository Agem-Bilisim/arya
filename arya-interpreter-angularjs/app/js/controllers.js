'use strict';

/* Controllers */

var aryaControllers = angular.module('aryaControllers', []);

aryaControllers.controller('AryaMainActionCtrl', [ '$scope', 'AryaService',
	function($scope, AryaService) {

	    $scope.aryaRequest = {
		action : 'main',
		requestType : 'V'
	    };

	    AryaService.getMetadata($scope.aryaRequest, function(data) {
		$scope.aryaMetadata = data;
	    });

	} ]);
