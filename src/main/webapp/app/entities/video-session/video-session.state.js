(function() {
    'use strict';

    angular
        .module('xclr8App')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('video-session', {
            parent: 'entity',
            url: '/video-session',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'VideoSessions'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/video-session/video-sessions.html',
                    controller: 'VideoSessionController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
            }
        })
        .state('video-session-detail', {
            parent: 'entity',
            url: '/video-session/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'VideoSession'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/video-session/video-session-detail.html',
                    controller: 'VideoSessionDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'VideoSession', function($stateParams, VideoSession) {
                    return VideoSession.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'video-session',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('video-session-detail.edit', {
            parent: 'video-session-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/video-session/video-session-dialog.html',
                    controller: 'VideoSessionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['VideoSession', function(VideoSession) {
                            return VideoSession.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('video-session.new', {
            parent: 'video-session',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/video-session/video-session-dialog.html',
                    controller: 'VideoSessionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                doctorId: null,
                                patientId: null,
                                videoSessionToken: null,
                                videoSessionId: null,
                                createdDate: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('video-session', null, { reload: 'video-session' });
                }, function() {
                    $state.go('video-session');
                });
            }]
        })
        .state('video-session.edit', {
            parent: 'video-session',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/video-session/video-session-dialog.html',
                    controller: 'VideoSessionDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['VideoSession', function(VideoSession) {
                            return VideoSession.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('video-session', null, { reload: 'video-session' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('video-session.delete', {
            parent: 'video-session',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/video-session/video-session-delete-dialog.html',
                    controller: 'VideoSessionDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['VideoSession', function(VideoSession) {
                            return VideoSession.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('video-session', null, { reload: 'video-session' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
