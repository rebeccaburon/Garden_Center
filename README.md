# PROJECT

##### This project is a RESTful service provider built with the Javalin framework in Java.

##### It demonstrates various functionalities,

##### including data management using DAOs, API error handling, JPA persistence, and automated testing.

# Task 1: Build a REST Service Provider with Javalin

[
{
"id": 0,
"planttype": "Rose",
"name": "Albertine",
"maxheight": 400,
"price": 199.99
},
{
"id": 0,
"planttype": "Bush",
"name": "Aronia",
"maxheight": 200,
"price": 159.99
},
{
"id": 0,
"planttype": "Rose",
"name": "Albertine",
"maxheight": 300,
"price": 299.99
}
]

# Task 2: Exception Handling 
| HTTP method | REST Ressource            | Exceptions and status(es) |
|-------------|---------------------------|---------------------------|
| GET         | `/api/plants`             | Statuscode 204 / 400      |
| GET         | `/api/plants/{id}`        | Statuscode 404 / 400      |
| GET         | `/api/plants/type/{type}` | Statuscode 204 / 400      |
| POST        | `/api/plants`             | Statuscode   400          |

##### I  throw a API Exception with the statuscode 204, if the item is empty. 
##### Showing that the request was  successfully, but there’s no data to return
##### I throw a 404 status code to tell the file is not found
##### And the 400 is for the invalid client input. 
#####
{
"time of error": "2024-10-31 12:05:29",
"error message": {
"status": 400,
"message": "File not found, no Plants with id 1 was found"
}
}



# Task 3: Streams and Generis 
### Programming paradigm
##### The Java Stream API is inspired by the functional programming paradigm
##### The Stream API encourages a functional style of programming by using declarative constructs, lambda expressions, and higher-order functions (such as map, filter, reduce)




# Task 6: Testing the Doctor API with REST Assured
#### Purpose of Rest Assured : 
##### Rest Assured is a Java library for testing RESTful APIs. It allows us to simulate client requests and validate responses, ensuring that each endpoint behaves as expected from a client’s perspective.

#### Set up database for tests :
##### Testcontainers spins up a temporary database container specifically for testing, and a populator class loads predefined data for consistency.

#### Why testing REST endpoints is different from testing DAO :
##### REST endpoint tests simulate full client interactions by sending HTTP requests, testing the complete API response cycle, while dao tests focused only on database operations in isolation.
