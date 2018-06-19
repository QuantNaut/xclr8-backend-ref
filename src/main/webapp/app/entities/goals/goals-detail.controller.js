(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('GoalsDetailController', GoalsDetailController);

    GoalsDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Goals'];

    function GoalsDetailController($scope, $rootScope, $stateParams, previousState, entity, Goals) {
        var vm = this;

        vm.goals = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:goalsUpdate', function(event, result) {
            vm.goals = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
