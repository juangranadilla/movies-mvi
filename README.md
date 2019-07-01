# movies-mvi

This is a sample [TMDB](https://www.themoviedb.org/) application to show what I've learn about MVI (Model View Intent) architecture pattern, applied in a clean architecture project, using [the new integration between Coroutines and Architecture Components](https://medium.com/corouteam/exploring-kotlin-coroutines-and-lifecycle-architectural-components-integration-on-android-c63bb8a9156f)

## Dependencies

The application uses the following libraries:

* [Coroutines](https://developer.android.com/kotlin/coroutines) for concurrency
* [Koin](https://github.com/InsertKoinIO/koin) for dependency injection
* [Navigation](https://developer.android.com/guide/navigation) (Architecture component)
* [Retrofit](https://square.github.io/retrofit/) for networking
* [Timber](https://github.com/JakeWharton/timber) for logging
* [Glide](https://github.com/bumptech/glide) for loading images

## TMDB API

The app uses the https://www.themoviedb.org/ API, so you need to use your own API key. Just add your API key in the *local.properties* file like the following:

```
tmdb_api_key="your_api_key"
```

## Useful links
* https://medium.com/corouteam/exploring-kotlin-coroutines-and-lifecycle-architectural-components-integration-on-android-c63bb8a9156f
* https://www.raywenderlich.com/817602-mvi-architecture-for-android-tutorial-getting-started
* https://medium.com/@thanh.bm/android-mvi-architecture-with-livedata-c72e9e1bd3e6
* https://proandroiddev.com/mvi-on-android-with-livedata-coroutines-d2172bc7f775

License
-------

    Copyright 2019 Juan José Granadilla Manzano

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.