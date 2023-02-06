## Movie Mania
This application is an encyclopedia for movies all around the world.
Using the free OMDB API service, This application can search for any movies based on the user input.
You can see all detailed information about your favorite movies and save them in the local database.

### Api
OMDB: https://www.omdbapi.com/

### Used Tools
- Jetpack Compose
- Material 3
- Compose Navigation
- Coroutines
- Retrofit
- Hilt
- Room Database
- Coil Image Loading Library

### Screens
Main Screen  ||  Search Screen  ||  Detail Screen

This application has three screen.
Main Screen: User's list containing the movies he/she previously added.
Search Screen: User searches for movies using the search option.
Details Screen: Selecting a movie from the list will take the user in the detailed screen where it shows the information about the movie.

### Current Issues
- Offline crash on movie list screen after some time
  - turn off wifi and test removing movie from the list
  - turn off wifi and search
- handle backstack navigation
  - go back from details screen to search screen and previous searched words are gone from the bar but the results
- handle loading 
  - details screen
  - main screen
- Dark and Light Theme color mess up
  - text colors on dark mode
  - background color on dark mode