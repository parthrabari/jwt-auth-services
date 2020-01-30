TO start the services -


- Import all the project as maven project in ide
- start discory service, auth-service, imdb-service
- start api-gateway

make requests as shown in postman screenshots.

for gettig token with password grant type
user name - parth
password - 12345

Currently system supports issuing token and refreshing tokens,
however token revocation has not been implemented due to time constraint,
so multiple tokens can be active at a time, since this is not actual app, and tokens are short lived, we can allow that.
if we wanted to revoke it , then redis cache can be used , which is shared between auth and gateway service. 
which will have list of all revoked tokens 
and we can check blacklisted token on apigateway


