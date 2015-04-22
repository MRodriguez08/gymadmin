(function(){
  'use strict';

  angular.module('gymAdminApp')
  	.factory('PlanResource', function ($resource) {
  	    return $resource('api/plan/:id', {id:'@_id'}, {
  	    	update: {
  	          method: 'PUT' // this method issues a PUT request
  	        }
  	    });
  	});
  
})();