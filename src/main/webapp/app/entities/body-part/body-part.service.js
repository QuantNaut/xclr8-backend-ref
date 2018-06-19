(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('BodyPart', BodyPart);

    BodyPart.$inject = ['$resource'];

    function BodyPart ($resource) {
        var resourceUrl =  'api/body-parts/:id';

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
