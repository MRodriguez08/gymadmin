(function(){
  'use strict';

  angular.module('gymAdminApp')
    .factory('CustomerService', ['$rootScope', '$http', 'Upload', 'CustomerResource', 'PendingImageResource', function ($rootScope, $http, Upload, CustomerResource, PendingImageResource) {
        return {
        	getImageSrc: function(img){
        		return img != null ? 'images/customers/' + img : 'images/unknown-user.png';
        	},
            getAll: function (data, callback) {
                var cb = callback || angular.noop;

                return CustomerResource.query(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            getPendingImage: function (data, callback) {
                var cb = callback || angular.noop;

                return PendingImageResource.get(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            get: function (data, callback) {
                var cb = callback || angular.noop;

                return CustomerResource.get(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            uploadImage: function (img, onProgress, onSuccess) {

                return Upload.upload({
    	            url: 'imageupload',
    	            data: {}, // additional data to send
    	            file: img,
    	        })
    	        .progress(onProgress)
    	        .success(onSuccess);
            },
            create: function (data, callback) {
                var cb = callback || angular.noop;

                return CustomerResource.save(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            update: function (data, callback) {
                var cb = callback || angular.noop;

                return CustomerResource.update(data,
                    function (response) {
                        return cb(response);
                    },
                    function (err) {
                        return cb(err);
                    }.bind(this)).$promise;
            },
            delete: function (data, callback) {
                var cb = callback || angular.noop;

                return CustomerResource.delete(data,
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