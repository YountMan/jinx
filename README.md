# Jinx - Flickr API for Java

Jinx is a Java interface to the Flickr API. The project goals are:

 * Simple and straightforward to use
 * Complete coverage of the Flickr API
 * Minimal library requirements

You can find an example of how to use the Jinx library here: <https://github.com/jeremybrooks/jinxexamples>

There is an older version of Jinx, build 20110503, found here: <http://jeremybrooks.net/jinx/>. Version 0.6.0 and higher are a major rewrite of Jinx. They will not be compatible with old builds available on jeremybrooks.net.

## WARNING
Versions prior to 1.0 are in a state of flux and subject to change. There is no guarantee that releases before 1.0 will remain compatible with each other.

## Notes About Jinx

### ID's (NSID)
Flickr objects (users, institutions, groups, etc.) have unique identifiers named "nsid". Jinx will translate the nsid into a more specific identifier. For example, for users the nsid will be called userId; for groups, groupId. It is the same value, just named more specifically to make it easier to see what you are referring to.

### Objects vs Primitives
The Flickr API does not return all possible data for every API call. The returned values often depend on what parameters were passed in. In general, Jinx will return null to indicate that there was no value returned by Flickr. This means your code needs to pay attention to return types like Integer and Boolean, since they can return null.

The Flickr API returns boolean values as 1 or 0 (or sometimes true or false). Jinx will translate these to the Java Boolean type. The object Boolean is used rather than the primitive boolean so that a value of "not returned" can be represented as null.
For example, the Photos object can return information about who can see geotag data. However, this is considered extra data, and is not returned unless you specifically ask for it. If you did not ask for the data, Flickr will not return the data, and Jinx will return null when you call a method that returns the data.

### Errors
By default, methods that call the Flickr API will throw an instance of JinxException when Flickr returns a non-zero response code. If you do not want non-zero response codes to cause JinxException to be thrown, you can disable this as follows:

	jinx.setFlickrErrorThrowsException(false);

If this is set to false, you will need to check the returned objects to know if Flickr reported an error. Instances of JinxException will still be thrown to indicate other errors, such as invalid parameters or networking errors.

### Logging
By default, Jinx will not log. If you wish to see logging, you must set a JinxLogger, and then enable verbose logging.

	JinxLogger.setLogger(new StdoutLogger());
	jinx.setVerboseLogging(true);

The StdoutLogger is provided. You can write your own logger to log to log4j or another destination. Just implement the LogInterface.


# REQUIREMENTS
You must be using Java 1.6 or higher.

If you are using Maven, just include this in your pom.xml file

	<dependency>
		<groupId>net.jeremybrooks</groupId>
		<artifactId>jinx</artifactId>
		<version>0.6.10</version>
	</dependency>

If you are not using Maven, you will need these libraries, and their dependencies:
  
  * Gson 2.2.2+ -- deserializes json documents in to Java objects
  * Scribe 1.3.7+ -- provides OAuth services
  
  NOTE: At this time, Scribe 1.3.7 is the development version; you will need to build Scribe from source until they release again.

# VERSION HISTORY

## Version 0.6.10 supports the following API's
  * activity
  * blogs (have not really tested the postPhoto method, though)
  * cameras
  * collections
  * commons
  * contacts
  * favorites
  * galleries
  * oauth
  * photos
  * photosets
  * photosets.comments


# KNOWN ISSUES

 * If a user has no blogs set up, calling the getBlogList() method will throw an Exception. This is due to different data structures returned by Flickr if a user has blogs or does not have blogs. This issue has been reported to Flickr.

 * PhotoUtils methods that use ImageIO do not use configured proxy:


    WARN net.jeremybrooks.suprsetr.flickr.PhotoHelper [SwingWorker-pool-2-thread-6] 2014-07-09 06:41:55,774 - ERROR GETTING ICON FOR PHOTO 3097549845
    net.jeremybrooks.jinx.JinxException: Unable to get image for size SIZE_SMALL_SQUARE
        at net.jeremybrooks.jinx.PhotoUtils.getImageForSize(PhotoUtils.java:88)
        at net.jeremybrooks.suprsetr.flickr.PhotoHelper.getIconForPhoto(PhotoHelper.java:229)
        at net.jeremybrooks.suprsetr.workers.LoadImagesWorker.doInBackground(LoadImagesWorker.java:128)
        at net.jeremybrooks.suprsetr.workers.LoadImagesWorker.doInBackground(LoadImagesWorker.java:48)
        at javax.swing.SwingWorker$1.call(SwingWorker.java:296)
        at java.util.concurrent.FutureTask.run(FutureTask.java:262)
        at javax.swing.SwingWorker.run(SwingWorker.java:335)
        at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1145)
        at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:615)
        at java.lang.Thread.run(Thread.java:745)
    Caused by: javax.imageio.IIOException: Can't get input stream from URL!
        at javax.imageio.ImageIO.read(ImageIO.java:1395)
        at net.jeremybrooks.jinx.PhotoUtils.getImageForSize(PhotoUtils.java:84)
        ... 9 more
    Caused by: java.io.IOException: Unable to tunnel through proxy. Proxy returns "HTTP/1.0 407 Proxy Authentication Required"
        at sun.net.www.protocol.http.HttpURLConnection.doTunneling(HttpURLConnection.java:1873)
        at sun.net.www.protocol.https.AbstractDelegateHttpsURLConnection.connect(AbstractDelegateHttpsURLConnection.java:183)
        at sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1300)
        at sun.net.www.protocol.https.HttpsURLConnectionImpl.getInputStream(HttpsURLConnectionImpl.java:254)
        at java.net.URL.openStream(URL.java:1037)
        at javax.imageio.ImageIO.read(ImageIO.java:1393)



# API IMPLEMENTATION STATUS

## Available In Latest Release
  * activity
  * auth.oauth
  * blogs (have not really tested the postPhoto method, though)
  * cameras
  * collections
  * commons
  * contacts
  * favorites
  * galleries
  * photos
  * photosets
  * photosets.comments

## Available In Latest Source
   * groups
   * groups.discuss.replies
   * groups.discuss.topics
   * groups.members
   * groups.pools
   * interestingness
   * machinetags
   * panda
   * people
   * photos.comments
   * photos.geo

## Not Yet Implemented

  * photos.licenses
  * photos.notes
  * photos.people
  * photos.suggestions
  * photos.transform
  * photos.upload
  * places
  * prefs
  * push
  * reflection
  * stats
  * tags
  * test
  * urls


# BUILDING FROM SOURCE
To build the project, use Maven. The jar file will end up in the "target" directory:

	mvn clean package

If the tests are failing and you want to build the jar anyway, add -DskipTests:

	mvn clean package -DskipTests


# JINX PROJECT CONTRIBUTORS - READ THIS
Flickr apps need an API key to work properly. Jinx is no exception. To run the unit tests, you need to go to Flickr and apply for an API key. Once you have this key, you need to do the following:

  * Copy the file test/resources/flickr.properties to test/resources/response/auth/secret.properties
  * Fill in the key, secret, and a valid full path where the oauth token will be saved
  * Uncomment the call to testOauthAccessWorkflow() in JinxTest
  * Run JinxTest
  * Authorize the application
  * Enter the verification code and allow the test to complete

You should now be able to run the rest of the tests. The secret.properties file is excluded by the .gitignore file, so your secrets should not end up on github.

NOTE: The test token will need WRITE and DELETE access to your photostream! Do not authorize the test application if you are concerned about possible data loss due to test errors!

The code is organized as follows:

Package net.jeremybrooks.jinx
	Contains the main Jinx class. Keeps the API key, secret, and OAuth info during runtime. Other classes use this to call Flickr.
	Contains constants
	Contains the OAuthAccesToken class, used to store and load the oauth access token info

Package net.jeremybrooks.jinx.api
	Contains the classes that correspond to the Flickr API.
	Each class should correspond to a section of the Flickr API, and each method in the class should correspond to a method in the Flickr API.

Package net.jeremybrooks.jinx.response
	Contains the objects that are returned from the API classes. Each object should represent the response returned by the call to a Flickr API method.
	Response objects should extend net.jeremybrooks.jinx.response
	Some methods can share return values, for example the methods in the ActivityApi.

There should be a test for each Response object. The response object tests should use sample json responses to test that the deserialization works as expected. Test json documents are in the test/resources/response folder.
The response objects represent deserialized json documents, and can have quite complex object graphs. Care should be taken to expose the data in a way that makes sense in the Java world. For example, the perms field in the OAuthCredentials json document looks like this:

	{ "oauth": {
		"token": { "_content": "72157632940881881-6db7bec3c46b67b2" },
		"perms": { "_content": "delete" },
		"user": { "nsid": "85853333@N00", "username": "Jeremy Brooks", "fullname": "Jeremy Brooks" } }, "stat": "ok" }

When deserialized to Java objects, the permissions string is named _content and is inside a "Perms" object inside an "Oauth" object. If left alone, users of Jinx would have to do this to get the value:

	response.getOauth().getPerms().get_Content();

Rather than make the user remember which object the perms value is found in, the OAuthCredentials response object provides a getPerms() method that returns a String. The complexity of the object graph is hidden from the user. This results in somewhat ugly and complex classes, but the view to the Jinx user is clean and simple.

Generally, response objects are read only. They will have getters, but not setters. The Gson library is used to deserialize json documents into Java objects, and operates on the fields directly.

## Coding Conventions

This project attempts to follow the Sun (now Oracle) [Java coding conventions](http://www.oracle.com/technetwork/java/codeconvtoc-136057.html).

Since this library uses Gson to deserialize JSON documents into Java classes, the Java classes contain a lot of inner classes. This can be confusing at first glance. The classes in the package net.jeremybrooks.jinx.response should follow these guidelines:

 * All classes should implement java.io.Serializable, including inner classes
 * Private inner classes should be named with a beginning underscore
 * Objects should be returned as opposed to primitives; null should be returned when the data was not provided by Flickr
 * Getter methods should be careful to avoid null pointers when returning values, especially when returning values from inner classes
 * In general, these classes will have getters but not setters


# LICENSE
Jinx is Copyright 2010-2014 by Jeremy Brooks and Contributors

Jinx is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Jinx is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Jinx.  If not, see <http://www.gnu.org/licenses/>.

# TODO

