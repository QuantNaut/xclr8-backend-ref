(function() {
    'use strict';
    angular
        .module('xclr8App')
        .factory('PatientBooking', PatientBooking);

    PatientBooking.$inject = ['$resource', 'DateUtils'];

    function PatientBooking ($resource, DateUtils) {
        var resourceUrl =  'api/patient-bookings/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.bookingDateAndTime = DateUtils.convertDateTimeFromServer(data.bookingDateAndTime);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
