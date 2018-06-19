(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('BodyPartDetailController', BodyPartDetailController);

    BodyPartDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'BodyPart'];

    function BodyPartDetailController($scope, $rootScope, $stateParams, previousState, entity, BodyPart) {
        var vm = this;

        vm.bodyPart = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:bodyPartUpdate', function(event, result) {
            vm.bodyPart = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
