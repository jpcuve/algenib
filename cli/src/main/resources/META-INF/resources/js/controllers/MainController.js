/**
 * Created by jpc on 07/04/16.
 */
/*global angular*/
angular.module("client")
    .controller("mainController", ["$scope", "$log", "$resource", function($scope, $log, $resource){
        "use strict";
        $log.info("main controller");

        $scope.message = "coucou";

        $scope.rpc = function(){
            $log.debug("calling server");
            var testResource = $resource("/algenib/test");
            testResource.get(function(){
                $log.debug("response received");
            });
        }

    }])
;