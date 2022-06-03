package com.rentme.rentme.utils

import com.rentme.rentme.R

object SelectColor {

    fun nameToCode(name: String) : Int{
        return when(name){
            "BLACK" -> R.color.car_black
            "METEOR_GREY" -> R.color.car_meteor_grey
            "BRIGHT_WHITE" -> R.color.car_bright_white
            "CANDY_WHITE" -> R.color.car_candy_white
            "BRILLIANT_SILVER" -> R.color.car_brilliant_silver
            "ENERGY_BLUE" -> R.color.car_energy_blue
            "RACE_BLUE" -> R.color.car_race_blue
            "VELVET_RED" -> R.color.car_velvet_red
            "CARRIDO_RED" -> R.color.car_corrida_red
            "YELLOW" -> R.color.car_yellow
            else -> R.color.car_orange
        }
    }

    fun codeToName(code: Int) : String{
        return when(code){
            R.color.car_black -> "BLACK"
            R.color.car_meteor_grey -> "METEOR_GREY"
            R.color.car_bright_white -> "BRIGHT_WHITE"
            R.color.car_candy_white -> "CANDY_WHITE"
            R.color.car_brilliant_silver -> "BRILLIANT_SILVER"
            R.color.car_energy_blue -> "ENERGY_BLUE"
            R.color.car_race_blue -> "RACE_BLUE"
            R.color.car_velvet_red -> "VELVET_RED"
            R.color.car_corrida_red -> "CARRIDO_RED"
            R.color.car_yellow -> "YELLOW"
            else -> "ORANGE"
        }
    }

}