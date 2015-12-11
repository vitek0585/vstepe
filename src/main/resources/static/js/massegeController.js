globalApp.controller("messageCtrl", ['$scope', '$http', 'dialogService', '$routeParams',
    function (scope, http, dialog, routeParams) {

        scope.comeMsg.list = [];

        scope.dialog = {
            id: scope.user.id,
            current: undefined,
            messages: [],
            text: ''
        };

        scope.messages = [];

        scope.clickDialog = function (id) {

            scope.dialog.current = id;
            $("#show-dialog").trigger('click');

            http.get('message/current', {params: {id: scope.dialog.current}}).
            success(function (d) {
                scope.dialog.messages = d;
            });

        };
        scope.clickMsg = function () {
            http.get('message/all').success(function (d) {
                scope.messages = d;
            });
        }

        scope.send = function () {
            dialog.send(scope.dialog);
            scope.dialog.text = '';
        };

       scope.$on('notifyMsg',function(e,message){

           if (message.ownerId == scope.dialog.id || message.ownerId == scope.dialog.current) {
               //console.log(message)
               message.sender = {name: message.name};
               scope.dialog.messages.push(message);
           }

       });

        if (angular.isDefined(routeParams.id)) {
            scope.clickDialog(routeParams.id);
        }
        scope.clickMsg();
    }]);
