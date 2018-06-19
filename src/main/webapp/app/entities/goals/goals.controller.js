(function() {
    'use strict';

    angular
        .module('xclr8App')
        .controller('GoalsController', GoalsController);

    GoalsController.$inject = ['$scope', '$state', 'Goals'];

    function GoalsController ($scope, $state, Goals) {
        var vm = this;

        vm.goals = [];

        loadAll();

        function loadAll() {
            Goals.query(function(result) {
                vm.goals = result;
                vm.searchQuery = null;
            });
        }
    }
})();
