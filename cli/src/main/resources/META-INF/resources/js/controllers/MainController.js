/**
 * Created by jpc on 07/04/16.
 */
/*global angular*/
angular.module("client")
    .controller("mainController", ["$scope", "$log", "$resource", function($scope, $log, $resource){
        "use strict";
        $log.log("main controller");

        $scope.message = "coucou";

        $scope.rpc = function(){
            $log.log("calling server");
            var testResource = $resource("http://localhost:8080/algenib/api/test");
            $scope.res = testResource.get(function(){
                $log.log("response received", angular.toJson($scope.res));
            });
        }

    }])
;