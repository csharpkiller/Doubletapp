package com.example.doubletapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

class ViewmodelList : ViewModel(){
    val habitList = mutableStateListOf<HabitListElement>()
}

class HabitListActivityLauncher : ComponentActivity(){
    private var habitList = mutableListOf<HabitListElement>()
    //private lateinit var viewModel: ViewmodelList


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       // viewModel = ViewModelProvider(this).get(ViewmodelList::class.java)

        //TODO поменять подход см Parcelable ну или строками передавать o_O
        val habitInfo = intent.getSerializableExtra("info") as? HabitInfo
        val intentHabitList = intent.getParcelableArrayListExtra<HabitListElement>("habitElementList")?.toMutableList()

        if(intentHabitList!=null){
            habitList = intentHabitList
        }

        if(habitInfo!=null){
            habitList.add(HabitListElement(habitInfo))
            //viewModel.habitList.add(HabitListElement(habitInfo))
            intent.removeExtra("info")
        }


        setContent{
            drawHabitList(habitList)
            //drawHabitList(viewModel.habitList)
        }
    }


    /*private val habitEditorLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val habitInfo = result.data?.getSerializableExtra("info") as? HabitInfo
            if (habitInfo != null) {
                viewModel.habitList.add(HabitListElement(habitInfo))
            }
        }
    }*/

    /*@Composable
    private fun drawHabitList(habitList: List<HabitListElement>){

        Column(modifier = Modifier.fillMaxSize()) {

            Column(modifier = Modifier.padding(vertical = 6.dp).verticalScroll(rememberScrollState())){
                for(habitElement in habitList){
                    habitElement.getHabitElement(this@HabitListActivityLauncher, habitList)
                }

            }

            FloatingActionButton(onClick = {
                val intent = Intent(this@HabitListActivityLauncher, HabitEditorActivity::class.java)

                //TODO я хочу по-другому сохрнаять состояние
                //
                intent.putParcelableArrayListExtra("habitElementList", ArrayList(habitList))
                //

                startActivity(intent)

                //habitEditorLauncher.launch(intent)
            }){
                Icon(Icons.Filled.Add, contentDescription = "Добавить")
            }
        }
    }*/

    @Composable
    private fun drawHabitList(habitList: List<HabitListElement>) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 72.dp) // отступ снизу для fap
                    .verticalScroll(rememberScrollState())
            ) {
                for (habitElement in habitList) {
                    habitElement.getHabitElement(this@HabitListActivityLauncher, habitList)
                }
            }

            FloatingActionButton(
                onClick = {
                    val intent = Intent(this@HabitListActivityLauncher, HabitEditorActivity::class.java)
                    intent.putParcelableArrayListExtra("habitElementList", ArrayList(habitList))
                    startActivity(intent)
                },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Добавить")
            }
        }
    }
}
