package com.example.clastic.data.entity

import com.example.clastic.R

data class Mission(
    val title: String,
    val description: String,
    val image: Int,
    val tag: String,
    val reward: String,
    val listImpact: List<Impact>
){
    constructor(): this("","",0,"","", emptyList())
}

object MissionData{
    val dummyMission = listOf(
        Mission(
            "Plastic bags diet!",
            "Selamat datang di misi ini! Dalam misi ini, kamu harus bisa berhenti untuk menggunakan plastik yang menyebabkan polusi, tingkat karbon yang tinggi, zat racun, dan banyak dampak negatif lainnya!\n" +
                    "Lakukan hal di bawah ini:\n" +
                    "1. Pakailah tas yang dapat digunakan kembali untuk berbelanja makanan, barang, dal hal lainnya.\n" +
                    "2. Daur ulang kantong plastik yang ada di rumahmu.\n" +
                    "\n" +
                    "Ayo selesaikan misi ini!\n",
            R.drawable.mission1_cover,
            "HDPEM",
            "200pts",
            listOf(
                Impact("Kali memanggang pizza cepat saji", "11",R.drawable.mission1_impact1),
                Impact("Kali mengisi daya baterai AA", "1.947",R.drawable.mission3_impact2),
                Impact("Jam menonton TV", "62",R.drawable.mission3_impact3),
                Impact("Hari menyalakan kulkas", "4",R.drawable.mission3_impact4)
            )),
        Mission(
            "Say no to plastic utensils and yes to reusable ones!",
            "Selamat datang di misi ini! Dalam misi ini, kamu harus bisa berhenti untuk menggunakan plastik yang menyebabkan polusi, tingkat karbon yang tinggi, zat racun, dan banyak dampak negatif lainnya!\n" +
                    "Lakukan hal di bawah ini:\n" +
                    "1. Pakailah alat makan yang dapat digunakan kembali untuk berbelanja makanan, barang, dal hal lainnya.\n" +
                    "2. Bawalah alat makan yang dapat digunakan kembali di sekolah ataupun tempat kerja.\n" +
                    "\n" +
                    "Ayo selesaikan misi ini!\n",
            R.drawable.mission2_cover,
            "PP",
            "200pts",
            listOf(
                Impact("Kali memanggang pizza cepat saji", "4", R.drawable.mission2_impact1),
                Impact("Jam menonton TV", "24",R.drawable.mission2_impact2),
                Impact("Kali mengisi daya handphone", "861",R.drawable.mission2_impact3),
                Impact("Kali mengisi daya laptop", "31",R.drawable.mission2_impact4)
            )),
        Mission(
            "Get your bottle or keep your cup topless!",
            "Selamat datang di misi ini! Dalam misi ini, kamu harus bisa berhenti untuk menggunakan plastik yang menyebabkan polusi, tingkat karbon yang tinggi, zat racun, dan banyak dampak negatif lainnya!\n" +
                    "Lakukan hal di bawah ini:\n" +
                    "1. Gunakan gelas yang dapat digunakan kembali ketika membeli teh/kopi\n" +
                    "2. Bawalah botol sendiri di sekolah/tempat kerja\n" +
                    "3. Tidak membeli botol plastik di tempat belanja\n" +
                    "\n" +
                    "\n" +
                    "Ayo selesaikan misi ini!\n",
            R.drawable.mission3_cover,
            "PET",
            "300pts",
            listOf(
                Impact("Kali memanggang pizza cepat saji", "17", R.drawable.mission3_impact1),
                Impact("Cangkir teh diisi", "2.207",R.drawable.mission1_impact2),
                Impact("Bathtub diisi", "3",R.drawable.mission1_impact3),
                Impact("Kali shower selama lima menit", "11",R.drawable.mission1_impact4)
            )),
    )
}
