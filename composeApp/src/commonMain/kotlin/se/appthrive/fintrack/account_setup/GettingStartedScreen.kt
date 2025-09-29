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
import fintrack.composeapp.generated.resources.app_name
import fintrack.composeapp.generated.resources.capriola_regular
import fintrack.composeapp.generated.resources.create_a_pin
import fintrack.composeapp.generated.resources.create_a_saving_goal
import fintrack.composeapp.generated.resources.create_a_saving_goal_desc
import fintrack.composeapp.generated.resources.img_digital_wallet
import fintrack.composeapp.generated.resources.img_growing_money
import fintrack.composeapp.generated.resources.img_trading_app
import fintrack.composeapp.generated.resources.lets_get_you_setup
import fintrack.composeapp.generated.resources.link_bank_account
import fintrack.composeapp.generated.resources.link_bank_account_desc
import fintrack.composeapp.generated.resources.setup_a_pin
import fintrack.composeapp.generated.resources.setup_a_pin_desc
import fintrack.composeapp.generated.resources.skip_for_now
import fintrack.composeapp.generated.resources.welcome_to
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.appthrive.fintrack.composables.PrimaryButton


@Composable
fun GettingStartedScreen(
    onSkipForNow: () -> Unit = {},
    onSetupItemClick: (SetupItem) -> Unit = {}
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .navigationBarsPadding()
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            WelcomeText(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 20.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))
            SetupItems(
                modifier = Modifier.fillMaxWidth().weight(1f),
                onItemClick = onSetupItemClick
            )
            PrimaryButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                text = stringResource(resource = Res.string.skip_for_now),
                onClick = onSkipForNow
            )
            Spacer(
                modifier = Modifier.height(16.dp)
            )
        }
    }
}

@Composable
private fun WelcomeText(modifier: Modifier = Modifier) {
    val annotatedWelcomeString = buildAnnotatedString {
        append(text = stringResource(Res.string.welcome_to))
        append(" ")
        withStyle(
            style = SpanStyle(fontWeight = FontWeight.Normal, color = Color(0xFF008080))
        ) {
            append(stringResource(Res.string.app_name))
        }
        append("!\n")
        append(stringResource(Res.string.lets_get_you_setup))
    }
    Text(
        text = annotatedWelcomeString,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.W400,
        fontFamily = FontFamily(Font(Res.font.capriola_regular)),
        modifier = modifier
    )
}

@Composable
private fun SetupItems(
    modifier: Modifier = Modifier,
    onItemClick: (SetupItem) -> Unit
) {
    val setupItems = listOf(
        SetupItem(
            title = stringResource(Res.string.setup_a_pin),
            description = stringResource(Res.string.setup_a_pin_desc),
            image = Res.drawable.img_digital_wallet
        ),
        SetupItem(
            title = stringResource(Res.string.link_bank_account),
            description = stringResource(Res.string.link_bank_account_desc),
            image = Res.drawable.img_trading_app
        ),
        SetupItem(
            title = stringResource(Res.string.create_a_saving_goal),
            description = stringResource(Res.string.create_a_saving_goal_desc),
            image = Res.drawable.img_growing_money
        )
    )
    Box(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .verticalScroll(rememberScrollState())
        ) {
            setupItems.forEach{ item ->
                SetupItemCard(
                    setupItem = item,
                    onClick = {
                      onItemClick(item)
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
private fun SetupItemCard(
    setupItem: SetupItem,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = MaterialTheme.shapes.small,
        shadowElevation = 4.dp,
        color = Color(0xFFFAFAFA)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onClick() }
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