package com.joyline.matatuguide.ui.screens.onboarding

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.joyline.matatuguide.navigation.Routes
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(navController: NavHostController) {
    val pagerState = rememberPagerState(pageCount = { 3 })
    val scope = rememberCoroutineScope()

    val pages = listOf(
        OnboardingPage("Welcome to MatatuGuide", "Navigate the city like a pro with real-time route info.", "🚌"),
        OnboardingPage("Smart Fare Tracking", "Know your fare before you board. Save more during off-peak hours.", "💰"),
        OnboardingPage("Live Traffic Updates", "Stay informed about traffic status and reach your destination faster.", "🗺️")
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1E3A8A), Color(0xFF3B82F6))
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.weight(1f)
            ) { page ->
                OnboardingContent(pages[page])
            }

            // Indicator and Buttons
            Row(
                Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pages.size) { iteration ->
                    val color = if (pagerState.currentPage == iteration) Color.White else Color.White.copy(alpha = 0.5f)
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .background(color, CircleShape)
                            .size(10.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (pagerState.currentPage < 2) {
                        scope.launch { pagerState.animateScrollToPage(pagerState.currentPage + 1) }
                    } else {
                        navController.navigate(Routes.LOGIN) {
                            popUpTo(Routes.ONBOARDING) { inclusive = true }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 40.dp)
                    .height(56.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color(0xFF1E3A8A))
            ) {
                Text(
                    text = if (pagerState.currentPage == 2) "Get Started" else "Next",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            TextButton(
                onClick = {
                    navController.navigate(Routes.LOGIN) {
                        popUpTo(Routes.ONBOARDING) { inclusive = true }
                    }
                },
                modifier = Modifier.padding(bottom = 40.dp, top = 10.dp)
            ) {
                Text("Skip", color = Color.White.copy(alpha = 0.8f))
            }
        }
    }
}

@Composable
fun OnboardingContent(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = page.icon,
            fontSize = 120.sp
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = page.title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = page.description,
            fontSize = 16.sp,
            color = Color.White.copy(alpha = 0.8f),
            textAlign = TextAlign.Center
        )
    }
}

data class OnboardingPage(val title: String, val description: String, val icon: String)

@Preview
@Composable
fun OnboardingPreview() {
    OnboardingScreen(rememberNavController())
}
