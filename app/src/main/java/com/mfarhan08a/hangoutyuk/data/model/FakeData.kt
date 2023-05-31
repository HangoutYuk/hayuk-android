package com.mfarhan08a.hangoutyuk.data.model

import org.json.JSONObject

object FakeData {
    val placesDataJSON = """
        {
            "status": "OK",
            "message": "Daftar berhasil didapatkan",
            "data": [
                {
                    "id": "ChIJ16Xs67JZei4RpgV1otKBiO4",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0nnM5sAvjIeFYowm9dT1bTq2GdbU2qVEXKJuFar9swVlvufrvFL1_g0xGqrwxEsyhiMaMkppc7OoryJtayQLgrAy1x8X2PxaHbXWMEXIwBVOxOPYHEM9u4iT4eGvuNEucOJd6nivQ-7quFzwDhcwi5aRWz0Bpf-LgZDzM_uvVZaMPTS&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "Gudeg Yu Narni Pusat Mbarek",
                    "category": "restaurant",
                    "address": "Gg. Cokrowolo, Kocoran, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281, Indonesia",
                    "rating": 4.5,
                    "totalReview": 373,
                    "about": null,
                    "review": [
                        {
                            "id": "0",
                            "author": "Mutia Tiara",
                            "rating": 5,
                            "text": "Deket banget sama lokasi penginapan, makan siang di sini emm sedap! Tidak kemanisan, pedesnya anget anget banget, enak!\n\nBisa beli untuk oleh-oleh juga lo! Enak asli",
                            "time": "9 bulan lalu"
                        },
                        {
                            "id": "1",
                            "author": "Rifki Kurniawan",
                            "rating": 5,
                            "text": "Menurut saya ini Gudeg yg enak dan pas di lidah sunda saya. Apalagi kreceknya, harga pun terjangkau dibanding gudeg lainnya.",
                            "time": "3 bulan lalu"
                        },
                        {
                            "id": "2",
                            "author": "Blogger Kuliner",
                            "rating": 3,
                            "text": "Krecek keras, gudeg areh terlalu manis.. order tepong (paha atas) juga ayam terlalu keras dan hambar. Pertama nyoba dan terakhir.",
                            "time": "2 minggu yang lalu"
                        },
                        {
                            "id": "3",
                            "author": "Kumvid Niken",
                            "rating": 4,
                            "text": "Warung rmh bersih semebelnya,menurut saya: rasa gudegnya konsisten pas manisnya pas kematangannya(ga kering ga nyemek) ini semua yg sy suka dari gudeg Yu Narni, tehrasa pas juga manisnya👍🏻👍🏻👍🏻✅❤",
                            "time": "dalam minggu terakhir"
                        },
                        {
                            "id": "4",
                            "author": "Rini Panggabean",
                            "rating": 5,
                            "text": "So far buat aku ini resto Gudeg paling enak krn gak terlalu manis tapi bumbu ya meresap banget ke ayam. Uenak tenan.  Harga 1 porsi lengkap 39 k kalo gak salah. Standarlah.",
                            "time": "3 tahun lalu"
                        }
                    ],
                    "phone": "0878-3870-3999",
                    "website": null,
                    "latitude": -7.765867200000001,
                    "longitude": 110.3804744
                },
                {
                    "id": "ChIJUf61j6xZei4R8BquRPVGu6M",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0nx9bwCKeKwq1np5RBX37k3H2aU5dy6iwtWCM0KnwcbDA_TJAp_pxw2SU8Wx65XII-dEEOf9OkfUtw-0Ov9Ahbzj05Wrxzd6oP4sC7U1Bp7XpeE-ikerefNQQyTcTPBu1m2mD5jm8NLMMg1JGW3q8alh9JHuKOD3O0B3gDtCGgtCsAz&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "McDonald's",
                    "category": "restaurant",
                    "address": "Jl. Kaliurang No.KM. 5, Kocoran, Sinduadi, Kec. Mlati, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281, Indonesia",
                    "rating": 4.4,
                    "totalReview": 9570,
                    "about": "Franchise makanan cepat saji klasik yang telah lama berdiri, terkenal dengan burger dan kentang gorengnya.",
                    "review": [
                        {
                            "id": "0",
                            "author": "arriridhoO",
                            "rating": 5,
                            "text": "Salah satu fast food yg sdh tdk asing ditelinga, lokasi strategis, selalu ramai pengunjung, parkiran luas, mushola hanya terdapat di lantai dasar dket jalur drive thru, toilet terdapat di lantai 1 dan 2, utk lantai 1 selalu dilewati lalu lalang orang lewat, utk lantai 2 sering digunakan utk kumpul\" bersama sekedar nongkrong atau sembari nugas sambil cemal cemil",
                            "time": "7 bulan lalu"
                        },
                        {
                            "id": "1",
                            "author": "Dhea Adzana Putri",
                            "rating": 5,
                            "text": "Tempatnya nyaman, strategis, masih melayani dengan kasir di saat MCD lain menggunakan self serivice yang malah membuat lebih repot, mungkin karena belum terbiasa aja. Tapi tetap lebih nyaman jika dilayani.\n\nParkir nya sangat ramah, mushola aja, lantai 2 nyaman. Terimakasih",
                            "time": "10 bulan lalu"
                        },
                        {
                            "id": "2",
                            "author": "Asri's",
                            "rating": 5,
                            "text": "Kenapa Mcd store disini belum ada mesin self ordernya, kan biar ordernya lebih leluasa. Tetep bisa nukerin penawaran yg di aplikasi kok di kasir, terus pembayaran bisa qris dan banyak lagi ✨\nOverall nicee banget meskin cukup kecil tempatnya. Sebelahan sama tempo gelato, setiap kesini sangat tergoda sekali buat melipir ke sebelahnya 😔\nMungkin bisa segera susul kaya store mcd yg di Jombor sama Sudirman ya, ada mesin self ordernya biar lebih leluasa dan cepet sat set 🫶",
                            "time": "3 minggu yang lalu"
                        },
                        {
                            "id": "3",
                            "author": "suryo purnomo edi",
                            "rating": 5,
                            "text": "Salah satu restoran waralaba cepat saji di Jl. Kaliurang. Tempat strategis, sehingga banyak pengunjungnya. Di jam-jam tertentu antrian panjang sehingga kita harus bersabar. Seperti restoran cepat saji lainnya, menyediakan ayam goreng, burger, dll. Tempat makannya terdiri dari dua lantai, di lantai dua bagian balkon khusus area merokok. Tempatnya bersih, lahan parkir luas, bisa buat tempat nongkrong, layak untuk dikunjungi.",
                            "time": "sebulan yang lalu"
                        },
                        {
                            "id": "4",
                            "author": "Farhan Amen",
                            "rating": 5,
                            "text": "Mcd di Jalan Kaliurang! Tempat besar dengan parkir yang luas. Rasa dan harga seperti Mcd pada umumnya. Pelayanan cepat dan ramah menambah kenyamanan kamu yang sedang ingin menikmati santapan Mcd.",
                            "time": "5 bulan lalu"
                        }
                    ],
                    "phone": "0811-1786-136",
                    "website": "http://www.mcdonalds.co.id/",
                    "latitude": -7.7625093,
                    "longitude": 110.3796601
                },
                {
                    "id": "ChIJfY2XWq1Zei4RxW9hFtkamQo",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0lTdgL27jN0DySM3ptb4HoO23uZGbG76hnqFVosm3tMJY2Xp8cn980lKVxy4NxKZYdaFZ_9qURsbNBa-hShzJUtmybq1ROiwG1vNUDpARWVRcCnJqOZxGeWBx1X4_7yjnuFcpqiO477cjbNKIf5fNoyXdTX-Am5yXShZmX1UdhAVtRO&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "Cakra Kusuma Hotel Yogyakarta",
                    "category": "lodging",
                    "address": "KM.5,2, Jl. Kaliurang No.25, Karang Wuni, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281, Indonesia",
                    "rating": 4.2,
                    "totalReview": 3018,
                    "about": "Hotel trendi yang menawarkan kamar dan suite klasik, serta lounge/bar, kolam renang & spa.",
                    "review": [
                        {
                            "id": "0",
                            "author": "Jane Netanya",
                            "rating": 3,
                            "text": "Menginap disini rasanya cukup senang, kamarnya luas cocok untuk menginap dengan keluarga. Tetapi mungkin ke kurangannya ketika mau menambah extrabed pihak hotel tidak ada stok, dan juga kolam renangnya ada beberapa yang sudah mengelupas jadi sedikit sakit di kaki. Overall pelayanannya bagus 👍, breakfastnya juga enak.\nBuat teman-teman yang ingin menginap dan dekat mau kemana mana cakra kusama bisa jadi salah satu opsinya",
                            "time": "3 bulan lalu"
                        },
                        {
                            "id": "1",
                            "author": "rizka amalia winoto",
                            "rating": 3,
                            "text": "Lokasi sangat strategis banyak Cafe & toko oleh² di sekitarnya. Kamar nya standar aja, kebersihan perlu ditingkatkan perawatan nya. Untuk sarapan jumlah menu nya perlu ditambah, karena ketika saya mau sarapan jam 8.30 sudah habis & menunggu refil tidak datang². Akhir nya saya sarapan diluar. Untung nya dekat dengan jualan gudeg².",
                            "time": "5 bulan lalu"
                        },
                        {
                            "id": "2",
                            "author": "Herdy Trainer Origami",
                            "rating": 5,
                            "text": "Lokasi cukup strategis. Hidangan cukup.\nKaryawan ramah.\nMungkin perlu akses untuk yang menggunakan kursi roda. Terutama untuk menuju lantai 2. Pegangan di kanan kiri tangga mungkin bisa dibuat lebih nyaman dan mudah digunakan bagi yang mempunyai kesulitan naik turun tangga.",
                            "time": "sebulan yang lalu"
                        },
                        {
                            "id": "3",
                            "author": "yopy yuliawan",
                            "rating": 1,
                            "text": "Check in jam 14.30, kamar belum siap, harus menunggu lebih dari 15 menit. Internet jg gak nyambung, katanya lagi dilaporkan ke pusat utk perbaikan. Terakhir kesini sekitar 2013 lalu semua oke, tapi sekarang parah beud pelayanannya. Gak lagi2 staycation di sini.",
                            "time": "3 bulan lalu"
                        },
                        {
                            "id": "4",
                            "author": "Yusuf Firmansyah",
                            "rating": 5,
                            "text": "Lokasinya dekat dengan kafe2 dan tempat makan, bangunan oke aja, untuk kamar termasuk luas, harga bersaing, sarapan menu rumahan tapi cukup enak, pelayanan lumayan oke, kebersihan dan keamanan oke, hp saya tertinggl dikamar tapi pas saya balik posisi kamar sudah bersih dan hp saya masih ada 👍",
                            "time": "4 minggu yang lalu"
                        }
                    ],
                    "phone": "(0274) 588066",
                    "website": "http://www.cakrakusumahotel.com/",
                    "latitude": -7.759883100000001,
                    "longitude": 110.3803705
                },
                {
                    "id": "ChIJJ5csXqxZei4R5E53W3wFC98",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0kujZ2x5MmK6ovq6Yws08n9Yxpw_YbjNG0EMo4-Qp6HggGtrZDjrKqA8HYxmgu774rbvhlN3aLQhks8rnGZ7NZ8tZelpqqx9t1yxH79WVYvoNZ7KAj-Ki-CyAHu1fG1jloxUqgtp7Jzsw_i7q1yHPk4Sx7HIyD6rd5_1K_i-FSkM69j&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "Blackbone Coffee",
                    "category": "cafe",
                    "address": "Jl. Kaliurang Km. 5, Gg. Siti Sonya No.88, Kocoran, Sinduadi, Kec. Mlati, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281, Indonesia",
                    "rating": 4.5,
                    "totalReview": 1453,
                    "about": null,
                    "review": [
                        {
                            "id": "0",
                            "author": "Cynthia Annisa",
                            "rating": 4,
                            "text": "Pembayaran bisa dilakukan secara cash maupun scan qris. Rasa makanannya enak-enak, rasa minuman standar. Tempatnya kurang begitu bersih, perlu dilakukan deep clean. Ada stop kontak yg harus diperbaiki. Ada mushola, tetapi aroma mukenanya tidak sedap. Jadi secara kebersihan masih sangat kurang, semoga bisa diperbaiki kedepannya.",
                            "time": "5 bulan lalu"
                        },
                        {
                            "id": "1",
                            "author": "Nunuk Ambarwati",
                            "rating": 4,
                            "text": "LOKASI\nAgak masuk, lewat gang dulu, jadi suasananya tenang, sepi. Ngga bersliweran kendaraan. Dekat dengan UGM dan area bisnis di Jalan Kaliurang.\n\nMAKANAN\nKopinya enak, saya milih menu signature mereka. Harganya masih wajar. Staf baik dan cukup ramah.\n\nSUASANA\nNampaknya disini dikenal sama beberapa komunitas, yang pada kesini kebanyakan nugas atau meeting. Ada indoor (AC) dan outdoor. Tempatnya unik, nyampur interiornya antara Jawa klasik dan modern. Banyak mural atau grafiti di beberapa sudut temboknya. WIFI ada.\n\nLAIN LAIN\nPembayaran bisa mesin EDC (kartu debit/kredit), cash, QRIS. Cuma pas saya kesana, segala yang pakai BNI ngga bisa, baik QRIS atau EDCnya.\n\nTanamannya di luar banyak, gede gede juga, tapi juga satu meja ada banyak.semutnya :D\n\nPARKIR\nUntuk kendaraan roda 4, akan kesulitan parkir disini. Roda 2 masih oke.",
                            "time": "4 bulan lalu"
                        },
                        {
                            "id": "2",
                            "author": "Asti Asti",
                            "rating": 5,
                            "text": "Nasi goreng cumi nya enak banget! Kopi nya juga lumayan.\n\nDisini ga terlalu ramai, jadi cocok untuk yang butuh fokus. Kursi di indoor didesain untuk maksimal 2-3 orang saja.\n\nPelayanan ramah dan cepat.",
                            "time": "6 bulan lalu"
                        },
                        {
                            "id": "3",
                            "author": "Harits",
                            "rating": 5,
                            "text": "Tempat favorit dari tahun 2019 kalo ngopi di jogja, soalnya tempatnya tenang dan nyaman banget, cocok buat tempat curhat sama temen haha. Kopinya disini enak2, penyajian kopi nya di gelas, mantap banget, untuk kopi nya berasa banget, gak cuman kebanyakan susu. Makanan nya juga enak, saya terakhir kesini pesan Caesar Salad, sayur nya banyak, rotinya ada, telurnya ada, potongan ayam ada dan dressing nya enak. Harga tergolong murah sih, under 30k semua rata rata, wifi ada, parkiran motor luas, kalo mobil mungkin di pinggir jalan bisanya.",
                            "time": "9 bulan lalu"
                        },
                        {
                            "id": "4",
                            "author": "Ester Ana",
                            "rating": 5,
                            "text": "Tempatnya enakk buat sekedar ngobrol smaa temen, ngerjain tugasss juga okee banged, pilihan makanan Dan monumannya juga bykkkk, rekomen banged nongki di sini sore2",
                            "time": "8 bulan lalu"
                        }
                    ],
                    "phone": null,
                    "website": null,
                    "latitude": -7.7623107,
                    "longitude": 110.3782537
                },
                {
                    "id": "ChIJk82RHLNZei4Rdts9OiiSXrU",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0kcj3FYo-FyhmVqA78cgAE55VnqpCIGKBKTLoBFqKG9a7so5MRqNlXnqRSbg2HUf-pjgMXZcijWS2jRLLwQM4wBvxg_DZ07cGrIGkM4kJITT5JZUXMo_HY6u_xThqpOoJBAkpDlxMP1CyArNPskIoEKJOZdwztP5PB0CvGLgTa_jHoc&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "Hayam Wuruk Soft Bone Fried Chicken",
                    "category": "restaurant",
                    "address": "Jalan Kaliurang KM 5,5 No.18, Manggung, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55283, Indonesia",
                    "rating": 4.4,
                    "totalReview": 1415,
                    "about": null,
                    "review": [
                        {
                            "id": "0",
                            "author": "Ratna Yuwono",
                            "rating": 5,
                            "text": "Rumah makan ayam tulang lunak cabang baru Hayam Wuruk, lokasi padahal deket banget...\nMenu sama serba ayam dan tadi order ayam madu wijen, tahu telor dan sambal pete dibungkus\nEnak ayamnya empuk sambelnya joss 😋👌",
                            "time": "setahun yang lalu"
                        },
                        {
                            "id": "1",
                            "author": "Abdul Aziz",
                            "rating": 5,
                            "text": "Underrated resto banget! Bebek goreng prestonya sama sekali ga alot dan ga amis, full daging bisa dimakan semua. Bumbu sangat meresap semua bagian bisa dinikmati. Gurameh bakar matangnya pas, bumbu sangat imbang dan sedap. Ayam presto telur asin juga empuk, kuning telurnya tidak amis dan bumbunya meresap semua. Pelayanan sangat ramah. Tempat juga sangat bersih.",
                            "time": "sebulan yang lalu"
                        },
                        {
                            "id": "2",
                            "author": "Penjelajah Kuliner",
                            "rating": 4,
                            "text": "Siapa orang Jogja yang tidak kenal Resto ini... HW adalah resto ayam tulang Lunak yang terkenal di Jogja... Ayam dan tulangnya yang bener\" lunak.. pilihan menunya banyak...\nMulai Januari ini pindah ke tempat yang baru jauh lebih elegan dan mewah .. 15m keselatan dari resto yang lama sebelah Yamaha Motor",
                            "time": "setahun yang lalu"
                        },
                        {
                            "id": "3",
                            "author": "Syalinna",
                            "rating": 5,
                            "text": "Ayam tulang lunak favoritku di Jogja! 🥰 Wajib banget pesen menu ayam goreng telur asin kalo makan disini karena rasanya gurih banget dan ayamnya bisa di makan sampai ke tulang-tulangnya 😍",
                            "time": "3 minggu yang lalu"
                        },
                        {
                            "id": "4",
                            "author": "A Nonik Arini",
                            "rating": 5,
                            "text": "Gurameh fillet telur asinnya mantull.\nTempat bersih,pelayanan cepat & ramah.\nCocok untuk acara makan bersama keluarga maupun untuk tempat meeting.",
                            "time": "8 bulan lalu"
                        }
                    ],
                    "phone": "(0274) 563826",
                    "website": null,
                    "latitude": -7.759252900000001,
                    "longitude": 110.3811898
                },
                {
                    "id": "ChIJC0UFS61Zei4R2CeOyCw88KQ",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0mSugdUbwJWZtCDJE9xzSxtSWXkhByyjvSOs6y6Sx5UD1vIfjXSFEcQFiDL4dgoFmlUHXNHA26og_Sqrfr3lVI-fWkXuEO-wNsLL_Ct5Uzz2Fsa3yEhsWzNS2_ROvajHzhyEN_0FKjtwOIVTBFidnph4TDwPPetb28q_-ji5q1iOYdw&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "Bale Kinanti",
                    "category": "restaurant",
                    "address": "yg5284, Gg. Kinanti, Kocoran, Sinduadi, Kec. Mlati, Kabupaten Sleman, Daerah Istimewa Yogyakarta 5, Indonesia",
                    "rating": 4.3,
                    "totalReview": 1659,
                    "about": null,
                    "review": [
                        {
                            "id": "0",
                            "author": "yoga andika Satria",
                            "rating": 4,
                            "text": "Nggak sreg dengan harganya aja yang relatif jauh lebih mahal. Tapi ya emang kebayar dengan suasananya yang enak dan tempatnya yang luas. Makanannya enak-enak.  Pelayannya ramah-ramah. Parkiran motornya luas. Ada free wifi dan cepet koneksinya. Buka puasa di sini ada takjil gratis. Es campurnya enak.",
                            "time": "4 tahun lalu"
                        },
                        {
                            "id": "1",
                            "author": "Walking Around Chanel",
                            "rating": 5,
                            "text": "Good, tempat bersih. Cukup cepat pelayanannya. Bisa pilih tempat duduk lesehan atau menggunakan kursi. Cocok untuk rombongan. Empatnya santai, toilet dan mushola juga ada. Menu enak, bsnyak pilihan. Bakar ada, goreng ada.",
                            "time": "3 tahun lalu"
                        },
                        {
                            "id": "2",
                            "author": "Aulia Rofihatiar",
                            "rating": 5,
                            "text": "Bersih ada wastafel tapi toilet kurang tau ada atau enggak.  parkir mudah, halal, harganya sesuai dengan pelayanan yang diberikan. Tempatnya nyaman dan tidak terlalu sempit. Ada yang duduk di kursi dan lesehan. Resto ini terbuka, tidak ber ac. Harga di menu masih belum dikenakan pajak. Pajaknya 10%. Bisa pesan untuk box juga.",
                            "time": "4 tahun lalu"
                        },
                        {
                            "id": "3",
                            "author": "Frans Prasetya",
                            "rating": 5,
                            "text": "Menunya cukup banyak. Makananya tampilanya bagus, rasanya enak.\nTempat nyaman dan bersih. Harga sesuai rasa",
                            "time": "3 tahun lalu"
                        },
                        {
                            "id": "4",
                            "author": "Indi R",
                            "rating": 4,
                            "text": "Makanan disini enak, bersih, nyaman juga.\nPilihan makanan dan minumannya banyak tinggal pilih aja, ada paketan juga.\n\nTapi sayangnya makanan sama minumannya lama datengnyaa..setengah jam ada kali.. Udah keburu laperrr.. Yang berkunjung padahal g terlalu banyak, keliatanny sih ngerjain yang gampang2 dulu gitu..",
                            "time": "3 tahun lalu"
                        }
                    ],
                    "phone": "(0274) 588406",
                    "website": "https://baleayu.com/brands/bale-bebakaran/",
                    "latitude": -7.7635003,
                    "longitude": 110.3786902
                },
                {
                    "id": "ChIJWfro56VZei4RSzqKqZaGAdQ",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0l5zchGQ-m_IYCfJCIBwFOS3XI8yYDJFCYe6Zt0OR59PUm2tA4YzRZT32HzVWzqbDGxVTH9c5K8pD4rkawgR4BxYuM6VXZjj2EMJt18maP-XkFi1d538FrVq2AD1pCPjMfTgnveYeSNsbefy128waYrnYDgR8LO-TlS43xvkYzNSSo9&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "Parsley Bakery & Resto Jakal",
                    "category": "restaurant",
                    "address": "3, Kaliurang St KM.5, Manggung, Caturtunggal, Depok, Sleman Regency, Special Region of Yogyakarta 55281, Indonesia",
                    "rating": 4.6,
                    "totalReview": 5578,
                    "about": "Tempat makan dan toko roti yang menyediakan aneka menu barat dan Asia yang segar serta tersedia kopi.",
                    "review": [
                        {
                            "id": "0",
                            "author": "Agatha Elma",
                            "rating": 5,
                            "text": "Udah sejak lama banget sebenernya resto ini ada dan dipuji2 banyak orang. Tapi kalo aku baru pertama kalinya ke sini.\nFor their bakery, I agree that they're really good at it! 🫰🏻\n\nAnd for the resto, they serve delicious dishes 👍🏻 Ada menu western dan Indonesian. Mulai dari appetizer, main dish, sampe dessert pun ada.\n\nBuatku, rasanya oke. Enak.\n\nKalo aku mengamati yg dateng ke sini tuh banyak keluarga, kolega, sosialita. Aku ke sini ketemu temen2ku. Duduk di yg sofa gede banget dan nyaman. The interior, furniture, toilet, they're classic.\n\nKami pesen lumayan banyak menu dan they're all OK. Nggak ada yg menarik hatiku banget, tp semua aku makan soalnya enak 👌🏻",
                            "time": "11 bulan lalu"
                        },
                        {
                            "id": "1",
                            "author": "Yustina Carolina F. Salsinha",
                            "rating": 5,
                            "text": "Tiap ada event ulang tahun, selalu rekomendasiin buat nyari cake di sini. Gag kaleng-kaleng rasanya enak banget, gag cuman keren pilihannya. Pelayanan dan service memuaskan. Di sini juga bisa pesan via WhatsApp. Buat yang suka jajanan, banyak juga nih, on the spot. Enak buat nongkrong sama temen nyari pastry.",
                            "time": "dalam minggu terakhir"
                        },
                        {
                            "id": "2",
                            "author": "NurulY SF",
                            "rating": 4,
                            "text": "Saya selalu rutin pesan kue ulang tahun utk anak2 saya di sini. Lapis surabayanya juga enak, favorit anak saya banget. Kue2nya hampir semuanya enak ko, saya suka bgt kue talamnya. Kalau makanan di restonya saya seringnya makan yg di jalan kaliurang, pernah cb sekali yg chinese food di jalan solo tp ga tau masih ato ga. Mnrt saya rasanya cukup decent ko dg harga segini dan tempat yang nyaman.",
                            "time": "sebulan yang lalu"
                        },
                        {
                            "id": "3",
                            "author": "Adhitya Eka Pria W",
                            "rating": 5,
                            "text": "Beberapa kali mampir ketempat ini, baru kali ini sempat ambil foto dan bikin review nya.\n\nYang jelas sudah serba unggul di banyak hal.\nTempat strategis.\nParkiran super luas.\nMakanan Enak, apa lagi kue2nya.\nAtmosfir juga cukup oke, bisa pilih smoke dan non smoking.\nSalah satu andalan kalau mau meet up yang santai sampai lumayan serius.\n\nRecommended!!",
                            "time": "2 bulan lalu"
                        },
                        {
                            "id": "4",
                            "author": "Killa Biezt",
                            "rating": 4,
                            "text": "Suasana dan tempat nyaman. Tempat duduk nyaman terutama yg sofa empuk. Ada ruangan dalam dan luar. Ada menu western dan menu indonesia. Pastanya cukup enak. Burgernya biasa aja. Rasa kue tartnya biasa aja. Pelayanan baik. Pembayaran mudah.",
                            "time": "7 bulan lalu"
                        }
                    ],
                    "phone": "(0274) 520043",
                    "website": "http://www.parsley.co.id/",
                    "latitude": -7.759665300000001,
                    "longitude": 110.3814087
                },
                {
                    "id": "ChIJI5-HrqtZei4RUlJ0UEKxeV4",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0kH-5uo6GtCSdm_dcGroj_8WKypq3TWRppGJEHRirZ94hS19mzUmsUpRMua9Y5sdl88_GxbDweo0i0mbeaDIFqNFcqPiN6YHO-BHL2G1VFY_KBN4tEOElopDYFYcg1sUqWG2YwIYvoQyutlfHBQHof32DQ-yutm7ZZRMmicqkD-Xh2W&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "Alfamart",
                    "category": "convenience_store",
                    "address": "Jl. Kaliurang Km 5.6 Rt 01 Rw 01, Dk Manggung Kel, Manggung, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281, Indonesia",
                    "rating": 4.5,
                    "totalReview": 22,
                    "about": null,
                    "review": [
                        {
                            "id": "0",
                            "author": "alwi rohman",
                            "rating": 5,
                            "text": "Kak tlong di update donk jam bukanya, aq ksini jauh ternyata belum buka, mhon direspon ya.\nAgar kustomer yg lainnya juga tidak kecelik.makasih",
                            "time": "seminggu yang lalu"
                        },
                        {
                            "id": "1",
                            "author": "Viirman Situmorang",
                            "rating": 5,
                            "text": "Parkir luass dan nyamaan ada pentol dan kebab nyaa ✌️",
                            "time": "7 bulan lalu"
                        },
                        {
                            "id": "2",
                            "author": "Asa Akbar Nuansa Mukti",
                            "rating": 5,
                            "text": "Tempatnya nyaman , next mungkin di kasih stand Bean biar kalau mau minum di pagi hari gak jauh jauh carinya",
                            "time": "6 bulan lalu"
                        },
                        {
                            "id": "3",
                            "author": "Haula Taqya",
                            "rating": 4,
                            "text": "Lama bgt antri bayar pake QRIS, terus nungguin print struk belanjaan",
                            "time": "5 bulan lalu"
                        },
                        {
                            "id": "4",
                            "author": "Ananda Adittya",
                            "rating": 1,
                            "text": "Tulisan di map buka smp sini ternyata masih tutup",
                            "time": "2 bulan lalu"
                        }
                    ],
                    "phone": "1 500 959",
                    "website": "https://alfamartku.com/",
                    "latitude": -7.756957000000001,
                    "longitude": 110.3819896
                },
                {
                    "id": "ChIJhbz9P61Zei4REA0BJmJXiD0",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0mlZXUVdkkLnQ6IW3_S08q-8Hi1DniHY_QCm5uH_NSKN8_5z1xKG35izcGjP733c3n9SF48GMnbiUQAs_dabi4tAfZ3WPZSGcp6ErNISOKr5ToYhs1txahz18pIv_pJkZY-3T7TuxopptFeUdYRHRrusCaN4epMxQUPglvJ6ZZ4P5k9&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "Gudeg Yu Djum Pusat",
                    "category": "restaurant",
                    "address": "Jl. Kaliurang Km. 4,5 CT III/22 Gang Cokrowolo Karangasem, 69MH+HWR, Mbarek, Jl. Agro, Kocoran, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281, Indonesia",
                    "rating": 4.6,
                    "totalReview": 5559,
                    "about": "Restoran Indonesia yang menyajikan hidangan gudeg khas Yogyakarta, tersedia paket nasi, besek, kendil.",
                    "review": [
                        {
                            "id": "0",
                            "author": "Heriyanto",
                            "rating": 4,
                            "text": "Legendaris. Ini memang Gudeg yang paling jelas dari segi rasa. Paten enaknya. Dari dulu hingga sekarang, rasa Gudegnya memang enak.\n\nAsyiknya makan di Gudeg Yu Djum Pusat ini karena ini dapurnya. Aroma Gudeg menguar memenuhi ruangan. Ada perapian di bagian belakang bangunan.\n\nNamun, ya hanya itu pengalaman yang ditawarkan. Yaitu Gudeg yang maknyus.\n\nKalau ngomong kenyamanan, makan di pusat justru kurang nyaman. Cabang\" Yu Djum malah menawarkan pengalaman makan yang lebih baik karena luas tempatnya, misal seperti cabangnya di Wijilan.\n\nKarena di Pusat memang sepertinya fokus melayani pesanan dan produksi. Jadi tempat duduk terbatas dan sempit. Banyak orang wira wiri. Ada banyak dos. Pengemasan dan lain-lain.\n\nPelayanan juga sangat \"tradisional\", pelanggan pesan, makan, lalu bayar di kasir. Dan sepertinya pembayaran masih tunai. Tanpa ada sistem pembayaran modern dengan e-payment. Ini kadang bikin turis kecewa karena harus membayar tunai. Jadi harus sedia uang tunai saat makan di sini.\n\nKasir kurang simpatik (baca: jutek). Staf lumayan ramah tetapi jangan berharap layanannya professional seperti di rumah makan besar. Ini masih PR besar, sayang kalau tidak dibenahi.\n\nNevertheless, Gudeg-nya enak dengan aroma Gudeg yang memenuhi ruangan. Dengan teh yang panas, legi, kentel. Itu saja yang penting.",
                            "time": "9 bulan lalu"
                        },
                        {
                            "id": "1",
                            "author": "Pava Rotto",
                            "rating": 5,
                            "text": "Lokasinya bukan di pinggir jalan Besar, tpi masuk gang ke rumah penduduk.\nBangunan rumahnya nampak sederhana, bisa pesan untuk dimakan di lokasi atau dibawa pulang.\nHalaman parkir depan rumahnya tidak terlalu luas, tpi akan dibantu oleh Mas Parkir.\nLayanan cepat dan ramah.\nKlo rasa, gak usah ditanya lagi, sudah terkenal seantero negeri.",
                            "time": "5 bulan lalu"
                        },
                        {
                            "id": "2",
                            "author": "Tien Ummu Haidar",
                            "rating": 4,
                            "text": "Gudeg pusat terletak di gang, walapun sempit tersedia area parkir.\nRasa masakan seperti gudeg lainnya, sangat manis. Porsinya sedikit sampai kita order nasi 1 porsi lagi karena belum kenyang.\nTempat makan ramai karena peak season.\nHarga ? mahal kalo belinya sering....tapi kl sekedar wisata bolehlah di coba",
                            "time": "5 bulan lalu"
                        },
                        {
                            "id": "3",
                            "author": "hadaina rofiqoh",
                            "rating": 5,
                            "text": "Makan gudeg yu djum yg bener-bener di pusatnya langsung, suasana makan di t4 eyang, suasana jadul, tempatnya masuk gang. Tipe gudeg kering dg citarasa manis legit, tapi kreceknya pedas mantap. Beli 1 porsi ngenyangin banget, nasi pulen dan banyak lagi. Pas dateng kesini sore² mau maghrib, ga terlalu ramai dan meja kursi ga terlalu banyak. Parkir mobil tidak terlalu luas, tapi di tata sama tukang parkirnya biar miat banyak.",
                            "time": "3 bulan lalu"
                        },
                        {
                            "id": "4",
                            "author": "Lovia Tabita",
                            "rating": 4,
                            "text": "Cobain makan Gudeg ini di pusatnya\nLokasi di dalem gang\nTapi parkirnya luas\nRasanya masih sama dan khas\nSayang sekali tidak tersedia pembayaran non-tunai\nJarak ke ATM pun cukup jauh\nLokasi bersih, pelayanan cukup baik",
                            "time": "4 bulan lalu"
                        }
                    ],
                    "phone": "(0274) 515968",
                    "website": "http://gudegyudjumpusat.com/",
                    "latitude": -7.7653644,
                    "longitude": 110.3803136
                },
                {
                    "id": "ChIJrQPcgaxZei4RwRXKMsT8dNo",
                    "photo": "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=AZose0luPGfFxHzySPUiNuCBBLCb1KXfwR79ImRiLYiaR6ziwFxdjl-OSQ7bmCMxmfLQyNe880yu7HUFUZLg5iI0g9cXladRuBnTbi1zE-Go75b9nu--bBPSQJw12C_QvMkHHKqfbEh30WC916jDyKdJZNyDk0UG_nQH32gZmKBqAMo0YM_p&sensor=false&key=AIzaSyClh1AGWGGuXQM38uvxoxwjvdRNNP3h0mo",
                    "name": "NOX Coffee Boutique",
                    "category": "cafe",
                    "address": "2nd floor, Natasha Skin Clinic Center, KM.5 No, Jl. Kaliurang KM.5 No.53, Manggung, Caturtunggal, Kec. Depok, Kabupaten Sleman, Daerah Istimewa Yogyakarta 55281, Indonesia",
                    "rating": 4.5,
                    "totalReview": 658,
                    "about": null,
                    "review": [
                        {
                            "id": "0",
                            "author": "Resty (ChkinN)",
                            "rating": 5,
                            "text": "Tempatnya satu gedung dengan natasha skin clinic center. Kalau mau skin care dan sedang menunggu antrian, ke cafe ini jadi salah satu solusinya. Enaknya kalau skin care di natasha kaliurang, dapat free drink di cafe ini. Oh ya, suasana tempatnya juga sangat modern walaupun vibenya cukup retro. Ada outdoor dan indoor room. Indoor menyediakan AC dan setiap tempat duduk disediakan stop kontak untuk charger.  Senang disediakan tempat duduk khusus sendiri sih, lebih nyaman kalau misalnya datang sendirian. Rasa makanannya termasuk enak. Tapi, bukan wah ya. Penyajian makanannya juga sangat baik. Walaupun harganya tergolong moderat ke atas ya. Kebanyakan western food, tapi asian dan indonesian food juga ada. Pelayanannya cukup baik. Plus, disediakan wifi gratis. Kalau mau pesan dan bayar menu bisa langsung dibarcode yang disediakan tempat meja, jadi nggak usah lagi repot-repot panggil waitress/waiternya dan pergi ke kasir. Di sini kamu juga bisa order online gofood/grabfood. Kalau mau belajar, nongki, dan diskusi ke sini ok juga.",
                            "time": "5 bulan lalu"
                        },
                        {
                            "id": "1",
                            "author": "Debby Christiana",
                            "rating": 5,
                            "text": "Letak cafenya di gedung yang sama dengan Natasha beauty clinic di jalan Kaliurang.\nSuka banget dengan ambience nya, walaupun tdk telalu besar, tapi nyaman baik outdoor maupun indoornya.\nTersedia banyak colokan. Jadi enak banget untuk mengerjakan tugas.\nWIFInya koneksinya cepat.\nMakanan dan minuman bervariasi dengan harga standard.\nStaff ramah, pelayanan baik.",
                            "time": "6 bulan lalu"
                        },
                        {
                            "id": "2",
                            "author": "Vicky Koapaha",
                            "rating": 3,
                            "text": "Untuk minumannya so so, entah emg bgni rasanya atau yg buat ya.\nUntuk service dr barista kurang si.\nPelayanannya kurang ramah.\nI'm sorry to say this :(\nOklah klo hrga yg expensive, tp ku kira bakal dpt yg sesuai entah dr rasa atau servicenya....\nSedih :(\nBar yg ditinggal wkt kami dtg, cara menghadapi cust, kebersihan meja, keramahannya hiks :(\nFirst time ngasih bintang sgni k coffee shop:( maaf",
                            "time": "setahun yang lalu"
                        },
                        {
                            "id": "3",
                            "author": "Maandinaa",
                            "rating": 4,
                            "text": "Tempatnya berada di lantai 2, jadi satu tempat dengan Natasha Skincare\nPelayanan ramah, cocok sekaliiii untuk nugas atau nongkrong\nTempatnya nyaman, bersih, coffeenya enak🤩\nSurprisingly, Green tea latte nya ENAK BANGETTT!🥰 Artnya sebagus itu…. Unicorn🥺💓",
                            "time": "setahun yang lalu"
                        },
                        {
                            "id": "4",
                            "author": "Fatikhah Dwitasara",
                            "rating": 5,
                            "text": "Cafe yang lokasinya strategis bgt di Jalan Kaliurang satu tempat dg Natasha. Parkirnya di masuk basement dan cukup luas. Makanannya enak dan harganya cukup affordable. Tersedia mushola di gedungnya. Buka hingga jam 12 malam jdi cocok buat nugas sama temen maupun nongkrong. Disini bisa liat hiruk pikuk Jalan Kaliurang dari atas 😂Sangat recomended bgt",
                            "time": "10 bulan lalu"
                        }
                    ],
                    "phone": "(0274) 565330",
                    "website": "https://www.instagram.com/noxcoffee/",
                    "latitude": -7.757906599999998,
                    "longitude": 110.3816834
                }
            ]
        }
    """.trimIndent()

    val placesData = JSONObject(placesDataJSON)
    val place = placesData.getJSONArray("data")
}