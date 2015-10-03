exports.config =  {
    seleniumServerJar: '../../../node_modules/protractor/selenium/selenium-server-standalone-2.47.1.jar',
    seleniumPort: null,
    chromeDriver: '../../../node_modules/protractor/selenium/chromedriver',
    seleniumArgs: [],
    sauceUser: null,
    sauceKey: null,
    specs: ['e2e/*.spec.js'],
    capabilities: {
        'browserName': 'chrome'
    },
    baseUrl: 'http://localhost:8080/',
    maxSessions: 1
};