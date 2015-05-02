(function(){
  'use strict';

  angular.module('gymAdminApp')
    .factory('PaymentStateService', ['$rootScope', '$http', 'PaymentStateResource', function ($rootScope, $http, PaymentStateResource) {
        return {
            getAll: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentStateResource.query(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            }
        };
    }]);
  
})();