(function(){	
	'use strict';
	
	angular.module('gymAdminApp')
	    .config(function ($stateProvider) {
	        $stateProvider
	            .state('payment', {
	                abstract: true,
	                parent: 'site'
	            });
	    });

})();