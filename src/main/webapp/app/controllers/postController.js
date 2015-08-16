'use strict';

angular.module('app').controller('PostController', ['PostService', function (PostService) {
    var self = this;
    function init(){
         PostService.query(function (res) {
            self.posts=res;
        });
    };
    init();

    self.addPost = function () {
        if (self.postBody) {
            PostService.save({
                body: self.postBody
            }, function (post) {
                self.posts.unshift(post)
                self.postBody = null
            })
        }
    };
}]);