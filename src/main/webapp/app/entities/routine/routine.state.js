(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('routine', {
            parent: 'entity',
            url: '/routine?page&sort&search',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Routines'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/routine/routines.html',
                    controller: 'RoutineController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('routine-detail', {
            parent: 'entity',
            url: '/routine/{id}',
            data: {
                authorities: ['ROLE_ADMIN'],
                pageTitle: 'Routine'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/routine/routine-detail.html',
                    controller: 'RoutineDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Routine', function($stateParams, Routine) {
                    return Routine.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'routine',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('routine-detail.edit', {
            parent: 'routine-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/routine/routine-dialog.html',
                    controller: 'RoutineDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Routine', function(Routine) {
                            return Routine.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('routine.new', {
            parent: 'routine',
            url: '/new',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/routine/routine-dialog.html',
                    controller: 'RoutineDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                routineName: null,
                                createdBy: null,
                                creationDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('routine', null, { reload: 'routine' });
                }, function() {
                    $state.go('routine');
                });
            }]
        })
        .state('routine.edit', {
            parent: 'routine',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/routine/routine-dialog.html',
                    controller: 'RoutineDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Routine', function(Routine) {
                            return Routine.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('routine', null, { reload: 'routine' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('routine.delete', {
            parent: 'routine',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_ADMIN']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/routine/routine-delete-dialog.html',
                    controller: 'RoutineDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Routine', function(Routine) {
                            return Routine.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('routine', null, { reload: 'routine' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
