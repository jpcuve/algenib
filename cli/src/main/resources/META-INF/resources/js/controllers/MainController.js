/**
 * Created by jpc on 07/04/16.
 */
/*global angular*/
angular.module("client")
    .controller("mainController", ["$scope", "$log", "$resource", "$interval", function($scope, $log, $resource, $interval){
        "use strict";
        $log.log("main controller");

        $scope.message = "coucou";
        var testResource = $resource("http://localhost:8080/algenib/api/test");
        $interval(function(){
            $scope.res = testResource.get(function(){
                $log.log("response received", angular.toJson($scope.res));
            });
        }, 1000);

        $scope.rpc = function(){
            $log.log("calling server");
        }

    }])
;