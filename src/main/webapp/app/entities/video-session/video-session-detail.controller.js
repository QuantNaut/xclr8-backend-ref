(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('VideoSessionDetailController', VideoSessionDetailController);

    VideoSessionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'VideoSession'];

    function VideoSessionDetailController($scope, $rootScope, $stateParams, previousState, entity, VideoSession) {
        var vm = this;

        vm.videoSession = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:videoSessionUpdate', function(event, result) {
            vm.videoSession = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
