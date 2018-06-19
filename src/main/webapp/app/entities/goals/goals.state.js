(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('goals', {
            parent: 'entity',
            url: '/goals',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Goals'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/goals/goals.html',
                    controller: 'GoalsController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('goals-detail', {
            parent: 'entity',
            url: '/goals/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Goals'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/goals/goals-detail.html',
                    controller: 'GoalsDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Goals', function($stateParams, Goals) {
                    return Goals.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'goals',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('goals-detail.edit', {
            parent: 'goals-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/goals/goals-dialog.html',
                    controller: 'GoalsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Goals', function(Goals) {
                            return Goals.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('goals.new', {
            parent: 'goals',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/goals/goals-dialog.html',
                    controller: 'GoalsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                sessionId: null,
                                doctorId: null,
                                patientId: null,
                                routineId: null,
                                frequencyOfExercise: null,
                                repetitionOfExercise: null,
                                degreeOfMotion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('goals', null, { reload: 'goals' });
                }, function() {
                    $state.go('goals');
                });
            }]
        })
        .state('goals.edit', {
            parent: 'goals',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/goals/goals-dialog.html',
                    controller: 'GoalsDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Goals', function(Goals) {
                            return Goals.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('goals', null, { reload: 'goals' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('goals.delete', {
            parent: 'goals',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/goals/goals-delete-dialog.html',
                    controller: 'GoalsDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Goals', function(Goals) {
                            return Goals.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('goals', null, { reload: 'goals' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
