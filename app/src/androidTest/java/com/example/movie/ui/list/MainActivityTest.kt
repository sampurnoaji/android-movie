package com.example.movie.ui.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.PerformException
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.movie.R
import com.example.movie.utils.DataDummy
import com.example.movie.utils.formatDate
import com.google.android.material.tabs.TabLayout
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    private val dummyMovies = DataDummy.generateMovies()
    private val dummyShows = DataDummy.generateShows()

    @get:Rule
    var activityRule = ActivityScenarioRule(MainActivity::class.java)

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
        Espresso.onView(withId(R.id.rvMovies)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.detail_title))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_title))
            .check(ViewAssertions.matches(withText(dummyMovies[0].title)))
        Espresso.onView(withId(R.id.detail_date))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_date))
            .check(ViewAssertions.matches(withText(dummyMovies[0].releaseDate.formatDate())))
        Espresso.onView(withId(R.id.detail_language))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_language))
            .check(ViewAssertions.matches(withText(dummyMovies[0].language)))
        Espresso.onView(withId(R.id.detail_vote))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_vote))
            .check(ViewAssertions.matches(withText(dummyMovies[0].voteAverage.toString())))
        Espresso.onView(withId(R.id.detail_popularity))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_popularity))
            .check(ViewAssertions.matches(withText(dummyMovies[0].popularity.toString())))
        Espresso.onView(withId(R.id.detail_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_overview))
            .check(ViewAssertions.matches(withText(dummyMovies[0].overview)))
    }

    fun selectTabAtPosition(tabIndex: Int): ViewAction {
        return object : ViewAction {
            override fun getDescription() = "with tab at index $tabIndex"

            override fun getConstraints() =
                allOf(isDisplayed(), isAssignableFrom(TabLayout::class.java))

            override fun perform(uiController: UiController, view: View) {
                val tabLayout = view as TabLayout
                val tabAtIndex: TabLayout.Tab = tabLayout.getTabAt(tabIndex)
                    ?: throw PerformException.Builder()
                        .withCause(Throwable("No tab at index $tabIndex"))
                        .build()

                tabAtIndex.select()
            }
        }
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
        Espresso.onView(withId(R.id.rvShows)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                ViewActions.click()
            )
        )
        Espresso.onView(withId(R.id.detail_title))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_title))
            .check(ViewAssertions.matches(withText(dummyShows[9].title)))
        Espresso.onView(withId(R.id.detail_date))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_date))
            .check(ViewAssertions.matches(withText(dummyShows[9].releaseDate.formatDate())))
        Espresso.onView(withId(R.id.detail_language))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_language))
            .check(ViewAssertions.matches(withText(dummyShows[9].language)))
        Espresso.onView(withId(R.id.detail_vote))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_vote))
            .check(ViewAssertions.matches(withText(dummyShows[9].voteAverage.toString())))
        Espresso.onView(withId(R.id.detail_popularity))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_popularity))
            .check(ViewAssertions.matches(withText(dummyShows[9].popularity.toString())))
        Espresso.onView(withId(R.id.detail_overview))
            .check(ViewAssertions.matches(isDisplayed()))
        Espresso.onView(withId(R.id.detail_overview))
            .check(ViewAssertions.matches(withText(dummyShows[9].overview)))
    }
}