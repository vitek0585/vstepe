globalApp.controller('searchCtrl', [
    '$scope', '$timeout', '$http', 'list', function (scope, timeout, http, list) {

        scope.nameOfFind = "";

        scope.search = function (name) {
            if (!angular.isDefined(name)) {
                name = "";
            }
            http.get("user/find", {params: {name: name}}).then(function (d) {
                list.users = d.data;
            }, function () {
                list.users = [];
            });
        }
    }
]);
