'use strict';

angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('plan.list', {
                parent: 'plan',
                url: '/listPlan',
                data: {
                    roles: [], 
                    pageTitle: 'plan.title.list'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/plan/list/list.html',
                        controller: 'ListPlanController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('plan');
                        return $translate.refresh();
                    }]
                }
            });
    });
