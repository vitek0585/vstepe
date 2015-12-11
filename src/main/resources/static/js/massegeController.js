globalApp.controller("messageCtrl", ['$scope', '$http', function (scope, http) {

    scope.dialog = {
        current: undefined,
        messages: []
    };
    scope.messages = [];

    scope.clickDialog = function (id) {
        console.log(id)
        scope.dialog.current = id;
        $("#show-dialog").trigger('click');

        http.get('message/message', {params: {id: scope.dialog.current}}).
        success(function (d) {
            scope.dialog.messages = d;
        });

    };

    http.get('message/all').success(function (d) {
        scope.messages = d;

    });

}]);
