# arya-interpreter-angularjs

## Overview

arya-interpreter-angularjs is an AngularJS implementation of interpreter. Application directory structure and most of the files inside it come from the [angular-seed](https://github.com/angular/angular-seed) project which is an application skeleton for AngularJS web apps.

## Prerequisites

### Node.js and Tools

- Get [Node.js](http://nodejs.org/download/).

In Debian based distros, there is a name clash with another utility called `node`. The suggested solution is to also install `nodejs-legacy` package, which renames `node` to `nodejs`:

```
apt-get install nodejs-legacy npm
nodejs --version
npm --version
```

- Install the tool dependencies `npm install`.

### Installing dependencies

The application relies upon various node.js tools, such as Bower, Karma and Protractor.  You can
install these by running:

```
npm install
```

This will also run bower, which will download the angular files needed for the application

Most of the scripts described below will run this automatically but it doesn't do any harm to run
it whenever you like.

## Running the app during development

- Run `npm start`
- navigate your browser to `http://localhost:8000/app/index.html` to see the app running in your browser.

## Running unit tests

We recommend using [Jasmine][jasmine] and [Karma](http://karma-runner.github.io) for your unit tests/specs, but you are free
to use whatever works for you.

- Start Karma with `npm test`
  - A browser will start and connect to the Karma server. Chrome is the default browser, others can
  be captured by loading the same url as the one in Chrome or by changing the `test/karma.conf.js`
  file.
- Karma will sit and watch your application and test JavaScript files. To run or re-run tests just
  change any of your these files.

## End to end testing

We recommend using [Jasmine](http://pivotal.github.com/jasmine/) and [Protractor](https://github.com/angular/protractor) for end-to-end testing.

Requires a webserver that serves the application. See Running the app during development, above.

- Serve the application: run `npm start`.
- In a separate console run the end2end tests: `npm run protractor`. Protractor will execute the
  end2end test scripts against the web application itself.
  - The configuration is set up to run the tests on Chrome directly. If you want to run against
    other browsers then you must install the webDriver, `npm run update-webdriver`, and modify the
  configuration at `test/protractor-conf.js`.

## Application Directory Layout

    app/                --> all of the files to be used in production
      css/              --> css files
        app.css         --> default stylesheet
      img/              --> image files
      index.html        --> app layout file (the main html template file of the app)
      js/               --> javascript files
        app.js          --> the main application module
        controllers.js  --> application controllers
        directives.js   --> application directives
        filters.js      --> custom angular filters
        services.js     --> custom angular services
        animations.js   --> hooks for running JQuery animations with ngAnimate
      partials/         --> angular view partials (partial html templates) used by ngRoute
        partial1.html
        partial2.html
      bower_components  --> 3rd party js libraries, including angular and jquery
    test/               --> test source files and libraries
      karma.conf.js        --> config file for running unit tests with Karma
      protractor-conf.js   --> config file for running e2e tests with Protractor
      e2e/
        scenarios.js       --> end-to-end specs
      unit/             --> unit level specs/tests
        controllersSpec.js --> specs for controllers
        directivesSpec.js  --> specs for directives
        filtersSpec.js     --> specs for filters
        servicesSpec.js    --> specs for services
