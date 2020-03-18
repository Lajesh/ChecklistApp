# TODO Android Application in Kotlin

# Highlights

1. MVVM Architectural pattern
2. Kotlin
3. Firebase authentication
4. Firebase realtime database
5. Offline storage


The application has been designed using **Android Architecture components** and **Data Binding**

The whole application is built based on the MVVM architectural pattern.

# Application Architecture
<img src="/screenshots/arc.png"  alt="Architecture"/>

The main advantage of using MVVM, there is no two way dependency between ViewModel and View unlike MVP. Here the view can observe the datachanges in the viewmodel as we are using LiveData which is lifecycle aware. The viewmodel to view communication is achieved through observer pattern (basically observing the state changes of the data in the viewmodel).

# Screenshots
<img src="/screenshots/1.png" width="346" height="615" alt="Home"/> 
<img src="/screenshots/2.png" width="346" height="615" alt="Home"/>
<img src="/screenshots/3.png" width="346" height="615" alt="Home"/>
<img src="/screenshots/4.png" width="346" height="615" alt="Home"/>
<img src="/screenshots/5.png" width="346" height="615" alt="Home"/>


# Programming Practices Followed
a) Android Architectural Components (LiveData, Lifecycle Aware components, ViewModel) <br/>
b) Dagger 2 for Dependency Injection <br/>
c) MVVM <br/>
d) Retrofit with Okhttp <br/>
e) JUnit and Mockito for Unit testing <br/>
f) Repository pattern for Data Layer <br/>
g) RxJava for Reactive programming pattern <br/>



# How to build ?

Open terminal and type the below command to generate debug build <br/>

``` ./gradlew assembleDebug ```

Open terminal and type the below command to generate release build <br/>

``` ./gradlew assembleRelease ```

# How to generate Sonarqube report ?

Open gradle.properties and update the below line with the sonarqube server url

```systemProp.sonar.host.url=http://localhost:9000```

Before running the sonarqube job, make sure the project version has been updated in the build.gradle. On every run, increment the version by 1.<br/>

```
            property "sonar.sources", "src/main"
            property "sonar.projectName", "ChecklistApplication" // Name of your project
            property "sonar.projectVersion", "1.0.0" // Version of your project
            property "sonar.projectDescription", "ChecklistApplication to manage your tasks"
```

For running the sonarqube job, type the below command in the terminal. <br/>

```./gradlew sonarqube assembleDebug```

<br/>

# How to generate code coverage report ?

Open terminal and type the following command

```./gradlew clean jacocoTestReport```

The coverage report will be generated on the following path.

``` app/build/reports ```

# How to generate lint reports ?

Open terminal and type the following command
``` ./gradlew lint```

The lint report will be generated on the following path.
``` app/build/reports ```

