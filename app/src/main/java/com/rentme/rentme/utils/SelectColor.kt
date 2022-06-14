package com.rentme.rentme.utils

import com.rentme.rentme.R

object SelectColor {

    fun nameToCode(name: Colors) : Int{
        return when(name){
            Colors.BLACK -> R.color.car_black
            Colors.METEOR_GREY -> R.color.car_meteor_grey
            Colors.BRIGHT_WHITE -> R.color.car_bright_white
            Colors.CANDY_WHITE -> R.color.car_candy_white
            Colors.BRILLIANT_SILVER -> R.color.car_brilliant_silver
            Colors.ENERGY_BLUE -> R.color.car_energy_blue
            Colors.RACE_BLUE -> R.color.car_race_blue
            Colors.VELVET_RED -> R.color.car_velvet_red
            Colors.CORRIDA_RED -> R.color.car_corrida_red
            Colors.YELLOW -> R.color.car_yellow
            else -> R.color.car_orange
        }
    }

    fun codeToName(code: Int) : String{
        return when(code){
            R.color.car_black -> Colors.BLACK.toString()
            R.color.car_meteor_grey -> Colors.METEOR_GREY.toString()
            R.color.car_bright_white -> Colors.BRIGHT_WHITE.toString()
            R.color.car_candy_white -> Colors.CANDY_WHITE.toString()
            R.color.car_brilliant_silver -> Colors.BRILLIANT_SILVER.toString()
            R.color.car_energy_blue -> Colors.ENERGY_BLUE.toString()
            R.color.car_race_blue -> Colors.RACE_BLUE.toString()
            R.color.car_velvet_red -> Colors.VELVET_RED.toString()
            R.color.car_corrida_red -> Colors.CORRIDA_RED.toString()
            R.color.car_yellow -> Colors.YELLOW.toString()
            else -> Colors.ORANGE.toString()
        }
    }

}

enum class Colors{
    BLACK,
    METEOR_GREY,
    BRIGHT_WHITE,
    CANDY_WHITE,
    BRILLIANT_SILVER,
    ENERGY_BLUE,
    RACE_BLUE,
    VELVET_RED,
    CORRIDA_RED,
    YELLOW,
    ORANGE
}