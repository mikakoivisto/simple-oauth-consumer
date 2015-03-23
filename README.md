# Simple OAuth Consumer

This project demonstrates how to consumer Liferay JSON WS services protected by Liferay OAuth Provider Plugin. 

1. Install OAuth Provider to your Liferay from https://www.liferay.com/marketplace/-/mp/application/45261909
2. Build and deploy liferay-rest-consumer from https://github.com/sammso/liferay-rest-consumer
3. Build and deploy simple-jersey from https://github.com/sammso/simple-jersey
4. Create a new Application on the OAuth Admin in control panel and use callback url http://localhost:8080/simple-oauth-consumer/callback
5. Copy Consumer Key and Consumer Secret to application.properties then build and deploy simple-oauth-consumer

You can access the simple-oauth-consumer at http://localhost:8080/simple-oauth-consumer once you've authorized the app then you can get the notes produced by simple-jersey fronted by Liferay and get the authorized user info from Liferay.