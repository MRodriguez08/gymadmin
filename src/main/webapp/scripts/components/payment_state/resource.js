(function(){
  'use strict';

  angular.module('gymAdminApp')
  	.factory('PaymentStateResource', function ($resource) {
  	    return $resource('api/paymentstate/:id', {id:'@_id'}, {
  	    });
  	});
  
})();