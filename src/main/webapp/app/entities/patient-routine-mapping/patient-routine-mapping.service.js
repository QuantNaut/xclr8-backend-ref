(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('PatientRoutineMapping', PatientRoutineMapping);

    PatientRoutineMapping.$inject = ['$resource', 'DateUtils'];

    function PatientRoutineMapping ($resource, DateUtils) {
        var resourceUrl =  'api/patient-routine-mappings/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.createdDate = DateUtils.convertDateTimeFromServer(data.createdDate);
                        data.modifiedDate = DateUtils.convertDateTimeFromServer(data.modifiedDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
