package se.appthrive.fintrack.account_setup

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.capriola_regular
import fintrack.composeapp.generated.resources.img_onboarding_0
import fintrack.composeapp.generated.resources.img_onboarding_1
import fintrack.composeapp.generated.resources.img_onboarding_2
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
fun GettingStartedScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            WelcomeText(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            SetupItems(modifier = Modifier.fillMaxWidth().weight(1f))
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .navigationBarsPadding(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF008080),
                )
            ) {
                Text(
                    text = "Skip for now",
                    fontFamily = FontFamily(Font(Res.font.capriola_regular))
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
private fun WelcomeText(modifier: Modifier = Modifier) {
    val annotatedString = buildAnnotatedString {
        append(text = "Welcome to ")
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Normal, color = Color(0xFF008080))
        ) {
            append("Fintrack")
        }
        append("!\nLet's get you set up.")
    }
    Text(
        text = annotatedString,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.W400,
        fontFamily = FontFamily(Font(Res.font.capriola_regular)),
        modifier = modifier
    )
}

@Composable
private fun SetupItems(modifier: Modifier = Modifier) {
    val setupItems = listOf(
        SetupItem(
            title = "Setup a pin",
            description = "Enhance your account security.",
            image = Res.drawable.img_onboarding_0
        ),
        SetupItem(
            title = "Link your bank accounts",
            description = "Link your bank accounts to start tracking your expenses.",
            image = Res.drawable.img_onboarding_1
        ),
        SetupItem(
            title = "Create a savings goal",
            description = "What are your financial goals?",
            image = Res.drawable.img_onboarding_2
        )
    )
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            repeat(setupItems.size) { index ->
                SetupItemCard(setupItem = setupItems[index])
                Spacer(modifier = Modifier.height(16.dp))
            }

        }
    }
}

@Preview()
@Composable
private fun SetupItemCard(
    setupItem: SetupItem = SetupItem(
        title = "Setup Item",
        description = "Description of the setup item.",
        image = Res.drawable.img_onboarding_1
    ),
    onClick: () -> Unit = {}
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = MaterialTheme.shapes.small,
        shadowElevation = 4.dp,
        color = MaterialTheme.colorScheme.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(vertical = 12.dp)
            ) {
                Text(
                    text = setupItem.title,
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontFamily = FontFamily(Font(Res.font.capriola_regular)),
                        fontWeight = FontWeight.W400
                    )
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = setupItem.description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontFamily = FontFamily(Font(Res.font.capriola_regular)),
                        fontWeight = FontWeight.W400
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Image(
                painter = painterResource(resource = setupItem.image),
                contentDescription = "Next",
                modifier = Modifier
                    .size(124.dp)
            )
        }
    }
}

data class SetupItem(
    val title: String,
    val description: String,
    val image: DrawableResource
)

@Preview()
@Composable
fun GettingStartedPreview() {
    Surface {
        GettingStartedScreen()
    }
}