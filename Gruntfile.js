'use strict';

module.exports = function (grunt) {
    grunt.initConfig({
        karma: {
            unit: {
                configFile: 'src/test/js/karma.conf.js',
                singleRun: true
            }
        }
    });

    grunt.loadNpmTasks('grunt-karma');
};