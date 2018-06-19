(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('Session', Session);

    Session.$inject = ['$resource', 'DateUtils'];

    function Session ($resource, DateUtils) {
        var resourceUrl =  'api/sessions/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.startDate = DateUtils.convertDateTimeFromServer(data.startDate);
                        data.endDate = DateUtils.convertDateTimeFromServer(data.endDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
