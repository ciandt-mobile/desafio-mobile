#  Movies app
![](https://img.shields.io/badge/challenge-CI&T-red.svg) ![](https://img.shields.io/badge/platform-iOS-blue.svg)

## Description & features

The `Movies` app is a simple iOS app that enables users to check the most popular movies that are currently on theathers, and the ones that are upcoming over the next few weeks/months.

Users are able to browse through a list of movies, as well as pick one of them to view more details about it, like its genres, overview, cast members, and trailer.

The app can be used both in portrait and in landscape mode, and the presentation of the information varies a little, depending on which mode the app is running on.

All information provided by the app comes from [The Movie DataBase APIs](https://www.themoviedb.org/documentation/api).

---

### Portrait mode

When in portrait mode, the app takes full advantage of the device's screen size to display one big poster at a time.

Users are then able to page through the list of movies, and when one of the movies is tapped, it flips around to reveal the chosen movie details:

<div style="text-align:center" style="width:100%;"><img src="https://github.com/pieromattos/desafio-mobile/blob/master/Docs/GIFs/PortraitBrowsing.gif" /></div>

---

When viewing details about a movie, the user can scroll within the flipped poster to view more information:

<div style="text-align:center" style="width:100%;"><img src="https://github.com/pieromattos/desafio-mobile/blob/master/Docs/GIFs/PortraitDetails.gif" /></div>

---

### Landscape mode

When in landscape mode, the app adjusts the layout of the movie poster to provide a coverflow-like experience. The user can see more than one movie poster at once, and can more easily find an interesting one:

<div style="text-align:center" style="width:100%;"><img src="https://github.com/pieromattos/desafio-mobile/blob/master/Docs/GIFs/LandscapeBrowsing.gif" /></div>

---

Since the poster are now too small to flip around to display the movie details, this presentation takes the form of a full-screen modal with the same information that's available in portrait mode:

<div style="text-align:center" style="width:100%;"><img src="https://github.com/pieromattos/desafio-mobile/blob/master/Docs/GIFs/LandscapeDetails.gif" /></div>

---

### Full screen poster

When viewing details about a movie, users can tap the small poster thumbnail on the upper right corner to get a better view of the movie poster:

<div style="text-align:center" style="width:100%;"><img src="https://github.com/pieromattos/desafio-mobile/blob/master/Docs/GIFs/PortraitFullScreenPoster.gif" /></div>

---

### Trailer videos

Users can also scroll down and watch the movie's trailer directly from YouTube:

<div style="text-align:center" style="width:100%;"><img src="https://github.com/pieromattos/desafio-mobile/blob/master/Docs/GIFs/LandscapeMovieTrailer.gif" /></div>

---

### Link to IMDB

Finally, at the end of the details information, there is a link to the movie's entry on IMDB, which opens Safari so users can get even more information about the film:

<div style="text-align:center" style="width:100%;"><img src="https://github.com/pieromattos/desafio-mobile/blob/master/Docs/GIFs/PortraitLinkToImdb.gif" /></div>

---

## Technical info

### Project dependencies

The Movies app project has one dependency only: the [HTTPeasy framework](https://github.com/pieromattos/HTTPeasy). This is a framework created by me, which makes it really simple to perform HTTP requests, it's fully independent and has a code coverage of 94%.

In order to run the Movies app, you'll need to first make sure you have Carthage installed on your machine. If you're not familiar with Carthage, you can learn more about it [over here](https://github.com/Carthage/Carthage).

You can run the following command while in the project's root directory:

``` sh
carthage update --no-use-binaries
```

This will make Carthage fetch the `HTTPeasy` framework from GitHub and build it fresh from source in your machine.

After this step is done, you can now run the app using Xcode and it should build just fine.

### Architecture overview

The entire application uses a MVC (model-view-controller) architecture. This means each "feature" or "screen" usually has the following structure:

```
My Feature
  - Feature.storyboard
  - FeatureVC.swift
```

The model layer consist mainly of the Structs used for encapsulating the data that comes from The Movie DB APIs.

Rather than simply parsing the data through conformance to `Decodable`, most of the model structs implement a custom decoding initializer.

This was done to intercept the decoding process and transform the data received into the proper Swift types to be used throughout the app (for example: using a proper `URL` instance to represent the path to an image asset on the web, rather than a `String`).

### Auxiliary components & extensions

Additional auxiliary files for each feature might exist alongside the view and controller files, such as `.nib` files, or components that help organzie the feature better by providing some encapsulation to resuable functionality.

Extension on Swift's built-in types also provide syntactic sugar and easy access to commonly used objects/methods.

Examples of these are:

- `Components/MovieDetailsView`: encapsulate the detailed view of a movie and makes it easier to present this information either on the back of the movie poster (portrait mode), or as a modal (landscape mode).
- `Components/PersonView`: encapsulates the setup of simple rounded views that displays a person's information, alongside their picture.
- `Extensions/CGSize`: provides an easy mechanism for scaling down a given size to fit inside a container size (used to determine the proper size of the movie posters for each orientation configuration).
- `Extensions/UIImageView`: provides an easy mechanism to asynchronously load images from the web, while supporting a fallback placeholder image to be used when the proper image cannot be loaded.

### Assets

Some assets were used to provide a nicer user interface, those were:

- Movie, by Mikicon ([Noun Project link](https://thenounproject.com/term/movie/769946/))
- Info, by Justiconnic, Sumenep ([Noun Project link](https://thenounproject.com/term/info/2754051/))
- Person, by Simon Mettler, CH ([Noun Project link](https://thenounproject.com/term/person/113116/))

### Improvement ideas

## Contributions, feedback, and more about me


If you have contributions or feedback to provide regarding this project, please do so by contacing me at piero_mattos@icloud.com.

You can also find more information about me on my webstie at: [pmattos.me](https://pmattos.me).

Or follow me on Twitter at: [@piero_mattos](https://twitter.com/piero_mattos)
