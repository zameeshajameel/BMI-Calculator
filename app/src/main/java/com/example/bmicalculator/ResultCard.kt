package com.example.bmicalculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.NormalBMI
import com.example.bmicalculator.ui.theme.NormalContainer
import com.example.bmicalculator.ui.theme.ObeseBMI
import com.example.bmicalculator.ui.theme.ObeseContainer
import com.example.bmicalculator.ui.theme.OverweightBMI
import com.example.bmicalculator.ui.theme.OverweightContainer
import com.example.bmicalculator.ui.theme.PrimaryText
import com.example.bmicalculator.ui.theme.UnderweightBMI
import com.example.bmicalculator.ui.theme.UnderweightContainer
import com.example.bmicalculator.ui.theme.White

@Composable
fun ResultCard(
    bmiValue: String,
    bmiCategory: String,
    bmiMessage: String,
) {

    Card(
        modifier = Modifier
            .fillMaxWidth(0.9f),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = when (bmiCategory) {
                "Underweight" -> UnderweightContainer
                "Normal" -> NormalContainer
                "Overweight" -> OverweightContainer
                else -> ObeseContainer
            }
        ),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {

        Column(
            modifier = Modifier.fillMaxWidth().padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "YOUR BMI",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = PrimaryText,
                letterSpacing = 1.sp
            )
            Spacer(modifier = Modifier.height(4.dp))


            Text(
                text = bmiValue,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = when (bmiCategory) {
                    "Underweight" -> UnderweightBMI
                    "Normal" -> NormalBMI
                    "Overweight" -> OverweightBMI
                    else -> ObeseBMI
                }
            )
            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        when (bmiCategory) {
                            "Underweight" -> UnderweightBMI
                            "Normal" -> NormalBMI
                            "Overweight" -> OverweightBMI
                            else -> ObeseBMI
                        }
                    )
                    .padding(horizontal = 16.dp, vertical = 6.dp)
            ) {
                Text(
                    text = bmiCategory,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = White
                )
            }

            Spacer(modifier = Modifier.height(2.dp))

            Text(
                text = bmiMessage,
                fontSize = 13.sp,
                color = PrimaryText,
                textAlign = TextAlign.Center
            )
        }
    }
}