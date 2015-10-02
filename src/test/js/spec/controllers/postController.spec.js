'use strict';

describe('test', function () {

    var ctrl;
    var $httpBackend;
    beforeEach(module('app'));
    var all_posts = [
        {
            id:1,
            username:'zeros',
            body:'toto'
        },{
            id:3
        }
    ];

    beforeEach(inject(function ($controller,$httpBackend,ROOT_PATH) {
        ctrl = $controller('PostController');
        $httpBackend.expectGET(ROOT_PATH+'/post').respond(all_posts);
        $httpBackend.flush();
    }));
    describe('fetch all posts on load', function () {
        it('should ask server to send all posts', function () {
            expect(ctrl.posts.length).toEqual(2)
        });
    });


});