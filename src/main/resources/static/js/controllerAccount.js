//var arr = ['ngRoute'];
//Array.prototype.push(globalApp.requires, arr);
//globalApp.requires.push('ngRoute');
globalApp.config(['$routeProvider', '$locationProvider', function ($routeProvider, $locationProvider) {

    $routeProvider
        .when('/', {
            templateUrl: '/html/Title.html' ,

        })
        .when('/option', {
            templateUrl: '/html/Options.html',
            controller: 'pageOptionsCtrl'
        })

        .when('/search', {
            templateUrl: '/html/Search.html',

        })
        .when('/message', {
            templateUrl: '/html/Messages.html',
            controller: 'messageCtrl'
        })


    $locationProvider.html5Mode({
        requireBase: false
    });

}]);
globalApp.controller('pageOptionsCtrl', [
    '$scope', '$timeout', '$http', function (scope, timeout, http) {

        scope.response = {message: ""};
        scope.clearMessage = function () {
            timeout(function () {
                scope.response.message = "";
            }, 2000, true);
        };
        scope.save = function (user) {

            http.post("user/edit", user, {headers: {'Accept': 'text/plain'}})
                .success(function (d) {

                    scope.response.message = d;

                    timeout(function () {
                        angular.copy(scope.userTmp, scope.user);
                    }, 0, true);


                }).error(function (d) {
                scope.response.message = "ошибка";
            }).finally(function () {
                scope.clearMessage();
            });


        };

    }
]);

globalApp.controller('userCtrl', [
    '$scope','list','dialogService', function (scope,list,dialog) {

        scope.list = list;
        scope.comeMsg = {list:[]};

        scope.user = {
            id:-1,
            name: "",
            lastname: "",
            group: ""
        };
        scope.userTmp = {};

        scope.init = function (id,name, lastname, group) {

            scope.user.id = id;
            scope.user.name = name;
            scope.user.lastname = lastname;
            scope.user.group = group;
            angular.copy(scope.user, scope.userTmp);

            //setup socket
            dialog.listen(function (message) {

                scope.comeMsg.list.push(message);
                scope.$broadcast('notifyMsg',message);

            });
            dialog.setup(scope.user.id);

        }


    }

]);
