(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('PatientBookingDetailController', PatientBookingDetailController);

    PatientBookingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PatientBooking'];

    function PatientBookingDetailController($scope, $rootScope, $stateParams, previousState, entity, PatientBooking) {
        var vm = this;

        vm.patientBooking = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:patientBookingUpdate', function(event, result) {
            vm.patientBooking = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
