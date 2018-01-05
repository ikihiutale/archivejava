##To run this application use
```
mvn spring-boot:run
```

##Environment
###Install MongoDB 3.6
Create config file (mongo.config):
```
dbpath=..\data\db\ 
port = 27017
logpath=..\log\mongo.log
```
###Install Eclipse

###Install Maven

###Start MongoDB
```
..bin\mongod.exe --config="..\mongo.config"
```

##Development

IDE: Eclipse
```
E.g. Run As / Debug As.. -> Java application -> /codereview/src/main/java/com/eficode/buggywebservice/CodereviewApplication.java
```
##Test
IDE: Eclipse
```
E.g. Run As / Debug As.. -> JUnit Test -> /codereview/src/test/java/com/eficode/buggywebservice/controller/LoginControllerTest.java
```

##Try "login" 
###Start mongo shell 
Run ../bin/mongo.exe

###Mongo shell commands 
```
show dbs
use codereview
db.getCollectionNames()
db.loginInformation.drop()
```
### CULR command
```
curl -X POST -i -H "Content-type: application/json" -X POST http://localhost:8080/login -d '{"userName":"username", "password":"password"}'
```

###Mongo shell commands 
```
db.loginInformation.save( { username: "username", password: "password" } )
db.loginInformation.find()
```
### CULR command
```
curl -X POST -i -H "Content-type: application/json" -X POST http://localhost:8080/login -d '{"userName":"username", "password":"password"}'
```
