(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('NotesDetailController', NotesDetailController);

    NotesDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Notes'];

    function NotesDetailController($scope, $rootScope, $stateParams, previousState, entity, Notes) {
        var vm = this;

        vm.notes = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('xclr8App:notesUpdate', function(event, result) {
            vm.notes = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
