package com.example.movie.ui.list

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.rule.ActivityTestRule
import com.example.movie.R
import com.example.movie.ui.detail.movie.MovieDetailActivity
import com.example.movie.ui.detail.show.ShowDetailActivity
import com.example.movie.utils.DataDummy
import com.example.movie.utils.DummyData
import com.example.movie.utils.EspressoIdlingResource
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyMovies = DataDummy.generateMovies()
    private val dummyShows = DataDummy.generateShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val activityRuleMovieDetail = ActivityTestRule(MovieDetailActivity::class.java, false, false)

    @get:Rule
    val activityRuleShowDetail = ActivityTestRule(ShowDetailActivity::class.java, false, false)

    @Before
    fun setUp() {
        ActivityScenario.launch(MainActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovies() {
        Espresso.onView(withId(R.id.tabs))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvMovies))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvMovies))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        Espresso.onView(withId(R.id.tabs))
            .check(ViewAssertions.matches(isDisplayed()))

        val intent = Intent().putExtra(MovieDetailActivity.INTENT_KEY_MOVIE_ID, DummyData.movieDetail.id)
        activityRuleMovieDetail.launchActivity(intent)

        Espresso.onView(withId(R.id.detail_title))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(allOf(withId(R.id.detail_title), not(withText(""))))
    }

    @Test
    fun loadShows() {
        Espresso.onView(withId(R.id.tabs))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withText("TV SHOW")).perform(ViewActions.click())
        Espresso.onView(withId(R.id.rvShows))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.rvShows))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyShows.size))
    }

    @Test
    fun loadDetailShow() {
        Espresso.onView(withId(R.id.tabs))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withText("TV SHOW")).perform(ViewActions.click())

        val intent = Intent().putExtra(ShowDetailActivity.INTENT_KEY_SHOW_ID, DummyData.showDetail.id)
        activityRuleShowDetail.launchActivity(intent)

        Espresso.onView(withId(R.id.detail_title))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(allOf(withId(R.id.detail_title), not(withText(""))))
    }
}