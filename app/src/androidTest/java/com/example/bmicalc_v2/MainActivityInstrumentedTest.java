package com.example.bmicalc_v2;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testBMICalculation() {
        Espresso.onView(ViewMatchers.withId(R.id.weightInput)).perform(ViewActions.clearText(), ViewActions.typeText("70"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.heightInput)).perform(ViewActions.clearText(), ViewActions.typeText("175"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.calculateButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.resultText)).check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("22.86"))));

        Espresso.onView(ViewMatchers.withId(R.id.resultText)).check(ViewAssertions.matches(ViewMatchers.withText(Matchers.containsString("Optimum"))));
    }

    @Test
    public void testEmptyInputValidation() {
        Espresso.onView(ViewMatchers.withId(R.id.weightInput)).perform(ViewActions.clearText(), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.heightInput)).perform(ViewActions.clearText(), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.calculateButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.resultText)).check(ViewAssertions.matches(ViewMatchers.withText("Podaj poprawne wartości!")));
    }

    @Test
    public void testZeroInputValidation() {
        Espresso.onView(ViewMatchers.withId(R.id.weightInput)).perform(ViewActions.clearText(), ViewActions.typeText("0"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.heightInput)).perform(ViewActions.clearText(), ViewActions.typeText("175"), ViewActions.closeSoftKeyboard());

        Espresso.onView(ViewMatchers.withId(R.id.calculateButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.resultText)).check(ViewAssertions.matches(ViewMatchers.withText("Waga i wzrost muszą być większe od zera!")));
    }

    @Test
    public void testNavigationToCalorieCalculator() {
        Espresso.onView(ViewMatchers.withId(R.id.caloriesButton)).perform(ViewActions.click());

        Espresso.onView(ViewMatchers.withId(R.id.ageInput)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.activityLevelSpinner)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
        Espresso.onView(ViewMatchers.withId(R.id.calculateCaloriesButton)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
    }
}