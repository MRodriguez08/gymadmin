'use strict';

angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('listPlan', {
                parent: 'plan',
                url: '/listPlan',
                data: {
                    roles: [], 
                    pageTitle: 'plan.title'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/plan/list/list.html',
                        controller: 'ListPlanController'
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
