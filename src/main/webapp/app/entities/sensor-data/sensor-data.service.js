(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('SensorData', SensorData);

    SensorData.$inject = ['$resource', 'DateUtils'];

    function SensorData ($resource, DateUtils) {
        var resourceUrl =  'api/sensor-data/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dateAndTime = DateUtils.convertDateTimeFromServer(data.dateAndTime);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
