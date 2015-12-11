var globalApp = angular.module("globalApp", ['ngRoute']);
globalApp.value("list",{users:[]});
globalApp.constant('WsConfig', {
    socketUrl: '/chat',
    brokerUrl: '/app/chat',
    messagesUrl: '/user/topic/message'
});

