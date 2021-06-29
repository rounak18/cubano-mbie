# Test Automation for MBIE Toilet Calculator

This Automation Project is developed using BDD (Behaviour Driven Development), Java as programing language, Maven as build automation tool and open source framework Cubano with Concordian and external java libraries like PdfComparator etc..

Please read the below note to successfully setup the project on your machine.

If you are working behind a proxy, ensure you work through the next section 'Dealing with Proxies'.   


## Prerequisite to Run project
* Installed Java 1.8.** JDK
* Installed Apace Maven 3.5.***
* Installed Eclipse or IntelliJ IDE
* Set Environment Variables for Java Home, Maven Home and Path variable for the same


## Set up and run this project  
* Ensure you have a [Git client](https://git-scm.com/downloads) and [Gradle](https://gradle.org/) installed (or use via your IDE or other tools)
* Install [Firefox](https://www.mozilla.org/en-US/firefox/new/).  The default browser used in this demo project is Firefox.
* Install [Chrome](https://www.google.com/chrome/).  Used for at least one test.
* `cd` to a relevant folder
*  download and unzip this project
* Import as a Gradle or Maven project into your IDE (e.g. for Eclipse, ensure [Buildship](http://www.eclipse.org/buildship) is installed, then File > Import > Gradle > Existing Gradle Project > follow the wizard)

## A note on Configuration Files
The default configuration for this project is pulled from `config.properties`.  Default configuration can be overriden by introducing a `user.properties` file.  Simply override properties in `user.properties` as required (useful for multi developer teams).  The project supplies a `user.properties.template` which can be used for this purpose.

## A note on Browsers
By default this project is set up to use the Firefox browser (with the Switch Browser fixture additionally using Chrome).

The property `webdriver.browserprovider`, in config.properties controls the default browser (e.g. `webdriver.browserprovider = FirefoxBrowserProvider`).

To modify the default browser, update property `webdriver.browserprovider` in either:
* the config.properties file
* or the user.properties file

Additional `[BrowserName]BrowserProviders` can be found in package `org.concordion.cubano.driver.web.provider.*`. 

Two other important classes to review are:
* `org.concordion.cubano.driver.web.config.WebDriverConfig` > Reads and supplies properties from the config.properties file that is required by the framework
* `org.concordion.cubano.driver.web.provider.LocalBrowserProvider` > Base class for local browser providers.

Further information on browser support and configuration can be found in the [Cubano documentation on Browser Providers](https://concordion.org/cubano/browser/providers)

## To Execute Tests
Concordion fixtures use the JUnit library, with a specialised ConcordionRunner (`@RunWith(ConcordionRunner.class)`).  This annotation is part of the class hierarchy from `ConcordionIndex` or `ConcordionFixture`, which all Fixtures inherit from.

### From an IDE (e.g. Eclipse)
* Class `specification` will run the full suite.  Run as per any standard JUnit fixture.
* Classes that end with `*Fixture` can be executed in the same way


### Using Maven
1. Ensure you are in the `cubano-mbie` root directory and that you've download and installed maven (this has been tested with 3.*).
1. From cmd line run `mvn test`
1. View the Concordion output under the subfolder
`target\concordion\nz\govt\mbie\specification\Specification.html`

## Dealing with Proxies
If working from behind a proxy, then you will need to manage some proxy configuration as specified below, for Gradle, Service and Browser Testing, and Eclipse.

### Gradle
For an initial example, see `gradle.properies`, in the root directory, and update the `systemProp.*` parameters as required. For additional configuration options see [accessing the web via a proxy](https://docs.gradle.org/current/userguide/build_environment.html#sec:accessing_the_web_via_a_proxy).

### Service and Browser Testing
For an initial example, see `config.properies`, in the root directory, and update the `proxy.*` parameters as required. You must set `proxy.required = true` to use any format of proxy configuration. 

The built in Cubano class `org.concordion.cubano.config.ProxyConfig`, provides three forms of Proxy Management (choose one):
* Setting the Proxy from a Config File (which is the mechanism in this project - see below)
* Setting the Proxy from System Properties
* Setting the Proxy from  Environment Variables


### With Eclipse
`Eclipse > Window > Preferences > General > Network Connections`

* Add Manual for Http and Https: 
  * host: [proxyHost]
  * port: [proxyPort]
  * authentication: required
  * proxy bypass: [hostsToBypass]

