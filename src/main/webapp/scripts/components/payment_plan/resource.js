(function(){
  'use strict';

  angular.module('gymAdminApp')
  	.factory('PaymentPlanResource', function ($resource) {
  	    return $resource('api/paymentplan/:id', {id:'@_id'}, {
  	    	update: {
  	          method: 'PUT' // this method issues a PUT request
  	        }
  	    });
  	});
  
})();