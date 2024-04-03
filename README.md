# spring-boot-api
CRUD REST API that allows for managing tours and tour packages across the different regions of Oregon. Includes navigation via hyperlinks.
Derived from the tutorial found [here](https://www.linkedin.com/learning/creating-your-first-spring-boot-microservice/paging-and-sorting?autoSkip=true&resume=false).

## Running the API
1. Download the project.
1. Navigate to the root folder.
1. Run the following command to start the service:
   `./mvnw spring-boot:run`

## Using the API
There are a few options for sending requests after the API is running:

### Postman 
Use the included postman JSON file to import a set of requests.

### Web Browser (GET only)
Navigate to http://localhost:8080 on a browser to view the API via GET requests (some endpoints won't load).

### Curl
You can send curl requests to the API via a terminal. Below are some examples:

**Get API Homepage:**
```bash
curl --location 'localhost:8080'
```

**Get Tours:**
```bash
curl --location 'localhost:8080/tours'
```

**Find Tour by Tour Package Code:**
```bash
curl --location 'http://localhost:8080/tours/search/findByTourPackageCode?code=CS'
```

**Find Tour Package by Name:**
```bash
curl --location 'http://localhost:8080/tourPackages/search/findByName?name=Rivers%20and%20Sea'
```

---

**Create Tour Package:**
```bash
curl --location 'localhost:8080/tourPackages' \
--header 'Content-Type: application/json' \
--data '{
    "code": "EW",
    "name": "East to West"
}'
```

**Read Tour Package:**
```bash
curl --location 'http://localhost:8080/tourPackages/EW'
```

**Update Tour Package:**
```bash
curl --location --request PUT 'localhost:8080/tourPackages/EW' \
--header 'Content-Type: application/json' \
--data '{
    "name": "East and West"
}'
```

**Delete Tour Package:**
```bash
curl --location --request DELETE 'localhost:8080/tourPackages/EW'
```
