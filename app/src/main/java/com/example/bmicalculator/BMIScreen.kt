package com.example.bmicalculator

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.DividerLine
import com.example.bmicalculator.ui.theme.HeightCardBackground
import com.example.bmicalculator.ui.theme.PrimaryBlue
import com.example.bmicalculator.ui.theme.PrimaryText
import com.example.bmicalculator.ui.theme.SecondaryText
import com.example.bmicalculator.ui.theme.WeightCardBackground
import com.example.bmicalculator.ui.theme.White

@Composable
fun BMIScreen(modifier: Modifier = Modifier){


    val context = LocalContext.current

    var heightInput by remember{mutableStateOf("")}
    var weightInput by remember{mutableStateOf("")}
    var bmiCategory by remember {mutableStateOf("")}
    var bmiMessage by remember {mutableStateOf("")}
    var bmiValue by remember { mutableStateOf("") }
    var showResultCard by remember {mutableStateOf(false)}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = modifier.height(4.dp))

        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = "BMI Logo",
            modifier = Modifier.size(72.dp),
        )

        Text(
            text= "BMI Calculator",
            fontWeight = FontWeight.Bold,
            fontSize = 26.sp,
            color = PrimaryText
        )

        Text(
            text= "Find your Body Mass Index",
            fontSize = 12.sp,
            color = SecondaryText,
            fontFamily = FontFamily.SansSerif
        )

        HorizontalDivider(
            modifier= Modifier.width(50.dp),
            thickness = 1.dp,
            color = PrimaryBlue
        )
        Spacer(modifier= Modifier.height(10.dp))

        Card(
            modifier = Modifier.fillMaxWidth(0.9f),
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(5.dp),
            colors = CardDefaults.cardColors(
                containerColor = White
            )
        ) {
            InputCard(
                title = "Height",
                value = heightInput,
                onValueChange = {heightInput= it},
                icon = R.drawable.height,
                backgroundColor = HeightCardBackground,
                inputPlaceholder = "Height in meter"
            )
            HorizontalDivider(
                modifier= Modifier
                    .fillMaxWidth(0.85f)
                    .align(Alignment.CenterHorizontally),
                thickness = 1.dp,
                color = DividerLine,
            )
            InputCard(
                title = "Weight",
                value = weightInput,
                onValueChange = {weightInput = it},
                icon = R.drawable.weight,
                backgroundColor = WeightCardBackground,
                inputPlaceholder = "Weight in Kilogram"
            )

        }


        Spacer(modifier= Modifier.height(14.dp))



        Button(
            onClick = {
                if (heightInput.isBlank() || weightInput.isBlank()){
                    Toast.makeText(
                        context,
                        "Enter a valid number.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                else {
                    val height = heightInput.toDoubleOrNull()
                    val weight = weightInput.toDoubleOrNull()
                    if (height == null || weight == null) {

                        Toast.makeText(
                            context,
                            "Enter valid number",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    else if (height <= 0 || weight <= 0) {

                        Toast.makeText(
                            context,
                            "Values must be greater than zero",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    else{
                        val bmi = weight / (height * height)
                        bmiValue = String.format("%.2f", bmi)
                        when {
                            bmi < 18.5 -> {

                                bmiCategory = "Underweight"

                                bmiMessage =
                                    "You should improve your nutrition."

                            }
                            bmi < 25 -> {

                                bmiCategory = "Normal"

                                bmiMessage =
                                    "You have a healthy BMI."

                            }

                            bmi < 30 -> {

                                bmiCategory = "Overweight"

                                bmiMessage =
                                    "You should exercise regularly."

                            }
                            else -> {

                                bmiCategory = "Obese"

                                bmiMessage =
                                    "Consult a healthcare professional."

                            }
                    }
                        showResultCard = true

                    }
                }
                      },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue
            )
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id =R.drawable.calculator),
                    contentDescription = "Calculator",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(5.dp))
                Text(text = "Calculate BMI", fontSize = 15.sp)
            }
        }
        Spacer(modifier= Modifier.height(8.dp))

        OutlinedButton(
            onClick = {
                heightInput = ""
                weightInput = ""
                bmiValue = ""
                bmiCategory = ""
                bmiMessage = ""
                showResultCard = false

                Toast.makeText(
                    context,
                    "Reset Successful",
                    Toast.LENGTH_SHORT
                ).show()
            },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(50.dp),
            border =BorderStroke(
                width = 2.dp,
                color = PrimaryBlue
            ),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            shape = RoundedCornerShape(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id =R.drawable.reset),
                    contentDescription = "Reset",
                    modifier = Modifier.size(18.dp)
                )
                Spacer(Modifier.width(5.dp))
                Text(text = "Reset", fontSize = 15.sp, color = PrimaryBlue)
            }
        }
        Spacer(modifier = Modifier.height(15.dp))

        if (showResultCard) {

            ResultCard(
                bmiValue = bmiValue,
                bmiCategory = bmiCategory,
                bmiMessage = bmiMessage
            )
        }
        }
    }
