(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('Routine', Routine);

    Routine.$inject = ['$resource', 'DateUtils'];

    function Routine ($resource, DateUtils) {
        var resourceUrl =  'api/routines/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.creationDate = DateUtils.convertDateTimeFromServer(data.creationDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
