package com.example.movie.ui.list.movie

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.movie.R
import com.example.movie.ui.list.MainActivity
import com.example.movie.utils.DataDummy
import com.example.movie.utils.formatDate
import org.junit.Rule
import org.junit.Test

class MoviesFragmentTest {

    private val dummyMovies = DataDummy.generateMovies()
    
    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)
    
    @Test
    fun loadMovies() {
        onView(withId(R.id.rvMovies)).check(matches(isDisplayed()))
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovies.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rvMovies)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
            ViewActions.click()
        ))
        onView(withId(R.id.detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_title)).check(matches(withText(dummyMovies[0].title)))
        onView(withId(R.id.detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_date)).check(matches(withText(dummyMovies[0].releaseDate.formatDate())))
        onView(withId(R.id.detail_language)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_language)).check(matches(withText(dummyMovies[0].language)))
        onView(withId(R.id.detail_vote)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_vote)).check(matches(withText(dummyMovies[0].voteAverage.toString())))
        onView(withId(R.id.detail_popularity)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_popularity)).check(matches(withText(dummyMovies[0].popularity.toString())))
        onView(withId(R.id.detail_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.detail_overview)).check(matches(withText(dummyMovies[0].overview)))
    }
}