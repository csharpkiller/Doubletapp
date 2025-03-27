package com.example.doubletapp

import java.io.Serializable

data class HabitInfo(
    val habitName: String,
    val habitDescription: String,
    val habitPriority: String,
    val habitType: String,
    val habitPeriod: String,
    val color: Int
): Serializable