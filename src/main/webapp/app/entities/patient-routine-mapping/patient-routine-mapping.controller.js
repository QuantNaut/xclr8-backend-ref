(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('PatientRoutineMappingController', PatientRoutineMappingController);

    PatientRoutineMappingController.$inject = ['$scope', '$state', 'PatientRoutineMapping'];

    function PatientRoutineMappingController ($scope, $state, PatientRoutineMapping) {
        var vm = this;

        vm.patientRoutineMappings = [];

        loadAll();

        function loadAll() {
            PatientRoutineMapping.query(function(result) {
                vm.patientRoutineMappings = result;
                vm.searchQuery = null;
            });
        }
    }
})();
