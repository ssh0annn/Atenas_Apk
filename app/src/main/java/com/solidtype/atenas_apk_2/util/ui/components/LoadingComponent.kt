package com.solidtype.atenas_apk_2.util.ui.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.solidtype.atenas_apk_2.R

@Composable
fun Loading(
    grande: Boolean = false
) {
    val loadingLottie = rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading))
    val loadingProgress = animateLottieCompositionAsState(
        composition = loadingLottie.value,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        composition = loadingLottie.value,
        progress = { loadingProgress.value },
        modifier = if(grande) Modifier
            .height(300.dp)
            .width(900.dp)
        else Modifier
            .height(100.dp)
            .width(300.dp)
    )
}