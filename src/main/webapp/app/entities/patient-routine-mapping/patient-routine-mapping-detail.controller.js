(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('PatientRoutineMappingDetailController', PatientRoutineMappingDetailController);

    PatientRoutineMappingDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'PatientRoutineMapping'];

    function PatientRoutineMappingDetailController($scope, $rootScope, $stateParams, previousState, entity, PatientRoutineMapping) {
        var vm = this;

        vm.patientRoutineMapping = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:patientRoutineMappingUpdate', function(event, result) {
            vm.patientRoutineMapping = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
