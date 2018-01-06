# Web REST service code review

To run the web rest service via Eclipse:
```
/codereview/src/main/java/com/eficode/buggywebservice/CodereviewApplication.java ->
Run As.. / Debug As.. -> Java application 
```

and via command line:
```
mvn spring-boot:run
```

## Environment

### OS

Windows 10

### MongoDB 3.6

Config file (mongo.config):
```
dbpath=..\data\db\ 
port = 27017
logpath=..\log\mongo.log
```
### Start MongoDB
```
..bin\mongod.exe --config="..\mongo.config"
```

## Eclipse Oxygen

## Maven 

> version 3.3.9

## Test

### Eclipse
```
E.g. Run As / Debug As.. -> JUnit Test -> /codereview/src/test/java/com/eficode/buggywebservice/CodereviewApplicationTests.java
```

### Command line
```
mvn clean test
```

## Manually testing login 

### Start mongo shell 

>Run ../bin/mongo.exe

### Mongo shell commands 

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

### Mongo shell commands 

```
db.loginInformation.save( { username: "username", password: "password" } )
db.loginInformation.find()
```

### CULR command

```
curl -X POST -i -H "Content-type: application/json" -X POST http://localhost:8080/login -d '{"userName":"username", "password":"password"}'
```
