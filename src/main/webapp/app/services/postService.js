'use strict';

angular.module('app').factory('PostService',
    function ($resource,ROOT_PATH) {
            return $resource(ROOT_PATH+'/post/:id', {id: '@id'});
    });