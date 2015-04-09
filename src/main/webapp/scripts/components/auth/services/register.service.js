'use strict';

angular.module('gymAdminApp')
    .factory('Register', function ($resource) {
        return $resource('api/register', {}, {
        });
    });


