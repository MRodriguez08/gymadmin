(function(){
  'use strict';

  angular.module('gymAdminApp')
  	.factory('PaymentResource', function ($resource) {
  	    return $resource('api/payment/:id', 
    		{
  	    		id:'@_id',
  	    		customerName:'@customerName',
  	    		stateId:'@stateId',
			}, 
			{
    	    update: {
              method: 'PUT' // this method issues a PUT request
            }
  	    });
  	});
  
})();