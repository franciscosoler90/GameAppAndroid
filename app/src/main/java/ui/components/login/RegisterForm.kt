package ui.components.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fransoler.R
import entity.UserData
import interfaces.LoginInterface

@Composable
fun RegisterForm(loginCallbacks: LoginInterface) {
    Surface {
        var userData by remember { mutableStateOf(UserData()) }
        val iconPainter = painterResource(id = R.drawable.ic_launcher_round)

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 120.dp)
        ) {
            Image(
                painter = iconPainter,
                contentDescription = null,
                modifier = Modifier.size(144.dp)
            )
            Text(
                text = stringResource(id = R.string.register_title),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                NameField(
                    value = userData.name,
                    onChange = { data -> userData = userData.copy(name = data) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                EmailField(
                    value = userData.email,
                    onChange = { data -> userData = userData.copy(email = data) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordField(
                    value = userData.pwd,
                    onChange = { data -> userData = userData.copy(pwd = data) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    loginCallbacks.createAccount(userData)
                },
                enabled = userData.isNotEmpty(),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(id = R.string.register_title))
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = stringResource(id = R.string.have_account_question),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    loginCallbacks.loginActivity()
                }
            )
        }
    }
}
