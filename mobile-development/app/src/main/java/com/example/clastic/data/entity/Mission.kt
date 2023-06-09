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
            "Welcome to this mission! In this mission, you have to stop using plastic bags because of its pollution, harmful, high carbon, and other negative impacts! \n" +
                    "Do these things below : \n" +
                    "1. Use reusable bags when buying foods, items, etc.\n" +
                    "2. Recycle your plastic bags collection if any at home.\n" +
                    "\n" +
                    "Let’s go complete this mission !\n",
            R.drawable.mission1_cover,
            "HDPEM",
            "200pts",
            listOf(
                Impact("Baking frozen pizzas", "11",R.drawable.mission1_impact1),
                Impact("Charging AA batteries", "1.947",R.drawable.mission1_impact2),
                Impact("Watching hours of TV", "62",R.drawable.mission1_impact3),
                Impact("Keeping your refrigerator cold for days", "4",R.drawable.mission1_impact4)
            )),
        Mission(
            "Say no to plastic utensils and yes to reusable ones!",
            "Welcome to this mission! In this mission, you have to stop using plastic utensils because of its pollution, harmful, high carbon, and other negative impacts! \n" +
                    "Do these things below : \n" +
                    "1. Use reusable utensils when buying foods, items, etc.\n" +
                    "2. Bring your own reusable utensils.\n" +
                    "\n" +
                    "Let’s go complete this mission !\n",
            R.drawable.mission2_cover,
            "PP",
            "200pts",
            listOf(
                Impact("Baking frozen pizzas", "4", R.drawable.mission2_impact1),
                Impact("Watching hours of TV", "24",R.drawable.mission2_impact2),
                Impact("Charging a cellphone times", "861",R.drawable.mission2_impact3),
                Impact("charging a laptop times", "31",R.drawable.mission2_impact4)
            )),
        Mission(
            "Get your bottle or keep your cup topless!",
            "Welcome to this mission! In this mission, you have to stop using plastic bags because of its pollution, harmful, high carbon, and other negative impacts! \n" +
                    "Do these things below : \n" +
                    "1. Use reusable mugs/cups when buying coffee/tea.\n" +
                    "2. Bring your own bottle for drinking\n" +
                    "3. Not buying bottles of water from mart\n" +
                    "\n" +
                    "\n" +
                    "Let’s go complete this mission !\n",
            R.drawable.mission3_cover,
            "PET",
            "300pts",
            listOf(
                Impact("Frozen pizzas baked", "17", R.drawable.mission3_impact1),
                Impact("Teacups filled", "2.207",R.drawable.mission3_impact2),
                Impact("Bathtubs filled", "3",R.drawable.mission3_impact3),
                Impact("Five minutes showers taked", "11",R.drawable.mission3_impact4)
            )),
    )
}
