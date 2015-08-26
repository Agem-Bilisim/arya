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
					scope.aryaMetadata.viewJson[id] = { 'id': id, 'value': val };
					var attrs = appendAttrs(this, ['id', 'class', 'height', 'onClick']);
					htmlStr += '<div class="col-md-3"><label ' + attrs + ' >{{aryaMetadata.viewJson.' + id +'.value}}</label></div>';
				} else if (thisTagName == 'textbox') {
					scope.aryaMetadata.viewJson[id] = { 'id': id, 'value': val };
					if (hasAttr(this, "rows")) { // Textarea
						var attrs = appendAttrs(this, ['id', 'class', 'height', 'readonly', 'maxlength', 'placeholder', 'mandatory', 'onChange', 'rows']);
						htmlStr += '<div class="col-md-2"><textarea ' + attrs + '>{{aryaMetadata.viewJson.' + id +'.value}}</textarea></div>';
					}
					else { // Password or Text
						var attrs = appendAttrs(this, ['id', 'class', 'height', 'readonly', 'maxlength', 'placeholder', 'mandatory', 'onChange']);
						htmlStr += '<div class="col-md-2"><input type="' + (hasAttr(this, "type") && $(this).attr("type") == "password" ? "password" : "text") + '" id="{{aryaMetadata.viewJson.' + id + '.id}}" value="{{aryaMetadata.viewJson.' + id + '.value}}" /></div>';
					}
				} else if (thisTagName == 'button') {
					scope.aryaMetadata.viewJson[id] = { 'id': id, 'value': val };
					var attrs = appendAttrs(this, ['id', 'class', 'height', 'onClick']);
					htmlStr += '<div class="col-md-5"><button type="button" ' + attrs + ' >' + $(this).attr("label") + '</button></div>';
				} else if (thisTagName == 'checkbox') {
					scope.aryaMetadata.viewJson[id] = { 'id': id, 'value': val };
					var attrs = appendAttrs(this, ['id', 'class', 'height', 'mandatory', 'readonly', 'onCheck']);
					htmlStr += '<div class="col-md-2"><input type="checkbox" ' + attrs + ' value="{{aryaMetadata.viewJson.' + id +'.value}}" /></div>';
				} else if (thisTagName == 'datebox') {
					if (typeof val == undefined) {
						var today = new Date();
						val = today.getFullYear() + '-' + (today.getMonth()+1) + '-' + today.getDate();
					}
					scope.aryaMetadata.viewJson[id] = { 'id': id, 'value': val };
					var attrs = appendAttrs(this, ['id', 'class', 'mandatory', 'readonly', 'onChange']);
					htmlStr += '<div class="col-md-2"><input type="date" ' + attrs + ' value="{{aryaMetadata.viewJson.' + id +'.value}}" /></div>';
				} else if (thisTagName == 'listbox') {
					scope.aryaMetadata.viewJson[id] = { 'id': id, 'value': val };
					var attrs = appendAttrs(this, ['id', 'class', 'height', 'mandatory', 'readonly', 'onSelect']);
					htmlStr += '<div class="col-md-2"><select multiple ' + attrs + ' >' + populateOptions(this, "listitem") + '</select></div>';
				} else if (thisTagName == 'combobox') {
					scope.aryaMetadata.viewJson[id] = { 'id': id, 'value': val };
					var attrs = appendAttrs(this, ['id', 'class', 'height', 'mandatory', 'readonly', 'onSelect']);
					htmlStr += '<div class="col-md-2"><select ' + attrs + ' >' + populateOptions(this, "comboitem") + '</select></div>';
				}

				// TODO append script tag!
		});

			return htmlStr;
		};

		var appendAttrs = function(element, attrArray) {
			var attrs = '';
			if (typeof attrArray !== typeof undefined) {
				for (var i = 0; i < attrArray.length; i++) {
					if (hasAttr(element, attrArray[i])) {
						attrs += ' ' + attrArray[i] + '="' + $(element).attr(attrArray[i]) + '" ';
					}
				}
			}
			return attrs;
		};

		var hasAttr = function(element, attrName) {
			var attr = $(element).attr(attrName);
			return typeof attr !== typeof undefined && attr !== false;
		};

		var populateOptions = function(element, childName) {
			var htmlStr = '';
			$(childName, $(element)).each(function(){
				htmlStr += '<option value="' + $(this).attr("value") + '">' + $(this).attr("label") + '</option>';
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
