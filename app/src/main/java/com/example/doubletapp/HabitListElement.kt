package com.example.doubletapp

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import kotlinx.parcelize.Parcelize

@Parcelize
class HabitListElement(
    /*private var habitName: String,
    private var habitDesctiption: String,
    private var habitPriority: Any,
    private var habitType: Any,
    private var habitPeriod: Any,
    private var color: Color*/

    var habitInfo: HabitInfo,
    ) : Parcelable {

    @Composable
    fun getHabitElement(context: Context, habitList: List<HabitListElement>){
        var info  by remember { mutableStateOf(habitInfo) }

        Row(
            modifier = Modifier.clickable{
                updateElement(context, habitList)
            }
        ){
            Text(info.habitName)
            Text(info.habitType)
        }
        /**
         * TODO хотим получить контейнер с элементами внутри, должно отображаться:
         *  название, описание, тип, периодичность
         */
        //return null
    }

    private fun updateElement(context: Context, habitList: List<HabitListElement>){
        /**
         * Хотим секса
         */
        val intent = Intent(context, HabitEditorActivity::class.java)
        intent.putExtra("element", this)
        intent.putParcelableArrayListExtra("habitElementList", ArrayList(habitList))
        context.startActivity(intent)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as HabitListElement

        return habitInfo == other.habitInfo
    }

    override fun hashCode(): Int {
        return habitInfo.hashCode()
    }


}