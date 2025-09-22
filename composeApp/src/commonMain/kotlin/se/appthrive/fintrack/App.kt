package se.appthrive.fintrack

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.backhandler.BackHandler
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.setup_a_pin
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.appthrive.fintrack.account_setup.CreatePasscodeScreen
import se.appthrive.fintrack.account_setup.GettingStartedScreen
import se.appthrive.fintrack.onboarding.OnboardingScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    var currentScreen by remember { mutableStateOf("GettingStarted") }
    val setPinTitle = stringResource(Res.string.setup_a_pin)
    MaterialTheme {
        BackHandler(enabled = currentScreen == "CreatePasscode") {
            currentScreen = "GettingStarted"
        }
        when (currentScreen) {
            "Onboarding" -> OnboardingScreen()
            "GettingStarted" -> GettingStartedScreen { setUpItem ->
                if (setUpItem.title == setPinTitle) {
                    currentScreen = "CreatePasscode"
                }
            }

            "CreatePasscode" -> CreatePasscodeScreen { currentScreen = "GettingStarted" }
        }
    }
}