'use strict';

angular.module('gymAdminApp')
    .config(function ($stateProvider) {
        $stateProvider
            .state('payment.list', {
                parent: 'payment',
                url: '/listPayment',
                data: {
                    roles: [ 'ROLE_ADMIN' , 'ROLE_USER'], 
                    pageTitle: 'payment.title.list'
                },
                views: {
                    'content@': {
                        templateUrl: 'app/payment/list/list.html',
                        controller: 'ListPaymentController'
                    }
                },
                resolve: {
                    translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                        $translatePartialLoader.addPart('payment');
                        return $translate.refresh();
                    }]
                }
            });
    });
