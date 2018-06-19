(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('body-section', {
            parent: 'entity',
            url: '/body-section',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'BodySections'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/body-section/body-sections.html',
                    controller: 'BodySectionController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('body-section-detail', {
            parent: 'entity',
            url: '/body-section/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'BodySection'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/body-section/body-section-detail.html',
                    controller: 'BodySectionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'BodySection', function($stateParams, BodySection) {
                    return BodySection.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'body-section',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('body-section-detail.edit', {
            parent: 'body-section-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/body-section/body-section-dialog.html',
                    controller: 'BodySectionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BodySection', function(BodySection) {
                            return BodySection.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('body-section.new', {
            parent: 'body-section',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/body-section/body-section-dialog.html',
                    controller: 'BodySectionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                bodySection: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('body-section', null, { reload: 'body-section' });
                }, function() {
                    $state.go('body-section');
                });
            }]
        })
        .state('body-section.edit', {
            parent: 'body-section',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/body-section/body-section-dialog.html',
                    controller: 'BodySectionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BodySection', function(BodySection) {
                            return BodySection.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('body-section', null, { reload: 'body-section' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('body-section.delete', {
            parent: 'body-section',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/body-section/body-section-delete-dialog.html',
                    controller: 'BodySectionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['BodySection', function(BodySection) {
                            return BodySection.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('body-section', null, { reload: 'body-section' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
