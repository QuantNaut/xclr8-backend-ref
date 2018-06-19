(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('BodySectionDetailController', BodySectionDetailController);

    BodySectionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'BodySection'];

    function BodySectionDetailController($scope, $rootScope, $stateParams, previousState, entity, BodySection) {
        var vm = this;

        vm.bodySection = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:bodySectionUpdate', function(event, result) {
            vm.bodySection = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
