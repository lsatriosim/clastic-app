package com.example.clastic.data.entity

import com.example.clastic.R

data class PlasticKnowledge(
    val tag: String,
    val fullName: String,
    val imageCover: Int,
    val content: String,
    val productList: List<Product>,
)

object ProductData {

    val plasticTypes = listOf(
        PlasticKnowledge(
            tag = "PET",
            fullName = "Polyethylene Terephthalate (PET)",
            imageCover = R.drawable.cover_pet,
            content = "Plastik berjenis PET (Polyethylene terephthalate) atau dalam bahasa Indonesia disebut polietilena tereftalat adalah polimer termoplastik serbaguna yang termasuk dalam kelompok polimer poliester. Resin poliester sendiri dikenal dengan beberapa sifat unggulnya dalam segi mekanis, termal, dan juga resisten terhadap zat kimia. Plastik berjenis PET ditandai dengan simbol daur ulang dengan angka 1 pada produk. \n\n" +
                    "Plastik PET merupakan termoplastik yang paling banyak didaur ulang jika dibandingkan dengan plastik lainnya. Plastik PET daur ulang bisa dibentuk menjadi fiber, kain, lembaran plastik, atau komponen kendaraan. Meskipun PET merupakan salah satu bahan plastik yang banyak didaur ulang, plastik PET mengandung antimony trioxide yang dianggap karsinogen (dapat memicu kanker).\n\n" +
                    "Secara struktur kimiawi, polietilena tereftalat (PET) memiliki kemiripan dengan plastik polibutilena tereftalat (PBT). PET pada umumnya memiliki karakter yang sangat fleksibel dan tembus pandang. Bergantung pada proses pembuatannya, plastik PET bisa dibuat menjadi produk dengan sifat kaku maupun semi-kaku.",
            productList = listOf(
                Product(
                    productName = "Botol Minum",
                    productImage = R.drawable.botol_minum
                ),
                Product(
                    productName = "Botol Saus",
                    productImage = R.drawable.botol_saus
                ),
                Product(
                    productName = "Botol Minyak",
                    productImage = R.drawable.botol_minyak
                ),
                Product(
                    productName = "Tutup Botol",
                    productImage = R.drawable.tutup_botol
                ),
                Product(
                    productName = "Toples",
                    productImage = R.drawable.toples
                ),
                Product(
                    productName = "Sisir",
                    productImage = R.drawable.sisir
                )
            )
        ),
        PlasticKnowledge(
            tag = "HDPE",
            fullName = "High Density Poly Ethylene (HDPE)",
            imageCover = R.drawable.cover_hdpe,
            content = "High Density Poly Ethylene (HDPE) adalah polimer termoplastik yang terbuat dari minyak bumi. Sebagai salah satu bahan plastik paling serbaguna, plastik HDPE digunakan dalam berbagai aplikasi, termasuk botol plastik, kendi susu, botol sampo, botol pemutih, talenan, dan pipa. Jenis plastik ini memiliki kekuatan tarik yang luar biasa, rasio kekuatan terhadap kerapatan yang besar, dan memiliki ketahanan benturan serta titik leleh yang tinggi.\n\n" +
                    "Selain itu, plastik berjenis HDPE juga mudah didaur ulang. HDPE juga relatif lebih stabil dari PET. HDPE dianggap sebagai jenis plastik yang cukup aman untuk digunakan bersama makanan dan minuman, meskipun beberapa studi menunjukkan bahwa jika terekspos oleh sinar UV dalam waktu yang lama, HDPE dapat menghasilkan zat kimia serupa estrogen (salah satu hormon pada manusia) yang bisa merusak sistem hormon manusia.\n\n" +
                    "HDPE terbuat dari proses pemanasan minyak bumi. Sifatnya keras, tahan terhadap suhu tinggi, dan dapat dibentuk menjadi berbagai benda tanpa kehilangan kekuatannya. Lapisan HDPE cenderung terlihat buram setelah diproses, dan dapat didaur ulang. Ketangguhan HDPE plastik datang dari susunan molekulnya. Percabangan molekulnya cukup jarang dan berjauhan, menciptakan kekuatan tensil yang tangguh. Hal ini memberi plastik HDPE kelenturan serta daya tahan tinggi.",
            productList = listOf(
                Product(
                    productName = "Galon Air Minum",
                    productImage = R.drawable.galon_air
                ),
                Product(
                    productName = "Botol Susu",
                    productImage = R.drawable.botol_susu
                ),
                Product(
                    productName = "Botol Sabun",
                    productImage = R.drawable.botol_sabun
                ),
                Product(
                    productName = "Botol Deterjen",
                    productImage = R.drawable.botol_deterjen
                ),
                Product(
                    productName = "Botol Shampoo",
                    productImage = R.drawable.botol_shampo
                ),
                Product(
                    productName = "Kantung Plastik",
                    productImage = R.drawable.kantung_plastik
                ),
                Product(
                    productName = "Plastik Sampah",
                    productImage = R.drawable.plastik_sampah
                )
            )
        ),
        PlasticKnowledge(
            tag = "PP",
            fullName = "Polypropylene (PP)",
            imageCover = R.drawable.cover_pp,
            content = "Polypropylene (PP) adalah jenis plastik yang paling banyak diproduksi dan salah satu jenis plastik yang penggunaannya juga paling banyak. Bahan Polypropylene merupakan material yang umum digunakan oleh masyarakat untuk berbagai kebutuhan. Polypropylene terbentuk melalui proses polimerisasi yang ditemukan oleh Robert Banks dan Paul Hogan yang merupakan dua ilmuwan. Material Polypropylene hanya perlu memakan waktu sekitar 6 tahun sampai diproduksi secara global.\n" +
                    "\n" +
                    "Polypropylene (PP) adalah termoplastik yang tangguh, kaku, dan kristal. Plastik jenis ini terbuat dari monomer propena (atau propilena). Resin hidrokarbon linier ini adalah polimer paling ringan di antara semua plastik komoditas. Polypropylene (PP) adalah jenis poliolefin yang sedikit lebih keras dari polietilen. Jenis ini adalah plastik komoditas dengan kepadatan rendah dan ketahanan panas yang tinggi. \n" +
                    "\n" +
                    "Beberapa karakteristik lainnya dari polypropylene atau plastik PP adalah elastisitas dan kekuatannya yang tinggi, kemampuannya mempertahankan bentuk, serta memiliki ketahanan terhadap aliran listrik. Polypropylene dianggap sebagai plastik yang cukup aman untuk digunakan bersama dengan makanan dan minuman. Namun meskipun memiliki kualitas yang cukup baik, PP tidak mudah didaur ulang dan bisa menimbulkan asma serta gangguan hormon pada manusia. ",
            productList = listOf(
                Product(
                    productName = "Kotak Makanan",
                    productImage = R.drawable.kotak_makanan
                ),
                Product(
                    productName = "Kotak Yogurt",
                    productImage = R.drawable.kotak_yogurt
                ),
                Product(
                    productName = "Sedotan Plastik",
                    productImage = R.drawable.sedotan_plastik
                ),
                Product(
                    productName = "Gantungan Baju",
                    productImage = R.drawable.gantungan_baju
                ),
                Product(
                    productName = "Utensils",
                    productImage = R.drawable.utensils
                ),
            )
        ),
        PlasticKnowledge(
            tag = "PVC",
            fullName = "Polyvinyl Chloride (PVC atau Vinyl)",
            imageCover = R.drawable.cover_pvc,
            content = "Polyvinyl Chloride (PVC atau Vinyl) adalah polimer termoplastik yang ekonomis dan serbaguna. Jenis ini banyak digunakan dalam industri bangunan dan konstruksi untuk lis pintu dan jendela. \n\n" +
                    "PVC merupakan termoplastik terbesar ketiga di dunia berdasarkan volumenya setelah polietilen dan polipropilena. PVC berbahan padat berwarna putih dan rapuh yang tersedia dalam bentuk bubuk atau butiran. PVC sekarang menggantikan bahan bangunan tradisional di beberapa aplikasi. Bahan-bahan ini termasuk kayu, logam, beton, karet, keramik, dll dalam beberapa aplikasi. Hal tersebut dikarenakan PVC bersifat ringan, tahan lama, biaya rendah, dan kemampuan proses yang mudah.\n" +
                    "\n" +
                    "Barang-barang plastik yang terbuat dari plastik polyvinyl chloride ini sering juga disebut dengan “plastik beracun”, karena mengandung berbagai macam bahan kimia beracun yang dapat larut dan berbahaya bagi kesehatan. Jenis plastik ini sangat sulit untuk didaur ulang, sehingga perlu dihindari menggunakan jenis plastik ini untuk kemasan makanan atau minuman.",
            productList = listOf(
                Product(
                    productName = "Pipa Air",
                    productImage = R.drawable.pipa_air
                ),
                Product(
                    productName = "Ubin",
                    productImage = R.drawable.ubin
                ),
                Product(
                    productName = "Kabel Listrik, Wrapping",
                    productImage = R.drawable.kabel_listrik
                ),
                Product(
                    productName = "Mainan Anak/Hewan Peliharaan",
                    productImage = R.drawable.mainan_anak
                ),
            )
        ),
        PlasticKnowledge(
            tag = "LDPE",
            fullName = "Low Density Polyethylene (LDPE)",
            imageCover = R.drawable.cover_ldpe,
            content = "LDPE (Low Density Polyethylene) adalah termoplastik, yaitu jenis plastik yang bisa diolah lewat pemanasan dan pendinginan. Plastik ini terbuat dari minyak bumi yang telah diproduksi sejak tahun 1933. Karakteristiknya relatif tipis, lentur, jernih, dan ringan sehingga mudah dijadikan berbagai material atau produk.\n" +
                    "\nLDPE termasuk jenis polimer yang hadir paling awal dalam dunia industri. Saat ini, LDPE plastik menghadapi persaingan ketat dengan beragam jenis polimer baru, termasuk HDPE. Akan tetapi, keunggulan LDPE membuatnya tetap populer di kalangan pemilik usaha, industri, dan rumah tangga.\n\n" +
                    "Jenis plastik ini terbuat dari bahan low density polyethylene yang bersifat elastis, memiliki daya tahan yang lama dan dapat digunakan untuk berulang kali.  Namun, lebih baik jika kalian menggunakannya sekali dan mendaur ulangnya agar tidak tertimbun di tanah yang butuh ratusan tahun untuk mengurainya.",
            productList = listOf(
                Product(
                    productName = "Kantung Roti",
                    productImage = R.drawable.kantung_roti
                ),
                Product(
                    productName = "Pembungkus Plastik (Wrapper)",
                    productImage = R.drawable.wrapper
                ),
            )
        ),
        PlasticKnowledge(
            tag = "PS",
            fullName = "Polystyrene (PS)",
            imageCover = R.drawable.cover_ps,
            content = "PP (polystyrene) adalah plastik yang terbuat dari polystyrene dan biasanya dijual dengan harga yang cukup murah dan ringan. Plastik ini dapat mengeluarkan styrene yang merupakan zat karsinogen yang dapat menyebabkan kanker, terutama jika saat menggunakan untuk makanan/minuman yang panas. \n\n" +
                    "Hal tersebut dikarenakan Polystyrene merupakan polimer aromatik yang dapat mengeluarkan bahan styrene ke dalam makanan ketika makanan tersebut bersentuhan. Bahan ini harus dihindari, karena selain berbahaya untuk kesehatan otak, mengganggu hormon estrogen pada wanita yang berakibat pada masalah reproduksi, pertumbuhan dansistem syaraf, juga bahan ini sulit didaur ulang. Bila didaur ulang, bahan ini memerlukan proses yang sangat panjang dan lama. Jika bahan PP dibakar, bahan ini akan mengeluarkan api berwarna kuning-jingga, dan meninggalkan jelaga. Jenis plastik ini memiliki titik leleh pada 95ºC.",
            productList = listOf(
                Product(
                    productName = "Tempat Makan",
                    productImage = R.drawable.tempat_makan
                ),
                Product(
                    productName = "Kemasan Foam",
                    productImage = R.drawable.kemasan_foam
                ),
                Product(
                    productName = "Helm Sepeda",
                    productImage = R.drawable.helm_sepeda
                ),
            )
        ),
        PlasticKnowledge(
            tag = "Other",
            fullName = "Other",
            imageCover = R.drawable.cover_other,
            content = "Jenis plastik ini merupakan jenis plastik yang tidak termasuk kedalam klasifikasi PETE atau PET, HDPE atau PE-HD, PVC atau V, LDPE atau PE-LD, PP, dan PS. Contohnya bioplastic atau Polycarbonate (PC) .Polycarbonate (PC) merupakan tipe plastik yang paling banyak dikategorikan sebagai tipe plastik nomor 7, yang mana tidak banyak lagi digunakan pada tahun-tahun terakhir ini karena diketahui memiliki kandungan bisphenol A (BPA). PC juga dikenal dengan nama lainnya, Lexan, Makrolon, Makroclear. BPA yang terkandung di dalam PC terbukti bisa menimbulkan beberapa masalah kesehatan termasuk kerusakan kromosom di dalam rahim wanita, penurunan jumlah sperma pada pria, pubertas dini, beberapa perubahan perilaku, perubahan fungsi imunitas, perubahan kelamin pada katak, kerusakan otak dan saraf, kerusakan sistem kardiovaskular, diabetes type III, kegemukan, kegagalan kemoterapi, kanker payudara, kanker prostat, kemandulan, serta kelainan metabolik.",
            productList = listOf(
                Product(
                    productName = "Compat Disk (CD)",
                    productImage = R.drawable.cd
                ),
                Product(
                    productName = "Botol Bayi",
                    productImage = R.drawable.botol_susu_other
                ),
            )
        )
    )
}
