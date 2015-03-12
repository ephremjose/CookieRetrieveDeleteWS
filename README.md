# CookieRetrieveDeleteWS

This is a WebService Application created using Weblogic 10.3. 
The purpose of the WS app is to provide a set of transactions that a user can utilize to view / delete / export information regarding the cookies of FireFox, which is stored in an SQLite database within the system.


WebService Name : CookieRetreiveDeleteService

Transactions:

1. getFireFoxCookiesRequest : 

Takes an input BaseDomain and returns all the cookies associated with that base domain. If the value of BaseDomain is given as ALL, then cookies for all the 
domains are pulled from the DB and shown to the user Synchronously.

2. exportFireFoxCookiesRequest :

Does not need any parameters being passed in the request. This transaction will create an xl sheet report that contains the complete information regarding all cookies that are present for FireFox at that moment and return the link to the XL file to User.

3. deleteFireFoxCookiesResponse :

Takes an input BaseDomain and deletes all cookies associated with the input Base Domain. If the input is given as ALL, then all the information is deleted.
