# Pokedex

Architecture:
- Clean Architecture
- MVVM
Language -> Kotlin

Branch Policy -> Git Flow

Technologies:

- Artificial intelligent:
  - It was integrate a custom TENSOR FLOW LITE model, that calculate the probability of victory of each pokemon

- UI
  - Jetpack Compose:
    - Allow a fast and descriptive UI develop

- Coroutines Kotlin:
    - They allow to change thread in a more comprehensible way than rxjava

- Flow:
    - It allows reactively to perform actions when the value of a variable changes.
    - Allow update the view when happen a change in the room database

- Retrofit
  - To get information from webService

- Room
  - To save and get information from DataBase

- ~~Koin~~ Hilt -> Dependencies injection

- JUnit: Testing



About de architecture it's define by:
 - DataSource:
    - Abstraction class to get information from webService or DataBase with independence of library
    
 - Repository:
    - It's to Centralizer all datasource
    
 - ViewModel:
    - It's like a presenter, it have de observables values and the business Logic of the functionality
    
 - View:
    - Layer that present de information to the user's

