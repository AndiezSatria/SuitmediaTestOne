# SuitmediaTestTwo
This is the result of Suitmedia Screening Test Phase two using on Android platform Kotlin. This is the architecture and library that I use :
- Data Binding
- Navigation Component and Navigation Safe Args
- MVVM
- Shared ViewModel
- Retrofit
- Moshi
- Kotlin Coroutine
- Glide
- Room
- Google Maps
All features on phase one are still maintained in this application. Some are modified to fulfill the question.

## Question 1
On home page, I create a method to check wether the name input is a palindrome using Kotlin String Extension and return a boolean. I also comment the manual algorithm below the used method. To show the result, I use AlertDialog.

![Home Page Is Palindrome](https://i.ibb.co/jg4FHM7/Question-One-Palindrome.png) ![Home Page Not Palindrome](https://i.ibb.co/rxNG7g9/Question-One-Not-Palindrome.png)

## Question 2
On Guests Page, I create a method to check the primality of a integer input. To show the result, I use Toast to show the result for isPrime and for guest's birthdate date.

![Not Prime](https://i.ibb.co/LpxrH1y/Guest-Not-Prime.png) ![Not Prime](https://i.ibb.co/myDVsCv/Guest-Is-Prime.png)

## Question 3
This is the updated Home Page UI.

![Home Page](https://i.ibb.co/QdtGsKw/Home-Page-Ui.png)

For the Event's list, this is the updated version. I make a button on the toolbar to change between fragments. Using Fragment Manager and Fragment Transaction to change between fragments. For the map version of page, I use Google Map and ```OnScrollStateChanged``` to detect what data is currently shown and move the map camera to the event's marker and show the marker info. 

![Events List](https://i.ibb.co/kSFkGP3/Events-Page-Ui.png) ![Event Map](https://i.ibb.co/n7gWY17/Event-Map-Page.png)

For the Guests Page, I use Room and SwipeRefreshLayout for caching and pull to refresh.

![Refresh Layout](https://i.ibb.co/5LpSCFp/Guest-Refresh.png)

