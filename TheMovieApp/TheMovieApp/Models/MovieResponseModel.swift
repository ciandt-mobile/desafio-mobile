//
//  MovieResponseModel.swift
//  TheMovieApp
//
//  Created by Wilson Kim on 25/06/19.
//  Copyright © 2019 WilsonKim. All rights reserved.
//

import Foundation

class MovieViewModel {
    var id:Int64
    var averageRating:Double
    var genresIds:[Int]
    var originalTitle:String
    var backdropPath:String
    var isAdult:Bool
    var popularity:Double
    var posterPath:String
    var title:String
    var overview:String
    var originalLanguage:String
    var voteCount:Int64
    var releaseDate:Date
    var isVideo:Bool
    
    init(_id:Int64,
    _averageRating:Double,
    _genresIds:[Int],
    _originalTitle:String,
    _backdropPath:String,
    _isAdult:Bool,
    _popularity:Double,
    _posterPath:String,
    _title:String,
    _overview:String,
    _originalLanguage:String,
    _voteCount:Int64,
    _releaseDate:Date,
    _isVideo:Bool) {
        id = _id
        averageRating = _averageRating
        genresIds = _genresIds
        originalTitle = _originalTitle
        backdropPath = _backdropPath
        isAdult = _isAdult
        popularity = _popularity
        posterPath = _posterPath
        title = _title
        overview = _overview
        originalLanguage = _originalLanguage
        voteCount = _voteCount
        releaseDate = _releaseDate
        isVideo = _isVideo
    }
}

class GeneralMovieResponseModel: Codable {
    
    var totalPages:Int
    var totalResults:Int
    var currentPage:Int
    var results:[MovieResponseModel]
    enum CodingKeys: String, CodingKey {
        case totalPages = "total_pages"
        case totalResults = "total_results"
        case currentPage = "page"
        case results = "results"
    }
    
    func getMoviesModel() -> [MovieViewModel] {
        var returnMovies:[MovieViewModel] = []
        for result in results {
            returnMovies.append(result.getMovieModel())
        }
        return returnMovies
    }
}

class MovieResponseModel: Codable {
    var id:Int64
    var averageRating:Double
    var genresIds:[Int]
    var originalTitle:String
    var backdropPath:String?
    var isAdult:Bool
    var popularity:Double
    var posterPath:String
    var title:String
    var overview:String
    var originalLanguage:String
    var voteCount:Int64
    var releaseDate:String
    var isVideo:Bool
    
    enum CodingKeys: String, CodingKey {
        case id = "id"
        case averageRating = "vote_average"
        case genresIds = "genre_ids"
        case originalTitle = "original_title"
        case backdropPath = "backdrop_path"
        case isAdult = "adult"
        case popularity = "popularity"
        case posterPath = "poster_path"
        case title = "title"
        case overview = "overview"
        case originalLanguage = "original_language"
        case voteCount = "vote_count"
        case releaseDate = "release_date"
        case isVideo = "video"
    }
    
    func getMovieModel() -> MovieViewModel {
        return MovieViewModel(_id: id,
                              _averageRating: averageRating,
                              _genresIds: genresIds,
                              _originalTitle: originalTitle,
                              _backdropPath: backdropPath ?? "",
                              _isAdult: isAdult,
                              _popularity: popularity,
                              _posterPath: posterPath,
                              _title: title,
                              _overview: overview,
                              _originalLanguage: originalLanguage,
                              _voteCount: voteCount,
                              _releaseDate: Date.dateFrom(string: releaseDate) ?? Date(),
                              _isVideo: isVideo)
    }
}

//{
//    "id" : 329996,
//    "vote_average" : 6.5,
//    "genre_ids" : [
//    12,
//    10751,
//    14
//    ],
//    "original_title" : "Dumbo",
//    "backdrop_path" : "\/5tFt6iuGnKapHl5tw0X0cKcnuVo.jpg",
//    "adult" : false,
//    "popularity" : 108.077,
//    "poster_path" : "\/279PwJAcelI4VuBtdzrZASqDPQr.jpg",
//    "title" : "Dumbo",
//    "overview" : "A young elephant, whose oversized ears enable him to fly, helps save a struggling circus, but when the circus plans a new venture, Dumbo and his friends discover dark secrets beneath its shiny veneer.",
//    "original_language" : "en",
//    "vote_count" : 1363,
//    "release_date" : "2019-03-27",
//    "video" : false
//}

//{
//    "total_pages" : 992,
//    "total_results" : 19829,
//    "page" : 1,
//    "results" : [
//    {
//    "id" : 301528,
//    "vote_average" : 7.4000000000000004,
//    "genre_ids" : [
//    12,
//    16,
//    35,
//    10751
//    ],
//    "original_title" : "Toy Story 4",
//    "backdrop_path" : "\/m67smI1IIMmYzCl9axvKNULVKLr.jpg",
//    "adult" : false,
//    "popularity" : 371.27800000000002,
//    "poster_path" : "\/w9kR8qbmQ01HwnvK4alvnQ2ca0L.jpg",
//    "title" : "Toy Story 4",
//    "overview" : "Woody has always been confident about his place in the world and that his priority is taking care of his kid, whether that's Andy or Bonnie. But when Bonnie adds a reluctant new toy called \"Forky\" to her room, a road trip adventure alongside old and new friends will show Woody how big the world can be for a toy.",
//    "original_language" : "en",
//    "vote_count" : 299,
//    "release_date" : "2019-06-19",
//    "video" : false
//    },
//    {
//    "id" : 320288,
//    "vote_average" : 6.4000000000000004,
//    "genre_ids" : [
//    878,
//    28
//    ],
//    "original_title" : "Dark Phoenix",
//    "backdrop_path" : "\/phxiKFDvPeQj4AbkvJLmuZEieDU.jpg",
//    "adult" : false,
//    "popularity" : 213.36699999999999,
//    "poster_path" : "\/kZv92eTc0Gg3mKxqjjDAM73z9cy.jpg",
//    "title" : "Dark Phoenix",
//    "overview" : "The X-Men face their most formidable and powerful foe when one of their own, Jean Grey, starts to spiral out of control. During a rescue mission in outer space, Jean is nearly killed when she's hit by a mysterious cosmic force. Once she returns home, this force not only makes her infinitely more powerful, but far more unstable. The X-Men must now band together to save her soul and battle aliens that want to use Grey's new abilities to rule the galaxy.",
//    "original_language" : "en",
//    "vote_count" : 933,
//    "release_date" : "2019-06-05",
//    "video" : false
//    },
//    {
//    "id" : 299537,
//    "vote_average" : 7,
//    "genre_ids" : [
//    28,
//    12,
//    878
//    ],
//    "original_title" : "Captain Marvel",
//    "backdrop_path" : "\/w2PMyoyLU22YvrGK3smVM9fW1jj.jpg",
//    "adult" : false,
//    "popularity" : 177.94,
//    "poster_path" : "\/AtsgWhDnHTq68L0lLsUrCnM7TjG.jpg",
//    "title" : "Captain Marvel",
//    "overview" : "The story follows Carol Danvers as she becomes one of the universe’s most powerful heroes when Earth is caught in the middle of a galactic war between two alien races. Set in the 1990s, Captain Marvel is an all-new adventure from a previously unseen period in the history of the Marvel Cinematic Universe.",
//    "original_language" : "en",
//    "vote_count" : 5908,
//    "release_date" : "2019-03-06",
//    "video" : false
//    },
//    {
//    "id" : 479455,
//    "vote_average" : 6.0999999999999996,
//    "genre_ids" : [
//    28,
//    35,
//    878,
//    12
//    ],
//    "original_title" : "Men in Black: International",
//    "backdrop_path" : "\/2FYzxgLNuNVwncilzUeCGbOQzP7.jpg",
//    "adult" : false,
//    "popularity" : 160.494,
//    "poster_path" : "\/dPrUPFcgLfNbmDL8V69vcrTyEfb.jpg",
//    "title" : "Men in Black: International",
//    "overview" : "The Men in Black have always protected the Earth from the scum of the universe. In this new adventure, they tackle their biggest, most global threat to date: a mole in the Men in Black organization.",
//    "original_language" : "en",
//    "vote_count" : 279,
//    "release_date" : "2019-06-12",
//    "video" : false
//    },
//    {
//    "id" : 420817,
//    "vote_average" : 7.2000000000000002,
//    "genre_ids" : [
//    12,
//    14,
//    10749,
//    35,
//    10751
//    ],
//    "original_title" : "Aladdin",
//    "backdrop_path" : "\/v4yVTbbl8dE1UP2dWu5CLyaXOku.jpg",
//    "adult" : false,
//    "popularity" : 143.41,
//    "poster_path" : "\/3iYQTLGoy7QnjcUYRJy4YrAgGvp.jpg",
//    "title" : "Aladdin",
//    "overview" : "A kindhearted street urchin named Aladdin embarks on a magical adventure after finding a lamp that releases a wisecracking genie while a power-hungry Grand Vizier vies for the same lamp that has the power to make their deepest wishes come true.",
//    "original_language" : "en",
//    "vote_count" : 1816,
//    "release_date" : "2019-05-22",
//    "video" : false
//    },
//    {
//    "id" : 532321,
//    "vote_average" : 5.2000000000000002,
//    "genre_ids" : [
//    16,
//    12
//    ],
//    "original_title" : "Re:ゼロから始める異世界生活 Memory Snow",
//    "backdrop_path" : "\/8sNz2DxYiYqGkxk66U8BqvuZZjE.jpg",
//    "adult" : false,
//    "popularity" : 139.01400000000001,
//    "poster_path" : "\/xqR4ABkFTFYe8NDJi3knwWX7zfn.jpg",
//    "title" : "Re: Zero kara Hajimeru Isekai Seikatsu - Memory Snow",
//    "overview" : "Subaru and friends finally get a moment of peace, and Subaru goes on a certain secret mission that he must not let anyone find out about! However, even though Subaru is wearing a disguise, Petra and other children of the village immediately figure out who he is. Now that his mission was exposed within five seconds of it starting, what will happen with Subaru's \"date course\" with Emilia?",
//    "original_language" : "ja",
//    "vote_count" : 25,
//    "release_date" : "2018-10-06",
//    "video" : false
//    },
//    {
//    "id" : 458156,
//    "vote_average" : 7.0999999999999996,
//    "genre_ids" : [
//    80,
//    28,
//    53
//    ],
//    "original_title" : "John Wick: Chapter 3 – Parabellum",
//    "backdrop_path" : "\/vVpEOvdxVBP2aV166j5Xlvb5Cdc.jpg",
//    "adult" : false,
//    "popularity" : 131.202,
//    "poster_path" : "\/ziEuG1essDuWuC5lpWUaw1uXY2O.jpg",
//    "title" : "John Wick: Chapter 3 – Parabellum",
//    "overview" : "Super-assassin John Wick returns with a $14 million price tag on his head and an army of bounty-hunting killers on his trail. After killing a member of the shadowy international assassin’s guild, the High Table, John Wick is excommunicado, but the world’s most ruthless hit men and women await his every turn.",
//    "original_language" : "en",
//    "vote_count" : 1227,
//    "release_date" : "2019-05-15",
//    "video" : false
//    },
//    {
//    "id" : 480042,
//    "vote_average" : 5.5,
//    "genre_ids" : [
//    28,
//    53
//    ],
//    "original_title" : "Escape Plan: The Extractors",
//    "backdrop_path" : "\/ygWKYTu7OnZKF9H5NsgjL9iURGV.jpg",
//    "adult" : false,
//    "popularity" : 112.41800000000001,
//    "poster_path" : "\/mgHu3OcwEFAm3GV9p2AoCoCEJPP.jpg",
//    "title" : "Escape Plan: The Extractors",
//    "overview" : "After security expert Ray Breslin is hired to rescue the kidnapped daughter of a Hong Kong tech mogul from a formidable Latvian prison, Breslin's girlfriend is also captured. Now he and his team must pull off a deadly rescue mission to confront their sadistic foe and save the hostages before time runs out.",
//    "original_language" : "en",
//    "vote_count" : 20,
//    "release_date" : "2019-06-20",
//    "video" : false
//    },
//    {
//    "id" : 299536,
//    "vote_average" : 8.3000000000000007,
//    "genre_ids" : [
//    12,
//    28,
//    14
//    ],
//    "original_title" : "Avengers: Infinity War",
//    "backdrop_path" : "\/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg",
//    "adult" : false,
//    "popularity" : 106.63,
//    "poster_path" : "\/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg",
//    "title" : "Avengers: Infinity War",
//    "overview" : "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
//    "original_language" : "en",
//    "vote_count" : 14100,
//    "release_date" : "2018-04-25",
//    "video" : false
//    },
//    {
//    "id" : 299534,
//    "vote_average" : 8.4000000000000004,
//    "genre_ids" : [
//    12,
//    878,
//    28
//    ],
//    "original_title" : "Avengers: Endgame",
//    "backdrop_path" : "\/7RyHsO4yDXtBv1zUU3mTpHeQ0d5.jpg",
//    "adult" : false,
//    "popularity" : 104.13500000000001,
//    "poster_path" : "\/or06FN3Dka5tukK1e9sl16pB3iy.jpg",
//    "title" : "Avengers: Endgame",
//    "overview" : "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
//    "original_language" : "en",
//    "vote_count" : 7151,
//    "release_date" : "2019-04-24",
//    "video" : false
//    },
//    {
//    "id" : 514999,
//    "vote_average" : 6.5,
//    "genre_ids" : [
//    35,
//    80,
//    9648,
//    28
//    ],
//    "original_title" : "Murder Mystery",
//    "backdrop_path" : "\/qeFO3u2IqbAeIT1Xgo6POqYbCaQ.jpg",
//    "adult" : false,
//    "popularity" : 100.313,
//    "poster_path" : "\/bSMSO9xupd4R4vwTPqigHn2quLN.jpg",
//    "title" : "Murder Mystery",
//    "overview" : "After attending a gathering on a billionaire's yacht during a European vacation, a New York cop and his wife become prime suspects when he's murdered.",
//    "original_language" : "en",
//    "vote_count" : 638,
//    "release_date" : "2019-06-14",
//    "video" : false
//    },
//    {
//    "id" : 329996,
//    "vote_average" : 6.5,
//    "genre_ids" : [
//    12,
//    10751,
//    14
//    ],
//    "original_title" : "Dumbo",
//    "backdrop_path" : "\/5tFt6iuGnKapHl5tw0X0cKcnuVo.jpg",
//    "adult" : false,
//    "popularity" : 94.421999999999997,
//    "poster_path" : "\/279PwJAcelI4VuBtdzrZASqDPQr.jpg",
//    "title" : "Dumbo",
//    "overview" : "A young elephant, whose oversized ears enable him to fly, helps save a struggling circus, but when the circus plans a new venture, Dumbo and his friends discover dark secrets beneath its shiny veneer.",
//    "original_language" : "en",
//    "vote_count" : 1342,
//    "release_date" : "2019-03-27",
//    "video" : false
//    },
//    {
//    "id" : 399579,
//    "vote_average" : 6.7000000000000002,
//    "genre_ids" : [
//    28,
//    878,
//    53,
//    12
//    ],
//    "original_title" : "Alita: Battle Angel",
//    "backdrop_path" : "\/aQXTw3wIWuFMy0beXRiZ1xVKtcf.jpg",
//    "adult" : false,
//    "popularity" : 93.369,
//    "poster_path" : "\/xRWht48C2V8XNfzvPehyClOvDni.jpg",
//    "title" : "Alita: Battle Angel",
//    "overview" : "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
//    "original_language" : "en",
//    "vote_count" : 2133,
//    "release_date" : "2019-01-31",
//    "video" : false
//    },
//    {
//    "id" : 429617,
//    "vote_average" : 5.5,
//    "genre_ids" : [
//    28,
//    12,
//    878
//    ],
//    "original_title" : "Spider-Man: Far from Home",
//    "backdrop_path" : "\/dihW2yTsvQlust7mSuAqJDtqW7k.jpg",
//    "adult" : false,
//    "popularity" : 91.989999999999995,
//    "poster_path" : "\/dzg8lHS78l5Xxpd2EM5H541Qn2s.jpg",
//    "title" : "Spider-Man: Far from Home",
//    "overview" : "Peter Parker and his friends go on a summer trip to Europe. However, they will hardly be able to rest - Peter will have to agree to help Nick Fury uncover the mystery of creatures that cause natural disasters and destruction throughout the continent.",
//    "original_language" : "en",
//    "vote_count" : 15,
//    "release_date" : "2019-06-28",
//    "video" : false
//    },
//    {
//    "id" : 533642,
//    "vote_average" : 5.7000000000000002,
//    "genre_ids" : [
//    27
//    ],
//    "original_title" : "Child's Play",
//    "backdrop_path" : "\/8Vjfen3Jit0nsMtu8huT09UqmsS.jpg",
//    "adult" : false,
//    "popularity" : 91.912000000000006,
//    "poster_path" : "\/xECBpdm4OXXlLajAlcvopXZFiQD.jpg",
//    "title" : "Child's Play",
//    "overview" : "The story follows a mother named Karen, who gives her son Andy a toy doll for his birthday, unaware of its sinister nature.",
//    "original_language" : "en",
//    "vote_count" : 58,
//    "release_date" : "2019-06-19",
//    "video" : false
//    },
//    {
//    "id" : 166428,
//    "vote_average" : 7.5999999999999996,
//    "genre_ids" : [
//    16,
//    10751,
//    12
//    ],
//    "original_title" : "How to Train Your Dragon: The Hidden World",
//    "backdrop_path" : "\/lFwykSz3Ykj1Q3JXJURnGUTNf1o.jpg",
//    "adult" : false,
//    "popularity" : 89.683999999999997,
//    "poster_path" : "\/xvx4Yhf0DVH8G4LzNISpMfFBDy2.jpg",
//    "title" : "How to Train Your Dragon: The Hidden World",
//    "overview" : "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
//    "original_language" : "en",
//    "vote_count" : 2117,
//    "release_date" : "2019-01-03",
//    "video" : false
//    },
//    {
//    "id" : 280960,
//    "vote_average" : 4.4000000000000004,
//    "genre_ids" : [
//    18,
//    9648
//    ],
//    "original_title" : "Catarina e os Outros",
//    "backdrop_path" : "\/8FfD5WXHrJLx0r4expl9IIBMsOW.jpg",
//    "adult" : false,
//    "popularity" : 86.103999999999999,
//    "poster_path" : "\/kZMCbp0o46Tsg43omSHNHJKNTx9.jpg",
//    "title" : "Catarina and the others",
//    "overview" : "Outside, the first sun rays break the dawn.  Sixteen years old Catarina can't fall asleep.  Inconsequently, in the big city adults are moved by desire...  Catarina found she is HIV positive. She wants to drag everyone else along.",
//    "original_language" : "pt",
//    "vote_count" : 48,
//    "release_date" : "2011-03-01",
//    "video" : false
//    },
//    {
//    "id" : 920,
//    "vote_average" : 6.7000000000000002,
//    "genre_ids" : [
//    16,
//    12,
//    35,
//    10751
//    ],
//    "original_title" : "Cars",
//    "backdrop_path" : "\/a1MlbLBk5Sy6YvMbSuKfwGlDVlb.jpg",
//    "adult" : false,
//    "popularity" : 85.712999999999994,
//    "poster_path" : "\/jpfkzbIXgKZqCZAkEkFH2VYF63s.jpg",
//    "title" : "Cars",
//    "overview" : "Lightning McQueen, a hotshot rookie race car driven to succeed, discovers that life is about the journey, not the finish line, when he finds himself unexpectedly detoured in the sleepy Route 66 town of Radiator Springs. On route across the country to the big Piston Cup Championship in California to compete against two seasoned pros, McQueen gets to know the town's offbeat characters.",
//    "original_language" : "en",
//    "vote_count" : 7608,
//    "release_date" : "2006-06-08",
//    "video" : false
//    },
//    {
//    "id" : 566555,
//    "vote_average" : 4.7999999999999998,
//    "genre_ids" : [
//    16,
//    28,
//    18,
//    9648,
//    35
//    ],
//    "original_title" : "名探偵コナン 紺青の拳（フィスト）",
//    "backdrop_path" : "\/wf6VDSi4aFCZfuD8sX8JAKLfJ5m.jpg",
//    "adult" : false,
//    "popularity" : 85.617000000000004,
//    "poster_path" : "\/86Y6qM8zTn3PFVfCm9J98Ph7JEB.jpg",
//    "title" : "Detective Conan: The Fist of Blue Sapphire",
//    "overview" : "23rd movie in the \"Detective Conan\" franchise.",
//    "original_language" : "ja",
//    "vote_count" : 16,
//    "release_date" : "2019-04-12",
//    "video" : false
//    },
//    {
//    "id" : 531309,
//    "vote_average" : 5.5999999999999996,
//    "genre_ids" : [
//    27,
//    878,
//    18,
//    53
//    ],
//    "original_title" : "Brightburn",
//    "backdrop_path" : "\/j3w3lT3ABvJsVE3byNOMCYmnGMB.jpg",
//    "adult" : false,
//    "popularity" : 85.527000000000001,
//    "poster_path" : "\/v3PLKFKHrUYHhZXcSuW6Fl48laG.jpg",
//    "title" : "Brightburn",
//    "overview" : "What if a child from another world crash-landed on Earth, but instead of becoming a hero to mankind, he proved to be something far more sinister?",
//    "original_language" : "en",
//    "vote_count" : 241,
//    "release_date" : "2019-05-09",
//    "video" : false
//    }
//    ]
//}
