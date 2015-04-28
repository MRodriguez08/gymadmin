(function(){
  'use strict';

  angular.module('gymAdminApp')
  	.factory('PaymentResource', function ($resource) {
  	    return $resource('api/payment/:id', {id:'@_id'}, {
  	    	update: {
  	          method: 'PUT' // this method issues a PUT request
  	        }
  	    });
  	});
  
})();