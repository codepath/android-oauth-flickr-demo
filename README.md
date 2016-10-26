android-oauth-flicker-demo
==========================

Android App Using [RestClientTemplate](https://github.com/codepath/android-rest-client-template) for Flickr

<img alt="Flickr" src="http://i.imgur.com/9fkukDU.png" width="300" />

## Setup

1. To get an Flickr API key, visit [https://www.flickr.com/services/apps/create/apply/](https://www.flickr.com/services/apps/create/apply/).

   <img src="http://imgur.com/DmOhVPJ.png"/>

2. Click on Apply for Non-Commercial Key.

3. Fill out a name and description of the app.  Make sure to accept terms of use and click Submit.

4. You should see `Done! Here's the API key and secret for your new app:`.  
     * Set the key value to be the `REST_CONSUMER_KEY`.
     * Set the secret value to be the `REST_CONSUMER_SECRET`.
     
Check the [source files in this repo](https://github.com/codepath/android-oauth-flickr-demo/tree/master/app/src/main/java/com/codepath/apps/restclienttemplate) for a simple example of how to build an authenticated client. 

You can review a detailed guide for using the RestClientTemplate to build OAuth based REST applications in the [android-rest-client-template README](https://github.com/codepath/android-rest-client-template/blob/master/README.md).
