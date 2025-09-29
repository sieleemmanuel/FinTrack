package se.appthrive.fintrack.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.unit.dp
import fintrack.composeapp.generated.resources.Res
import fintrack.composeapp.generated.resources.accept_and_continue
import fintrack.composeapp.generated.resources.accept_policy_desc
import fintrack.composeapp.generated.resources.accept_terms_desc
import fintrack.composeapp.generated.resources.already_have_account
import fintrack.composeapp.generated.resources.capriola_regular
import fintrack.composeapp.generated.resources.create_an_account
import fintrack.composeapp.generated.resources.ic_close
import fintrack.composeapp.generated.resources.img_onboarding_0
import fintrack.composeapp.generated.resources.img_onboarding_1
import fintrack.composeapp.generated.resources.img_onboarding_2
import fintrack.composeapp.generated.resources.onboarding_desc_0
import fintrack.composeapp.generated.resources.onboarding_desc_1
import fintrack.composeapp.generated.resources.onboarding_desc_2
import fintrack.composeapp.generated.resources.onboarding_title_0
import fintrack.composeapp.generated.resources.onboarding_title_1
import fintrack.composeapp.generated.resources.onboarding_title_2
import fintrack.composeapp.generated.resources.privacy_policy
import fintrack.composeapp.generated.resources.sign_in
import fintrack.composeapp.generated.resources.terms_and_conditions
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.Font
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import se.appthrive.fintrack.composables.PrimaryButton


@Composable
fun OnboardingScreen() {
    val pagerState = rememberPagerState(
        pageCount = { 3 },
        initialPage = 0,
    )
    val pages = listOf(
        OnBoardingPageItem(
            stringResource(resource = Res.string.onboarding_title_0),
            stringResource(resource = Res.string.onboarding_desc_0),
            Res.drawable.img_onboarding_0
        ),
        OnBoardingPageItem(
            stringResource(resource = Res.string.onboarding_title_1),
            stringResource(resource = Res.string.onboarding_desc_1),
            Res.drawable.img_onboarding_1
        ),
        OnBoardingPageItem(
            stringResource(resource = Res.string.onboarding_title_2),
            stringResource(resource = Res.string.onboarding_desc_2),
            Res.drawable.img_onboarding_2
        )
    )

    var showAcceptTermsSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Indicators(
                modifier = Modifier.statusBarsPadding(),
                pageCount = pages.size,
                selectedPage = { pagerState.currentPage }
            )
            Spacer(modifier = Modifier.height(10.dp))
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) { pageIndex ->
                val page = pages[pageIndex]
                OnboardingPage(
                    title = page.title,
                    description = page.description,
                    image = page.image
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            AuthActions(
                onCreateAccount = {
                    showAcceptTermsSheet = true
                },
                onSignIn = {}
            )
        }
    }

    if (showAcceptTermsSheet) {
        AcceptTermsSheet(
            onDismiss = { showAcceptTermsSheet = false },
            onAccept = {
                showAcceptTermsSheet = false
                // navigate to Signup
            },
            onViewTerms = {},
            onViewPrivacyPolicy = {}
        )
    }
}

@Composable
fun Indicators(
    modifier: Modifier = Modifier,
    pageCount: Int,
    selectedPage: () -> Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        repeat(pageCount) { index ->
            val animatedIndicator by animateColorAsState(targetValue = if (index == selectedPage()) Color.DarkGray else Color.LightGray)
            Box(
                modifier = Modifier
                    .height(6.dp)
                    .weight(1f)
                    .clip(MaterialTheme.shapes.large)
                    .background(color = animatedIndicator)
            )
        }
    }
}

@Composable
fun OnboardingPage(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    image: DrawableResource
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            fontFamily = FontFamily(Font(Res.font.capriola_regular)),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = description,
            fontFamily = FontFamily(Font(Res.font.capriola_regular)),
            color = MaterialTheme.colorScheme.onBackground
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(resource = image),
                contentDescription = null,
            )
        }
    }
}

@Composable
fun AuthActions(
    modifier: Modifier = Modifier,
    onCreateAccount: () -> Unit,
    onSignIn: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp, vertical = 8.dp)
            .navigationBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton(
            text = stringResource(resource = Res.string.create_an_account),
         onClick = onCreateAccount
        )
        Spacer(modifier = Modifier.height(10.dp))
        val annotatedString = buildAnnotatedString {
            append(text = stringResource(resource = Res.string.already_have_account))
            withLink(
                link = LinkAnnotation.Clickable(
                    tag = "SignIn",
                    styles = TextLinkStyles(
                        SpanStyle(
                            color = Color(0xFF008080),
                            textDecoration = TextDecoration.Underline
                        )
                    ),
                    linkInteractionListener = {
                        onSignIn()
                    }
                )
            ) {
                append(stringResource(resource = Res.string.sign_in))
            }
        }
        Text(
            text = annotatedString,
            fontFamily = FontFamily(Font(Res.font.capriola_regular))
        )
        Spacer(modifier = Modifier.height(24.dp))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AcceptTermsSheet(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onAccept: () -> Unit,
    onViewTerms: () -> Unit,
    onViewPrivacyPolicy: () -> Unit
) {
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true,
        confirmValueChange = {
            it == SheetValue.Expanded
        }
    )
    val acceptAnnotated = buildAnnotatedString {
        append(stringResource(resource = Res.string.accept_terms_desc))
        withLink(
            link = LinkAnnotation.Clickable(
                tag = "TermsAndConditions",
                styles = TextLinkStyles(SpanStyle(textDecoration = TextDecoration.Underline)),
                linkInteractionListener = {
                    onViewTerms()
                }
            )
        ) {
            append(stringResource(resource = Res.string.terms_and_conditions))
        }
        append(stringResource(resource = Res.string.accept_policy_desc))
        withLink(
            link = LinkAnnotation.Clickable(
                tag = "PrivacyPolicy",
                styles = TextLinkStyles(SpanStyle(textDecoration = TextDecoration.Underline)),
                linkInteractionListener = {
                    onViewPrivacyPolicy()
                }
            )
        ) {
            append(stringResource(resource = Res.string.privacy_policy))
        }
    }
    val scope = rememberCoroutineScope()
    fun animatedSheetDismissal(onDismissAction: () -> Unit = {}) = scope.launch {
        sheetState.hide()
    }.invokeOnCompletion {
        onDismissAction()
    }

    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = {
            onDismiss()
        },
        dragHandle = null
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(resource = Res.string.create_an_account),
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily(Font(Res.font.capriola_regular))
                )
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = { animatedSheetDismissal { onDismiss() } },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.LightGray
                    )
                ) {
                    Icon(
                        painter = painterResource(resource = Res.drawable.ic_close),
                        contentDescription = "Close"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = acceptAnnotated,
                fontFamily = FontFamily(Font(Res.font.capriola_regular))
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { animatedSheetDismissal { onAccept() } },
                modifier = modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF008080),
                )
            ) {
                Text(
                    text = stringResource(resource = Res.string.accept_and_continue),
                    fontFamily = FontFamily(Font(Res.font.capriola_regular))
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}

@Preview
@Composable
fun OnBoardingScreenScreenPreview() {
    Surface {
        OnboardingScreen()
    }
}

@Immutable
data class OnBoardingPageItem(
    val title: String,
    val description: String,
    val image: DrawableResource
)