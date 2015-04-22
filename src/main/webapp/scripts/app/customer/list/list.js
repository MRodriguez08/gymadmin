'use strict';

angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('customer.list', {
                parent: 'customer',
                url: '/listCustomer',
                data: {
                    roles: [], 
                    pageTitle: 'customer.title.list'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/customer/list/list.html',
                        controller: 'ListCustomerController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('customer');
                        return $translate.refresh();
                    }]
                }
            });
    });