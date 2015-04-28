(function(){
  'use strict';

  angular.module('gymAdminApp')
    .factory('PaymentService', ['$rootScope', '$http', 'PaymentResource', function ($rootScope, $http, PaymentResource) {
        return {
        	getFinalCost: function(cost , discount){
        		if (discount === 0)
        			return cost;
        		
        		return cost - (discount / 100 * cost);
        	},
            getAll: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentResource.query(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            get: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentResource.get(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            create: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentResource.save(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            update: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentResource.update(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            delete: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentResource.delete(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
        };
    }]);
  
})();