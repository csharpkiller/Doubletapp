package com.example.doubletapp

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class HabitEditorActivity() : ComponentActivity(){

    @Override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_habit)
        val titleEditText = findViewById<EditText>(R.id.title)
        val descriptionEditText = findViewById<EditText>(R.id.description)
        val prioritySpinner = findViewById<Spinner>(R.id.priority_spinner)
        val typeRadioGroup = findViewById<RadioGroup>(R.id.type_radio_group)
        val frequencyEditText = findViewById<EditText>(R.id.frequency)
        val periodicityEditText = findViewById<EditText>(R.id.periodicity)
        val saveButton = findViewById<Button>(R.id.save_button)


        val saveListOfHabitListElement = intent.getParcelableArrayListExtra<HabitListElement>("habitElementList") // TODO fix save inst logic
        val habitListElement = intent.getParcelableExtra<HabitListElement>("element")

        if(habitListElement!=null)
        {
            if (saveListOfHabitListElement != null) {
                saveButtonClickListenerUploadHabitInfo(saveListOfHabitListElement, habitListElement,
                    saveListOfHabitListElement,
                    titleEditText,
                    descriptionEditText,
                    prioritySpinner,
                    typeRadioGroup,
                    frequencyEditText,
                    periodicityEditText,
                    saveButton
                )
            }
        }
        else
        {
            saveButtonClickListenerNewHabit(saveListOfHabitListElement,
                titleEditText,
                descriptionEditText,
                prioritySpinner,
                typeRadioGroup,
                frequencyEditText,
                periodicityEditText,
                saveButton
                )

        }
    }

    private fun saveButtonClickListenerUploadHabitInfo(
        saveListOfHabitListElement: ArrayList<HabitListElement>,
        habitListElement: HabitListElement,
        saveListOfHabitListElement1: java.util.ArrayList<HabitListElement>,
        titleEditText: EditText,
        descriptionEditText: EditText,
        prioritySpinner: Spinner,
        typeRadioGroup: RadioGroup,
        frequencyEditText: EditText,
        periodicityEditText: EditText,
        saveButton: Button
    ) {
        saveButton.setOnClickListener{
            val intent = Intent(this, HabitListActivityLauncher::class.java)
            //saveListOfHabitListElement[0].habitInfo = HabitInfo("1337", "1337", "1337", "1337", "1337", color = Color.RED)
            for(element in saveListOfHabitListElement){
                if(element.equals(habitListElement)){
                    //element.habitInfo = HabitInfo("1337", "1337", "1337", "1337", "1337", color = Color.RED)
                    element.habitInfo = getHabitInfo(
                        titleEditText,
                        descriptionEditText,
                        prioritySpinner,
                        typeRadioGroup,
                        frequencyEditText,
                        periodicityEditText
                    )
                }
            }
            intent.putParcelableArrayListExtra("habitElementList", ArrayList(saveListOfHabitListElement))
            startActivity(intent)
        }
    }

    private fun saveButtonClickListenerNewHabit(
        intentHabitList: java.util.ArrayList<HabitListElement>?,
        titleEditText: EditText,
        descriptionEditText: EditText,
        prioritySpinner: Spinner,
        typeRadioGroup: RadioGroup,
        frequencyEditText: EditText,
        periodicityEditText: EditText,
        saveButton: Button
    ) {
        saveButton.setOnClickListener{

            val habitInfo = getHabitInfo(
                titleEditText,
                descriptionEditText,
                prioritySpinner,
                typeRadioGroup,
                frequencyEditText,
                periodicityEditText
            )

            val intent = Intent(this, HabitListActivityLauncher::class.java)
            intent.putExtra("info", habitInfo)

            //TODO я хочу по-другому сохрнаять состояние
            //
            intent.putParcelableArrayListExtra("habitElementList", ArrayList(intentHabitList))
            //

            startActivity(intent)
        }
    }

    private fun getHabitInfo(
        titleEditText: EditText,
        descriptionEditText: EditText,
        prioritySpinner: Spinner,
        typeRadioGroup: RadioGroup,
        frequencyEditText: EditText,
        periodicityEditText: EditText
    ): HabitInfo{
        val title = titleEditText.text.toString()
        val description = descriptionEditText.text.toString()
        val frequency = frequencyEditText.text.toString()
        val periodicity = periodicityEditText.text.toString()

        val selectedTypeRadioButtonId = typeRadioGroup.checkedRadioButtonId
        val selectedTypeRadioButton = findViewById<RadioButton>(selectedTypeRadioButtonId)
        val selectedType = selectedTypeRadioButton?.text?.toString() ?: "Нейтральная"

        //TODO норм обработку сделать у приоритета
        return HabitInfo(title, description, "?????", selectedType, frequency, color = Color.RED)
    }

}
