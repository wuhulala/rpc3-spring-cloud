var uiApp = angular.module("uiApp", ["ui.router"]);

uiApp.config(function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise("person");

    $stateProvider
        .state('person', {
            url: '/person',
            templateUrl: '/tpl/person.html',
            controller: 'PersonController'
        })
        .state('some', {
            url: '/some',
            templateUrl: '/tpl/some.html',
            controller: 'SomeController'
        });
});

var CURDService = function (modelName) {

    this.modelName = modelName;
    this.save = saveOne;


    //////////////////////////////////////

    function saveOne(item) {
        $http.post('/dispatch', item).success(function (data) {
            $scope.people = data;
            $scope.errorMessage = "";
        }).error(function () {
            $scope.errorMessage = "错误";
        });        console.log(this.modelName);
        console.log(item);
    }

};

uiApp.service('crudService', function ($http) {
    //this.modelName = modelName;
    this.save = saveOne;


    //////////////////////////////////////

    function saveOne(item) {
        $http.post('/dispatch', item).success(function (data) {
            $scope.people = data;
            $scope.errorMessage = "";
        }).error(function () {
            $scope.errorMessage = "错误";
        });        console.log(this.modelName);
        console.log(item);
    }

})

uiApp.controller("PersonController", function ($scope, $http, PersonService) {

    $scope.people = "";
    $scope.errorMessage = "";

    console.log("===============init person service asd ==============");
    console.log(PersonService);
    console.log("===============init person service ==============");
//    PersonService.save(personName);

    $scope.getMessageResponse = function (personName) {
        console.log(PersonService.save)
        PersonService.save(personName);
        // $http.post('/dispatch', personName).success(function (data) {
        //     $scope.people = data;
        //     $scope.errorMessage = "";
        // }).error(function () {
        //     $scope.errorMessage = "错误";
        // });
    }

});

// var personService = function ($http) {
//     console.log("----personService 初始化------")
//     personService.prototype = new CURDService("person");
//     personService.prototype.$http = $http;
// };
// personService.$inject = ['$http'];

uiApp.service("PersonService", function (crudService) {
    angular.extend(this, crudService);
    this.modelName = 'person';
});
uiApp.controller("SomeController", function ($scope, $http) {
    $scope.str = "";
    $scope.errorMessage = "";

    $scope.getSome = function () {
        $http.get('/getsome').success(function (data) {
            $scope.str = data;
            $scope.errorMessage = "";
        }).error(function () {
            $scope.errorMessage = "错误";
        });
    }
});