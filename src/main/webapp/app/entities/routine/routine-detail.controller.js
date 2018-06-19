(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('RoutineDetailController', RoutineDetailController);

    RoutineDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Routine'];

    function RoutineDetailController($scope, $rootScope, $stateParams, previousState, entity, Routine) {
        var vm = this;

        vm.routine = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:routineUpdate', function(event, result) {
            vm.routine = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
