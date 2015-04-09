'use strict';

angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('plan_list', {
                parent: 'plan',
                url: '/plan_list',
                data: {
                    roles: [], 
                    pageTitle: 'plan.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/plan/list/list.html',
                        controller: 'PlanController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('plan_list');
                        return $translate.refresh();
                    }]
                }
            });
    });
