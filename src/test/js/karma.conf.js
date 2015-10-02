// Karma configuration
// Generated on Sun Aug 16 2015 01:40:11 GMT+0200 (CEST)

module.exports = function(config) {
  config.set({

    // base path, that will be used to resolve files and exclude
    basePath: '../../',

    // testing framework to use (jasmine/mocha/qunit/...)
    frameworks: ['jasmine'],


    // frameworks to use
    // available frameworks: https://npmjs.org/browse/keyword/karma-adapter
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [
      'main/webapp/assets/jquery/dist/jquery.js',
      'main/webapp/assets/angular/angular.js',
      'main/webapp/assets/angular-mocks/angular-mocks.js',
      'main/webapp/assets/angular-resource/angular-resource.js',
      'main/webapp/app/app.js',

      'main/webapp/app/app.js',
      'main/webapp/app/**/*.js',
      'main/webapp/app/**/*.js',
      'test/js/**/!(karma.conf).js'
    ],
    // list of files to exclude
    exclude: [
    ],
    // preprocess matching files before serving them to the browser
    // available preprocessors: https://npmjs.org/browse/keyword/karma-preprocessor
    preprocessors: {
    },
    // test results reporter to use
    // possible values: 'dots', 'progress'
    // available reporters: https://npmjs.org/browse/keyword/karma-reporter
    reporters: ['progress','dots', 'coverage', 'threshold'],

    // optionally, configure the reporter
    coverageReporter: {
      dir: 'target/',
      reporters: [
        {type: 'html', subdir: 'html-coverage'},
        {type: 'cobertura', subdir: 'coverage'}
      ]
    },

    // web server port
    port: 8001,
    // enable / disable colors in the output (reporters and logs)
    colors: true,

    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,

    // start these browsers
    // available browser launchers: https://npmjs.org/browse/keyword/karma-launcher
    browsers: ['PhantomJS'],

    // Continuous Integration mode
    // if true, Karma captures browsers, runs the tests and exits
    singleRun: false,

    // Which plugins to enable
    plugins: [
      'karma-phantomjs-launcher',
      'karma-jasmine-jquery',
      'karma-ng-html2js-preprocessor',
      'karma-jasmine',
      'karma-coverage',
      'karma-junit-reporter',
      'karma-threshold-reporter'
    ],

    thresholdReporter: {
      statements: 95,
      branches: 95,
      functions: 95,
      lines: 95
    },

    junitReporter: {
      outputFile: 'target/surefire-reports/TEST-javascript.xml',
      suite: 'unit'
    },

    colors: true,

    // level of logging
    // possible values: LOG_DISABLE || LOG_ERROR || LOG_WARN || LOG_INFO || LOG_DEBUG
    logLevel: config.LOG_INFO,
    urlRoot: '_karma_'
  })
}
