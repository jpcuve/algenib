/**
 * Created by jpc on 07/04/16.
 */
/*global angular*/
angular.module("client")
    .controller("mainController", ["$scope", "$log", function($scope, $log){
        "use strict";
        $log.info("main controller");

        $scope.message = "coucou";

    }])
;