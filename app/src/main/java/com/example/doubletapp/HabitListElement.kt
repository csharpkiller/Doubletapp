package com.example.doubletapp

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
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

    /*@Composable
    fun getHabitElement(context: Context, habitList: List<HabitListElement>) {
        var info by remember { mutableStateOf(habitInfo) }

        Column(
            modifier = Modifier
                .padding(3.dp)
                .fillMaxWidth()
                .clickable
                {
                    updateElement(context, habitList)
                }
                .shadow(3.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            ){
                Text(info.habitName)
                Text(info.habitType)
            }

            Text(info.habitDescription)
            Text(info.habitPeriod)
        }
    }*/

    @Composable
    fun getHabitElement(context: Context, habitList: List<HabitListElement>) {
        var info by remember { mutableStateOf(habitInfo) }

        val primaryColor = Color(0xFF03DAC5)
        val secondaryColor = Color.White
        val textColor = Color.White

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .background(primaryColor, shape = RoundedCornerShape(7.dp))
                .clickable {
                    updateElement(context, habitList)
                }
                .padding(12.dp)
        ) {
            Column {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = info.habitName,
                        style = MaterialTheme.typography.titleLarge.copy(color = textColor), // Белый текст
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        text = info.habitType,
                        style = MaterialTheme.typography.labelSmall.copy(color = secondaryColor), // Бирюзовый текст
                        fontWeight = FontWeight.Normal
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = info.habitDescription,
                    style = MaterialTheme.typography.bodyLarge.copy(color = textColor),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = info.habitPeriod,
                    style = MaterialTheme.typography.bodyMedium.copy(color = secondaryColor),
                    fontWeight = FontWeight.Normal
                )

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = info.habitPriority,
                    style = MaterialTheme.typography.bodyMedium.copy(color = secondaryColor),
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }

    private fun updateElement(context: Context, habitList: List<HabitListElement>){
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