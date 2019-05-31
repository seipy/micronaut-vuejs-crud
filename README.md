Micronaut backend vuejs frontend application
---


Sample to get h2 database working taken from [this guide](https://guides.micronaut.io/micronaut-data-access-jpa-hibernate/guide/index.html)



Running app
----


##### Run Consul

To run first either install consul locally and run `./consul agent dev`

or if you have installed docker simply run `sudo docker run -p 8500:8500 consul`
 
 
 

##### To start all applications in one session run:

```
./gradlew frontend:start backend:run gateway:run --parallel


# Advanced: 
# When running on linux a process for node hangs on which also keeps jvms active - killing node kills all other jvms hanging off
# this is all in 1 line to kill if found and start apps

kill -9 $(netstat -pln 2>/dev/null |grep LISTEN|grep node|awk '{print $7}'|awk -F"/" '{print $1}'); ./gradlew frontend:start backend:run gateway:run --parallel



```

The above will launch 1 instance of frontend vuejs site running on `localhost:3000` 
and a backend micronaut site running on port `localhost:{random}` a gateway micronaut app running on port 
`localhost:8080` 


##### frontend changed to resemble a grails vuejs site: will start up on port 3000
```
c:\xxx\micronaut\hotel-info>gradlew frontend:start

To manually start app using npm or yarn run:

micro-projects/hotel-info/frontend$ npm run dev   

micro-projects/hotel-info/frontend$ yarn run dev


```


##### To run Backend: will currently launch and bind to port {random}  - mutiple instances can be started

```
c:\xxxx\micronaut\hotel-info>gradlew backend:run --stacktrace

```


##### To run gateway app : will currently launch and bind to port  8080 - testing single instance only currently

```
c:\xxxx\micronaut\hotel-info>gradlew gateway:run --stacktrace

```


##### Testing gateway -> backend:

```bash

curl "http://localhost:8080/list?name=&offset=1"
[{"id":7,"code":"MARL","name":"Mariott International - London Bridge","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":13,"code":"STWL","name":"Starwood Hotels - London Bridge","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":19,"code":"ACGL","name":"Accor Group - London Bridge","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":25,"code":"CHIL","name":"Choice Hotels - London Bridge",
"hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},{"roomType":"DOUB","price":65.00,"stockTotal":200},
{"roomType":"TWIN","price":65.00,"stockTotal":200},{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":31,"code":"BEWL","name":"Best Western - London Bridge",
"hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},{"roomType":"DOUB","price":65.00,"stockTotal":200},
{"roomType":"TWIN","price":65.00,"stockTotal":200},{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":37,"code":"CARL","name":"Carlson - London Bridge","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":43,"code":"HILI","name":"Hilton - Islington","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":49,"code":"MARI","name":"Mariott International - Islington","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":55,"code":"STWI","name":"Starwood Hotels - Islington","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]},
{"id":61,"code":"ACGI","name":"Accor Group - Islington","hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},{"roomType":"FAM","price":95.00,"stockTotal":200}]}]

curl "http://localhost:8080/1"
{"id":1,"code":"HILL","name":"Hilton - London Bridge",
"hotelRooms":[{"roomType":"SING","price":45.00,"stockTotal":200},
{"roomType":"DOUB","price":65.00,"stockTotal":200},
{"roomType":"TWIN","price":65.00,"stockTotal":200},
{"roomType":"TRIP","price":85.00,"stockTotal":200},
{"roomType":"FAM","price":95.00,"stockTotal":200}]}

```

##### Cors issue attempting to hit vuejs site to a core micronaut java controller:

```
webpack-internal:///./node_modules/babel-loader/lib/index.js!./node_modules/vue-loader/lib/selector.js?type=script&index=0!./src/components/Pagination.vue:99  thi1 0
:3000/#/hotel:1 Failed to load http://localhost:8080/list?name=&offset=0: No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'http://localhost:3000' is therefore not allowed access.
webpack-internal:///./src/services/HotelService.js:48 XMLHttpRequest
:3000/#/hotel:1 Failed to load http://localhost:8080/list?name=: No 'Access-Control-Allow-Origin' header is present on the requested resource. Origin 'http://localhost:3000' is therefore not allowed access
```

 `No 'Access-Control-Allow-Origin' header is present on the requested resource.` is typically an issue when attempting to hit a java micronaut app.
 
 To complete this a new `gateway app` will be added after this push which talks to http clients of core backend java micronaut apps.   

Port changed in application.yml of backend to `${random.port}` port 8080 will become the `gateway site`

To create gateway site:

`mn create-app gateway`

Gateway basics added as part of last push for things to work consul now is required:


Here is a very basic sample of site loading up micronaut backend app via gateway app through to vuejs frontend app.

![basics working](https://raw.githubusercontent.com/vahidhedayati/micronaut-vuejs-crud/master/docs/working-with-pagination-vuejs.png)

![save search demo](https://raw.githubusercontent.com/vahidhedayati/micronaut-vuejs-crud/master/docs/save-search-demo.png)

