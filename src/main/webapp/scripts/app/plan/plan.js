'use strict';

angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('plan', {
                abstract: true,
                parent: 'site'
            });
    });
