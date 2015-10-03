'use strict';

module.exports = function (grunt) {
    grunt.initConfig({
        karma: {
            unit: {
                configFile: 'src/test/js/karma.conf.js',
                singleRun: true
            }
        },
        protractor: {
            options: {
                keepAlive: true,
                configFile: "src/test/js/protractor.conf.js"
            },
            singlerun: {},
            auto: {
                keepAlive: true,
                options: {
                    args: {
                        seleniumPort: 4444
                    }
                }
            }
        }
    });

    grunt.loadNpmTasks('grunt-karma');
    grunt.loadNpmTasks('grunt-protractor-runner');
};