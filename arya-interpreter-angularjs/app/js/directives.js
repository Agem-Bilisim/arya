'use strict';

/* Directives */

var aryaDirectives = angular.module('aryaDirectives', []);

aryaDirectives.directive('aryaDynamicTemplate',
	function($compile, AryaService) {
	    var createDynamicTemplate = function(scope) {

		var htmlStr = '';
		scope.aryaMetadata.viewJson = {};

		$("window", scope.aryaMetadata.view).children().each(function(i) {
		    
		    var thisTagName = $(this).prop('tagName').toLowerCase();
		    var id = $(this).attr('id');
		    var val = $(this).attr('value');
		    
		    if (thisTagName == 'label') {
			scope.aryaMetadata.viewJson[id] = {'id': id, 'value': val, 'type': 'label'};
			htmlStr += '<label id="{{aryaMetadata.viewJson.' + id +'.id}}">{{aryaMetadata.viewJson.' + id +'.value}}</label>';
		    } else if (thisTagName == 'textbox') {
			scope.aryaMetadata.viewJson[id] = {'id': id, 'value': val, 'type': 'textbox'};
			htmlStr += '<input type="text" id="{{aryaMetadata.viewJson.' + id + '.id}}" value="{{aryaMetadata.viewJson.' + id + '.value}}" />';
		    }
		    
		    // TODO other elements...
		    
		});

		return htmlStr;
	    };

	    var linker = function(scope, element, attrs) {
		console.log("AryaRequest: " + JSON.stringify(scope.aryaRequest));
		AryaService.getMetadata(scope.aryaRequest, function(data) {
		    scope.aryaMetadata = data;
		    element.append(createDynamicTemplate(scope));
		    $compile(element.contents())(scope);
		});
	    };

	    return {
		restrict: 'E',
		replace: true,
		link: linker,
		scope: true
	    };
	});
