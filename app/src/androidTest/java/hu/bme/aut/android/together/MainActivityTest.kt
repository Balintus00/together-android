package hu.bme.aut.android.together

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import hu.bme.aut.android.together.features.shared.activity.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Test
    @SmallTest
    fun testMainBottomNavBarToProfile() {
        val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.profileFragment)).perform(ViewActions.click())
        scenario.onActivity { activity ->
            assertThat(activity.navController.currentDestination?.id).isEqualTo(R.id.profileFragment)
        }
    }

    @Test
    @SmallTest
    fun testMainBottomNavBarToCurrentEventsList() {
        val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.currentEventsListFragment)).perform(ViewActions.click())
        scenario.onActivity { activity ->
            assertThat(activity.navController.currentDestination?.id).isEqualTo(R.id.currentEventsListFragment)
        }
    }

    @Test
    @SmallTest
    fun testMainBottomNavBarToEventSearch() {
        val scenario = launchActivity<MainActivity>()
        onView(withId(R.id.eventQueryFragment)).perform(ViewActions.click())
        scenario.onActivity { activity ->
            assertThat(activity.navController.currentDestination?.id).isEqualTo(R.id.eventQueryFragment)
        }
    }

}