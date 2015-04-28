(function(){
  'use strict';

  angular.module('gymAdminApp')
    .factory('PaymentPlanService', ['$rootScope', '$http', 'PaymentPlanResource', function ($rootScope, $http, PaymentPlanResource) {
        return {
            getAll: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentPlanResource.query(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            get: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentPlanResource.get(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            create: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentPlanResource.save(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            update: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentPlanResource.update(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            delete: function (data, callback) {
                var cb = callback || angular.noop;

                return PaymentPlanResource.delete(data,
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