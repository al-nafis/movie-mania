# Compose UI Android Experiment
Experimenting Navigation with Compose along with data communication between screens

## Tracker in the House
You can see the list of people in this house and who is in which room. 
You can also make someone go to a specific room.
Each person is wearing a different colored shirt. You can make them wear a different shirt only 
if it is available in the room where the person is and is not currently worn by someone else.

### Used Tools
- Compose Navigation

### Rooms
Living Room  ||  Dining Room  ||  Bedroom

### People
Adam  ||  Brook  ||  Connor  ||  Delilah  ||  Ethan

### Shirts
Red  ||  Blue  ||  Green  ||  Yellow  ||  Gray  ||  Black  ||  Dark Gray  ||  Magenta  ||  Cyan 

### Current Issues
- Random number not randomly generating as recomposition happens and somehow the random number has the same pattern on the 2nd time


## Movie Mania
This experiment make api call using REST API and displays them on the screen.
You can search by movie title or by key words. From the search result, you can choose a specific movie to get detailed information.
You can save any movie you like in the local storage.

### Used Tools
- Jetpack Compose
- Compose Navigation
- Coroutines
- Retrofit
- Hilt
- Room Database

### Screens
Main Screen  ||  Search Screen  ||  Detail Screen

### Current Issues
- Offline crash on movie list screen after some time
- handle backstack navigation
- add image place holder
- handle loading 

## Next Step
segregate two experiments into two different project