package au.edu.swin.sdmd.core2


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun mainActivityTest() {
        //click on an image,
        val appCompatImageView = onView(
            allOf(
                withId(R.id.UniversityImage),
                childAtPosition(
                    allOf(
                        withId(R.id.University),
                        childAtPosition(
                            withClassName(`is`("android.widget.TableRow")),
                            0
                        )
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        appCompatImageView.perform(click())

        // change the name of a location,
        val textInputEditText = onView(
            allOf(
                withId(R.id.name), withText("university"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tillName),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText.perform(replaceText("uni"))

        val textInputEditText2 = onView(
            allOf(
                withId(R.id.name), withText("uni"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.tillName),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText2.perform(closeSoftKeyboard())

        // and ensure this change appears in the first activity
        pressBack()
        val textView = onView(
            allOf(
                withId(R.id.UniversityText), withText("uni"),
                withParent(
                    allOf(
                        withId(R.id.University),
                        withParent(IsInstanceOf.instanceOf(android.widget.TableRow::class.java))
                    )
                ),
                isDisplayed()
            )
        )
        textView.check(matches(withText("uni")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
