# GITGR Test Assessment



## Part 1
**What are your opinions in the usage of interfaces in general?
The concept of interfaces exists for a reason, but when and how should we use it?** 
	- If you want to take advantage of multiple inheritance
	- If you want to define what a class must do and not how.
	- If you think there could be multiple ways to implement some job.  This leads to the concept of polymorphism, which is the ability of one object,  to actually act in multiple ways depending upon the object type created. This makes the code loosely coupled, as the object of any implemented type can be stored in the interface type. That’s how we are hiding the implementation. 
	- Use interfaces to define an application programming contract. (For example: Database, External libraries, etc.)

**What are your opinions on the Trading Currency Interface above?
Is it necessary or unnecessary?**
It is good to code for interfaces as it inverses your dependencies. However, the design given here does not seem to be necessary. A better solution would have been to use a Strategy like design pattern. Furthermore, I wouldn't consider ExchangeCurrency a type of Currency. ExchangeCurrency could be on the manager layer where it performs the business logic and that is where we can use the strategy pattern. Means we create objects which represent various strategies and a context object whose behavior varies as per its strategy object. Usage of the same an be seen in my implemented code. 

**Programming languages typically comes with frameworks that implement arithmetic operations for decimal values differently. java.math.
BigDecimal is Java platform's implementation. System.Decimal is .NET Framework's implementation.
Share your opinions on why such arithmetic operations are implemented by individual programming language frameworks differently.**

Share your opinion why we should or shouldn't use implementations provided by individual programming languages.
Classes like these are available to help us to do all the mathematical operation in object oriented way. It provides a large number of utility methods which can do complex calculations on large number with very high accuracy. Although it may be slower.  It helps overcome the limitations that are there on the size of primitive datatypes. Different programming languages uses different algorithms to get faster and accurate results, sometimes making a tradeoff between the two. 
		
I believe that frameworks like these helps the developer in creating better, testable and bug free code. However, there is huge learning curve and it makes the program more complex. Unless the requirements deems it, I believe in keeping the code as simple as possible.


## Part 2
**api/v1/currency-exchange**

curl --location --request GET 'http://localhost:8080/api/v1/currency-exchange' \

**api/v1/currency-exchange/from/USD/to/SGD/quantity/1**

curl --location --request GET 'http://localhost:8080/api/v1/currency-exchange/from/SGD/to/USD/quantity/19000' \


**api/v1/currency-exchange**
curl --location --request POST 'http://localhost:8080/api/v1/currency-exchange' \

--header 'Content-Type: application/json' \

--data-raw '{

"inputQuantity": 2000,

"inputCurrency": "SGD",

"outputCurrency": "INR"

}'

## To connect to in memory database:

Once the application is running you can connect to database using: 

http://localhost:8080/api/h2-console/
JDBC URL: jdbc:h2:mem:testdb
username: sa
password: password