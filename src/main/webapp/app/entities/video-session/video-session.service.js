(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('VideoSession', VideoSession);

    VideoSession.$inject = ['$resource', 'DateUtils'];

    function VideoSession ($resource, DateUtils) {
        var resourceUrl =  'api/video-sessions/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
