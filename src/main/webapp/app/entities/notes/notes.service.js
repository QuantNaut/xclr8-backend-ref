(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('Notes', Notes);

    Notes.$inject = ['$resource'];

    function Notes ($resource) {
        var resourceUrl =  'api/notes/:id';

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
