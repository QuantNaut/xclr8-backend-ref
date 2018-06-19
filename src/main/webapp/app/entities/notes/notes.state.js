(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('notes', {
            parent: 'entity',
            url: '/notes?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Notes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/notes/notes.html',
                    controller: 'NotesController',
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
                }],
            }
        })
        .state('notes-detail', {
            parent: 'entity',
            url: '/notes/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Notes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/notes/notes-detail.html',
                    controller: 'NotesDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Notes', function($stateParams, Notes) {
                    return Notes.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'notes',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('notes-detail.edit', {
            parent: 'notes-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notes/notes-dialog.html',
                    controller: 'NotesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Notes', function(Notes) {
                            return Notes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('notes.new', {
            parent: 'notes',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notes/notes-dialog.html',
                    controller: 'NotesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                header: null,
                                currentCondition: null,
                                goals: null,
                                subjective: null,
                                objective: null,
                                assessment: null,
                                plan: null,
                                patientId: null,
                                routineId: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('notes', null, { reload: 'notes' });
                }, function() {
                    $state.go('notes');
                });
            }]
        })
        .state('notes.edit', {
            parent: 'notes',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notes/notes-dialog.html',
                    controller: 'NotesDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Notes', function(Notes) {
                            return Notes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('notes', null, { reload: 'notes' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('notes.delete', {
            parent: 'notes',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/notes/notes-delete-dialog.html',
                    controller: 'NotesDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Notes', function(Notes) {
                            return Notes.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('notes', null, { reload: 'notes' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
