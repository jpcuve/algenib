/**
 * Created by jpc on 07/04/16.
 */
/*global angular*/
angular.module("client", ["ngResource"])
    .constant("config", {
        value: 12,
        api: "/api"
    })
    .config(["$resourceProvider", function($resourceProvider){
        $resourceProvider.defaults.stripTrailingSlashes = false;
    }])
;
