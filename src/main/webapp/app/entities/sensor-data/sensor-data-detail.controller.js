(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('SensorDataDetailController', SensorDataDetailController);

    SensorDataDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'SensorData'];

    function SensorDataDetailController($scope, $rootScope, $stateParams, previousState, entity, SensorData) {
        var vm = this;

        vm.sensorData = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:sensorDataUpdate', function(event, result) {
            vm.sensorData = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
