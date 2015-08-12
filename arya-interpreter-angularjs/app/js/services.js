'use strict';

/* Services */

var aryaServices = angular.module('aryaServices', [ 'ngResource' ]);

aryaServices.factory('AryaService', [ '$http', function($http) {
    return {
	
	//
	// getMetadata is the main function which can be used to communicate with arya-gateway
	//
	getMetadata: function(aryaRequest, callback) {
	    $http({
		url: 'http://localhost:8080/arya/rest/hello',
		data: aryaRequest, // e.g. {action : 'main', requestType : 'V' }
		method: 'POST',
                transformResponse: function(data) {
                    var xmlDoc = $.parseXML(data);
                    var xmlView = $.parseXML($("view", xmlDoc).text());
                    if (xmlView) {
                	console.log("[AryaService] Metadata - VIEW: " + $(xmlView));
                    }
                    var xmlData = $.parseXML($("data", xmlDoc).text());
                    if (xmlData) {
                	console.log("[AryaService] Metadata - DATA: " + $(xmlView));
                    }
                    return { 'view': xmlView, 'data': xmlData };
                }
	    }).
            then(function(response) {
        	console.log("[AryaService] Request succeeded.");
        	if (typeof callback === "function") {
        	    callback(response.data);
        	}
            }, function(response) {
                console.log("[AryaService] Request failed. Status Code: " + response.status + " Status: " + response.statusText);
            });
	}
    
    }
} ]);
