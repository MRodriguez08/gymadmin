(function(){
  'use strict';
  
  angular.module('gymAdminApp')
	.factory('PendingImageResource', function ($resource) {
	    return $resource('pendingimagename', {});
	});
  
})();