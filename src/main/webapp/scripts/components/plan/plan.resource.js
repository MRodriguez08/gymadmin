(function(){
  'use strict';

  angular.module('gymAdminApp')
  	.factory('PlanResource', function ($resource) {
  	    return $resource('api/plan', {}, {
  	    });
  	});
  
})();