package se.appthrive.fintrack

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.appthrive.fintrack.onboarding.OnboardingScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        OnboardingScreen()
    }
}