'use strict';

angular.module('gymAdminApp')
    .factory('PlanService', function ($rootScope, $http) {
        return {
            getAll: function () {
                return $http.get('api/plan').then(function (response) {
                    return response.data;
                });
            }
        };
    });
