(function(){
  'use strict';

  angular.module('gymAdminApp')
  	.factory('CustomerResource', function ($resource) {
  	    return $resource('api/customer/:id', {id:'@_id'}, {
  	    	update: {
  	          method: 'PUT' // this method issues a PUT request
  	        }
  	    });
  	});
  
})();