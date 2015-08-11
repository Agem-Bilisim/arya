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
			if (!scope.aryaMetadata.viewJson.label) {
			    scope.aryaMetadata.viewJson.label = {};
			}
			scope.aryaMetadata.viewJson.label[id] = {'id': id, 'value': val};
			// Test
			scope.aryaMetadata.viewJson.test = 'Emre';
			htmlStr += 'name: {{scope.aryaMetadata.viewJson.test}}';
			//
			htmlStr += '<label id="{{aryaMetadata.viewJson.label[' + id +'].id}}">{{aryaMetadata.viewJson.label[' + id +'].value}}</label>'
		    } else if (thisTagName == 'textbox') {
			if (!scope.aryaMetadata.viewJson.input) {
			    scope.aryaMetadata.viewJson.input = {};
			}
			scope.aryaMetadata.viewJson.input[' + id + '] = {'id': id, 'value': val};
			htmlStr += '<input type="text" id="{{aryaMetadata.viewJson.input[' + id + '].id}}" value="{{aryaMetadata.viewJson.input[' + id + '].value}}" />';
		    }
		    // TODO other inputs
		});

		console.log(JSON.stringify(scope.aryaMetadata.viewJson));
		return htmlStr;

	    };

	    var linker = function(scope, element, attrs) {

		AryaService.getMetadata({
		    action : 'main',
		    requestType : 'V'
		}, function(data) {
		    scope.aryaMetadata = data;
		    element.append(createDynamicTemplate(scope));
		    $compile(element.contents())(scope);
		});

	    };

	    return {
		restrict : 'E',
		link : linker,
		scope: true
	    };
	});
