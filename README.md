# RappiMovieTest
============================

> (kotlin + room + coroutines + paging + di + repository + ViewModel + retrofit)

This example app uses as provider TMDB https://developers.themoviedb.org/3.

I tried to create an app with submodules.
Core module contains all the data layer like room, networking, pagination etc.

As you will see in the commints, when I started the project I created some view models following MVI architecture, also used pagination v3 (to be honest have not work yet with this version), due the problems and missing knologment I reverted pagination 3, to pagination 2. 

Given this I re-implement few modules into mvvm and detail and searching modules are following MVVI.

I have a draft here: https://medium.com/p/a6355f772463/edit which my custom implementation of MVI.

## Features included:
- Pagination supported.
- Saved item in localstorage using Room.
- Single activity application
- Top level navigation such contains feature-submodules
- Core module 
- Movie List module
- Search movie module
- Detail movie module.


### How to run
```
git clone git@github.com:cbedoy/RappiMovieTest.git app
cd app
gradlew assembleDebug
```

#### The app had been splitted into modules.
- App Module: 

#### The app has following packages:
1. **data**: It contains all the data accessing and manipulating components.
2. **di**: Dependency providing classes using Koin.
3. **ui**: View classes along with their corresponding ViewModel.
4. **usescases**: It contains usescases to wrap final user actions.
4. **ui**: Contains view, fragments, and viewholders.

### Library reference resources:
1. CoilKt: https://github.com/coil-kt/coil
2. NetworkResponseAdapter: https://github.com/haroldadmin/NetworkResponseAdapter
3. Paging: https://developer.android.com/topic/libraries/architecture/paging
4. Retrofit: https://square.github.io/retrofit/
5. Navigation: https://developer.android.com/guide/navigation
6. Room: https://developer.android.com/topic/libraries/architecture/room.html
7. MockK: https://mockk.io/
8. Android Youtube Player: https://github.com/PierfrancescoSoffritti/android-youtube-player

# Notes

- In order to support Play Videos feature I included a third party library which has a kinda bug haven't fixed. (Play didn't stop)
- I think would have better include all features into one view, I splitted them into different views, hence that complicated the implementation to me.
- When you leave search fragment, and return result dissapears, therefore you have to search again. 



```
Line by line, logic and syntax, my dreams explode.
```
