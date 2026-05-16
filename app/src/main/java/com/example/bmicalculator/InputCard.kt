package com.example.bmicalculator

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmicalculator.ui.theme.DividerLine
import com.example.bmicalculator.ui.theme.PrimaryBlue

@Composable
fun InputCard(
    title: String,
    value: String,
    onValueChange: (String) -> Unit,
    icon: Int,
    backgroundColor: Color,
    inputPlaceholder: String
){
    Row(
        modifier = Modifier.padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(height = 70.dp, width = 60.dp)
                .background(
                    color =backgroundColor,
                    shape =RoundedCornerShape(15.dp)),
            contentAlignment = Alignment.Center

        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = title,
                modifier = Modifier.size(40.dp)
            )
        }
        Spacer(modifier= Modifier.width(10.dp))
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(text= title,
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier= Modifier.height(5.dp))

            OutlinedTextField(
                value = value,
                onValueChange = onValueChange,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                placeholder = {Text(inputPlaceholder, fontSize = 12.sp)},
                modifier = Modifier.height(55.dp),
                shape = RoundedCornerShape(15.dp),
                textStyle = TextStyle(fontSize = 13.sp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = PrimaryBlue,
                    unfocusedBorderColor = DividerLine
                )
            )
        }
    }
}