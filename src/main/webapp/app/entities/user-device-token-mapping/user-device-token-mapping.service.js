(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('UserDeviceTokenMapping', UserDeviceTokenMapping);

    UserDeviceTokenMapping.$inject = ['$resource', 'DateUtils'];

    function UserDeviceTokenMapping ($resource, DateUtils) {
        var resourceUrl =  'api/user-device-token-mappings/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                        data.updatedDate = DateUtils.convertDateTimeFromServer(data.updatedDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
