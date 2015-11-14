# RxAndroidSession
This is the codelab for the session on basics of RxAndroid and Retrofit 

##Overall objective

Use the concepts of RxJava to orchestrate different operations in android. More specifically, we intend to make network calls to get lists of tracks created by 2 separate music bands and then using RxJava to combine and sort them. The results will be displayed in a RecyclerView.

##Some Additional tasks
- Handle Network Connection ,and show approriate error message
- On clicking a particular result load the Youtube app and show the list of video for the particular music video.

##Concepts covered
- Creating Observables using Observable.create
- Using Retrofit to create Observables, here we create an Observable manually using a synchronous call in Retrofit
- Chaining operators to do transformations, combining and sorting collections
- Specifically covered operators like map and zip
- Threading in RxJava
- Displaying results in a simple RecyclerView

##Steps to conduct the codelab

###Preparation
This is the final output of the codelab :

![Image of Demo app ](https://github.com/aldefy/RxAndroidSession/blob/master/screenshot.png)

To get started, you would need to clone the repository and delete/clear some files/methods, more specifically
- Clear the Music.java class in network package
- Clear the fabOnClick method inside MainActivity
- Clear the fetchData method inside MainFragment

Here are the actual steps in the codelab itself:
###Step 1: Understand how Observable.create and other operations like map work
- Create an Observable of type NetworkResponse from the synchronous call in Retrofit (the first call in MusicApi.java)
- Use the map operator to map from an Observable<NetworkResponse> to Observable<List<Result>>
- Use the Thread Schedulers to do the network task and mapping in the background thread and the subscription in the main thread
- Once the fab is clicked on the screen, you search for an artist and on submitting the name , you would need to display the results in provided RecyclerView.

###Step 2: Showcase the use of the zip and map operators to orchestrate operations in android
- Create two separate Observable<NetworkResponse> representing separate network calls for two separate search parameters
- Use the map operator to map from an Observable<NetworkResponse> to Observable<List<Result>> for each of the results
- Then use the zip operator to combine for the Observable<NetworkResponse> objects, in the zip function combine both the lists
- Use the map operator to sort the combined list of results
- Like before, use the Thread Schedulers to do the network task and mapping in the background thread and the subscription in the main thread
- Once the fab is clicked on the screen, you would need display the results in provided RecyclerView

##Dependencies
- AppCompat, RecyclerView and the Design support library
- Retrofit and GSON: for the network operations
- RxAndroid: for all Rx stuff
- Butterknife: for view injection

