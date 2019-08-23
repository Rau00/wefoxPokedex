# wefoxPokedex

Architecture MVVM + Router

Language -> Kotlin

Branch Policity -> Git Flow

Technologies:

- Coroutines Kotlin:
    - They allow to change thread in a more comprehensible way than rxjava

- LiveData Observables:
    - It allows reactively to perform actions when the value of a variable changes

- Retrofit
  - To get information from webService

- Room
  - To save and get information from DataBase

- Koin -> Dependences injection
    - It is written in kotlin, so it is more efficient when the application is written in kotlin, on the other hand the learning curve is less than dagger2

- JUnit: Testing
- Not incluyed in this test, to integration testing --> Expresso

![alt text](https://github.com/Rau00/wefoxPokedex/blob/master/architectureMVVM%2BRouter.png)

About de architecture it's define by:
 - DataSource:
    - Abstraction class to get information from webService or DataBase with independence of library
    
 - Repository:
    - It's to Centralizer all datasource
    
 - ViewModel:
    - It's like a presenter, it have de observables values and the business Logic of the fuctionality
    
 View:
    - Layer that present de information to the user's

