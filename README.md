# SuitmediaTestTwo
This is the result of Suitmedia Screening Test using on Android platform Kotlin. This is the architecture and library that I use :
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

## Home Page
On home page, I'm using ```TextInputLayout``` for the text field and simple button for next button. Text field can't be empty and the code check it when next button clicked. I use Navigation Safe Args to send the input to the Choose Button Page

![Home Page](https://i.ibb.co/ZK44pXg/Home-Page-Test-One.png) ![Home Page on Error](https://i.ibb.co/f45b5W9/Home-Page-on-Error-Test-One.png)

## Choose Button Page
On choose button page, I'm using data binding and shared viewmodel to attach the selected guest and event. If selected guest is null then button guest's name is "Pilih Guest". And so the button event does. For the args, this page received data from Home Page as String and attached it to the ```TextView```.

![Choose Button Page](https://i.ibb.co/zJKHK0B/Choose-Button.png)

## Events Page
I use data dummy, data binding, and recyclerview to present the events data. Also I use handler from interface to handle item on click. When one of the item clicked, set the selected event inside shared viewmodel then backstack the page.

![Events Page](https://i.ibb.co/DrFhqnp/Events-Page.png) ![After Choosing Event](https://i.ibb.co/PmZBm8X/Choose-Button-after-Choosing-Event.png)

## Guests Page
I use retrofit, moshi, and kotlin coroutine to get the data from api given. Also I use handler from interface to handle item on click. When one of the item clicked, check the day of birthdate using modulus then show Toast, set the selected guest inside shared viewmodel then backstack the page.

![Guests Page](https://i.ibb.co/C89JK0J/Guests-Page.png) ![After Choosing Event](https://i.ibb.co/w0hmTpL/Choose-Button-after-Choosing-Guest.png)
