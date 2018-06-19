(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('Goals', Goals);

    Goals.$inject = ['$resource'];

    function Goals ($resource) {
        var resourceUrl =  'api/goals/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
