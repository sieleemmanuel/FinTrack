package se.appthrive.fintrack.account_setup

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.delete
import androidx.compose.foundation.text.input.maxLength
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.capriola_regular
import fintrack.composeapp.generated.resources.create_a_pin
import fintrack.composeapp.generated.resources.create_your_passcode
import fintrack.composeapp.generated.resources.create_your_passcode_desc
import fintrack.composeapp.generated.resources.ic_chevron_left
import fintrack.composeapp.generated.resources.ic_delete_pin
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CreatePasscodeScreen(
    onBackClick: () -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .navigationBarsPadding()
        ) {
            TopBar(onBackClick = onBackClick)
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = stringResource(resource = Res.string.create_your_passcode_desc),
                style = MaterialTheme.typography.titleMedium.copy(
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.W400,
                    fontFamily = FontFamily(Font(Res.font.capriola_regular))
                ),
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(48.dp))
            PinForm(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 20.dp)
            )
            Button(
                onClick = {/*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 20.dp
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF008080),
                )
            ) {
                Text(
                    text = stringResource(Res.string.create_a_pin),
                    fontFamily = FontFamily(Font(Res.font.capriola_regular))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun TopBar(onBackClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 20.dp, top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(resource = Res.drawable.ic_chevron_left),
                contentDescription = "icon back"
            )
        }
        Text(
            text = stringResource(resource = Res.string.create_your_passcode),
            style = MaterialTheme.typography.titleLarge.copy(
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.W400,
                fontFamily = FontFamily(Font(Res.font.capriola_regular))
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun PinForm(modifier: Modifier = Modifier) {
    val pinState = rememberTextFieldState()
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(48.dp)
    ) {
        BasicTextField(
            state = pinState,
            lineLimits = TextFieldLineLimits.SingleLine,
            readOnly = true,
            inputTransformation = InputTransformation.maxLength(4),
            decorator = {
                val pin = pinState.text.toString()
                Row {
                    repeat(4) { index ->
                        PinDigitBox(
                            digit = if (index < pin.length) pin[index] else 'O',
                        )
                        if (index < 3) Spacer(modifier = Modifier.width(10.dp))
                    }
                }
            }
        )
        PinButtons(
            onDigitClick = {
                pinState.edit {
                    if (pinState.text.length < 4) {
                        append(it)
                    }
                }
            },
            onDeleteClick = {
                pinState.edit {
                    if (pinState.text.isNotEmpty()) {
                        delete(pinState.text.length - 1, pinState.text.length)
                    }
                }
            }
        )
    }
}


@Composable
fun PinDigitBox(
    digit: Char = 'O',
) {
    val textColor by animateColorAsState(
        if (digit == 'O') Color(0xFFCECFD2) else Color(0xFF161B26)
    )
    Box(
        modifier = Modifier
            .size(72.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFCECFD2),
                shape = RoundedCornerShape(4.dp)
            )
    ) {
        Text(
            text = digit.toString(),
            style = TextStyle(
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                color = textColor
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun PinButtons(
    onDigitClick: (String) -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    val firstRow = listOf("1", "2", "3")
    val secondRow = listOf("4", "5", "6")
    val thirdRow = listOf("7", "8", "9")
    val fourthRow = listOf(".", "0", "delete")
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(4) { index ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val rowDigits = when (index) {
                    0 -> firstRow
                    1 -> secondRow
                    2 -> thirdRow
                    else -> fourthRow
                }
                rowDigits.forEach { digit ->
                    PinFormButton(
                        modifier = Modifier.weight(1f),
                        value = digit,
                        onBtnClick = onDigitClick,
                        onDeleteClick = onDeleteClick
                    )
                }
            }
        }
    }
}

@Composable
private fun PinFormButton(
    modifier: Modifier = Modifier,
    value: String = "1",
    onBtnClick: (String) -> Unit = {},
    onDeleteClick: () -> Unit = {}
) {
    val btnColor = if (value.all { it.isDigit() }) Color(0xFFF5F5F6) else Color.Transparent
    Surface(
        modifier = modifier.clickable { onBtnClick(value) },
        color = btnColor
    ) {
        when {
            value == "delete" -> {
                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        painter = painterResource(resource = Res.drawable.ic_delete_pin),
                        contentDescription = null,
                    )
                }
            }

            else -> {
                Text(
                    text = value,
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleMedium.copy(
                        textAlign = TextAlign.Center,
                        fontSize = 24.sp,
                        color = Color(0xFF161B26)
                    ),
                )
            }
        }
    }
}

@Preview
@Composable
fun CreatePasscodeScreenPreview() {
    Surface {
        CreatePasscodeScreen()
    }
}