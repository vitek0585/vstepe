angular.module("globalApp").factory("dialogService", function ($q, WsConfig) {
    var socket;
    var stomp;
    var listener = $q.defer();
    var ident;

    var init = function (identifer) {
        ident = identifer;
        socket = new SockJS(WsConfig.socketUrl);
        stomp = Stomp.over(socket);
        stomp.connect({}, listen);
    };

    var getMessage = function (message) {
        message = JSON.parse(message.body);
        return message;
    }

    var listen = function () {
        console.log(WsConfig.messagesUrl + "/" + ident);
        ///topic/message
        stomp.subscribe(WsConfig.messagesUrl + "/" + ident, function (data) {
            listener.notify(getMessage(data));
        });
    };

    var send = function (dialog) {

        stomp.send(WsConfig.brokerUrl, {
            priority: 9
        }, JSON.stringify({
            text: dialog.text,
            senderId: dialog.current,
            ownerId: dialog.id
        }));
    };

    var addMessageListener = function (callback) {
        listener.promise.then(null, null, callback);
    };

    //init();

    return {
        send: send,
        listen: addMessageListener,
        setup: init,
    }
});
