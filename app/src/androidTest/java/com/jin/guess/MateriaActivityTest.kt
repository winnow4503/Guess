package com.jin.guess

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MateriaActivityTest {
    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MateriaActivity>(MateriaActivity::class.java)

    @Test
    fun guessWrong() {
        val resources = activityTestRule.activity.resources
        val secret = activityTestRule.activity.secreNumber.secret
        for (n in 1..10) {
            if (n != secret) {
                onView(withId(R.id.number)).perform(clearText())
                onView(withId(R.id.number)).perform(typeText(n.toString()))
                onView(withId(R.id.ok_button)).perform(click())
                val message =
                    if (n < secret) resources.getString(R.string.bigger)
                    else resources.getString(R.string.smaller)
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resources.getString(R.string.ok))).perform(click())
            }
        }
        onView(withId(R.id.number)).perform(closeSoftKeyboard())
        onView(withId(R.id.fab)).perform(click())
        onView(withText("Replay game")).check(matches(isDisplayed()))
        onView(withText(resources.getString(R.string.ok))).perform(click())
        onView(withId(R.id.counter)).check(matches(withText("0")))

    }
}