package com.mfarhan08a.hangoutyuk.data.model

//object FakeDataPlace {
//    val placesData = listOf(
//        Place(
//            id = 1,
//            distance = 4.56,
//            photoUrl = "https://cdn2.tstatic.net/tribunnewswiki/foto/bank/images/Gerai-Starbucks.jpg",
//            name = "Starbucks Coffee 1",
//            category = "Coffee Shop",
//            address = "Jl. Maoboro No.41, Sosromenduran, Gedong Tengen, Kota Yogyakarta, Daerah Istimewa Yogyakarta",
//            rating = 4.5,
//            totalReview = 69.9 ,
//            about = "Seattle-based coffeehouse chain known for its signature roasts, light bites and WiFi availability.",
//            review = listOf(
//                Review(
//                    id = 1,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 2,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 3,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//            ),
//            contactPhone = "085299887766",
//            contactWeb = "Starbuck.id",
//        ),
//        Place(
//            id = 2,
//            distance = 4.56,
//            photoUrl = "https://cdn2.tstatic.net/tribunnewswiki/foto/bank/images/Gerai-Starbucks.jpg",
//            name = "Starbucks Coffee 2",
//            category = "Coffee Shop",
//            address = "Jl. Maoboro No.41, Sosromenduran, Gedong Tengen, Kota Yogyakarta, Daerah Istimewa Yogyakarta",
//            rating = 4.5,
//            totalReview = 96.9,
//            about = "Seattle-based coffeehouse chain known for its signature roasts, light bites and WiFi availability.",
//            review = listOf(
//                Review(
//                    id = 1,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 2,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 3,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 4,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 5,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//            ),
//            contactPhone = "085299887766",
//            contactWeb = "Starbuck.id",
//        ),
//        Place(
//            id = 3,
//            distance = 4.56,
//            photoUrl = "https://cdn2.tstatic.net/tribunnewswiki/foto/bank/images/Gerai-Starbucks.jpg",
//            name = "Starbucks Coffee 3",
//            category = "Coffee Shop",
//            address = "Jl. Maoboro No.41, Sosromenduran, Gedong Tengen, Kota Yogyakarta, Daerah Istimewa Yogyakarta",
//            rating = 4.5,
//            totalReview = 96.9,
//            about = "Seattle-based coffeehouse chain known for its signature roasts, light bites and WiFi availability.",
//            review = listOf(
//                Review(
//                    id = 1,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 2,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 3,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//            ),
//            contactPhone = "085299887766",
//            contactWeb = "Starbuck.id",
//        ),
//        Place(
//            id = 4,
//            distance = 4.56,
//            photoUrl = "https://cdn2.tstatic.net/tribunnewswiki/foto/bank/images/Gerai-Starbucks.jpg",
//            name = "Starbucks Coffee 4",
//            category = "Coffee Shop",
//            address = "Jl. Maoboro No.41, Sosromenduran, Gedong Tengen, Kota Yogyakarta, Daerah Istimewa Yogyakarta",
//            rating = 4.5,
//            totalReview = 96.9,
//            about = "Seattle-based coffeehouse chain known for its signature roasts, light bites and WiFi availability.",
//            review = listOf(
//                Review(
//                    id = 1,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 2, rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 3,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//            ),
//            contactPhone = "085299887766",
//            contactWeb = "Starbuck.id",
//        ),
//        Place(
//            id = 5,
//            distance = 4.56,
//            photoUrl = "https://cdn2.tstatic.net/tribunnewswiki/foto/bank/images/Gerai-Starbucks.jpg",
//            name = "Starbucks Coffee 5",
//            category = "Coffee Shop",
//            address = "Jl. Maoboro No.41, Sosromenduran, Gedong Tengen, Kota Yogyakarta, Daerah Istimewa Yogyakarta",
//            rating = 4.5,
//            totalReview = 96.9,
//            about = "Seattle-based coffeehouse chain known for its signature roasts, light bites and WiFi availability.",
//            review = listOf(
//                Review(
//                    id = 1,
//                    rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 2, rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//                Review(
//                    id = 3, rating = 4.5,
//                    username = "Jason Susanto",
//                    reviewDetail = "Kopi disini enak mantap"
//                ),
//            ),
//            contactPhone = "085299887766",
//            contactWeb = "Starbuck.id",
//        ),
//    )
//}