/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.galleryapp

import android.content.res.Configuration
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.galleryapp.R
import com.example.galleryapp.images
//import com.example.owl.model.topics
import com.example.galleryapp.MainDestinations
//import com.example.owl.ui.onboarding.Onboarding

fun NavGraphBuilder.images(
    //onCourseSelected: (Long, NavBackStackEntry) -> Unit,
    onboardingComplete: State<Boolean>, // https://issuetracker.google.com/174783110
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(CourseTabs.FEATURED.route) { from ->
        // Show onboarding instead if not shown yet.
//        LaunchedEffect(onboardingComplete) {
//            if (!onboardingComplete.value) {
//                navController.navigate(MainDestinations.ONBOARDING_ROUTE)
//            }
//        }
         // Avoid glitch when showing onboarding
            GalleryImages(
                img = images,
                //selectImg = { id -> onCourseSelected(id, from) },
                modifier = modifier
            )

    }
    composable(CourseTabs.MY_COURSES.route) {
        CPhoto()
    }
    composable(CourseTabs.SEARCH.route) {
        SearchCourses(images, modifier)
    }
}

enum class CourseTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int,
    val route: String
) {
    MY_COURSES(R.string.my_courses, R.drawable.baseline_photo_camera_24, CoursesDestinations.MY_COURSES_ROUTE),
    FEATURED(R.string.featured, R.drawable.baseline_browse_gallery_24, CoursesDestinations.FEATURED_ROUTE),
    SEARCH(R.string.search, R.drawable.ic_search, CoursesDestinations.SEARCH_COURSES_ROUTE)
}

/**
 * Destinations used in the ([OwlApp]).
 */
private object CoursesDestinations {
    const val FEATURED_ROUTE = "GalleryImages"
    const val MY_COURSES_ROUTE = "courses/my"
    const val SEARCH_COURSES_ROUTE = "SearchCourses"
}

