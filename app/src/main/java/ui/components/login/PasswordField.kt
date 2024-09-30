package ui.components.login

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.google.android.material.R as MaterialR
import com.example.fransoler.R as AppR


@Composable
fun PasswordField(
    value: String,
    onChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = stringResource(id = AppR.string.password),
) {

    var isPasswordVisible by remember { mutableStateOf(false) }

    val icon: Painter = if (isPasswordVisible)
        painterResource(id = MaterialR.drawable.design_ic_visibility)
    else
        painterResource(id = MaterialR.drawable.design_ic_visibility_off)

    val leadingIcon = @Composable {
        Icon(
            Icons.Rounded.Lock,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
    val trailingIcon = @Composable {
        IconButton(onClick = {
            isPasswordVisible = !isPasswordVisible
        }) {
            Icon(
                painter = icon,
                contentDescription = "Visibility Icon"
            )
        }
    }

    TextField(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Password
        ),
        placeholder = {},
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        shape = RoundedCornerShape(8.dp),
    )
}