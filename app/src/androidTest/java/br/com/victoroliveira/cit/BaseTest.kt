package br.com.victoroliveira.cit

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import org.junit.Assert


open class BaseTest {

    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) =
            ViewActions.click().perform(uiController, view.findViewById<View>(viewId))
    }

    fun suspendUntilSuccess(actionToSucceed: () -> Unit, iteration: Int = 0) {
        try {
            actionToSucceed.invoke()
        } catch (e: Throwable) {
            Thread.sleep(200)
            val incrementedIteration: Int = iteration + 1
            if (incrementedIteration == 50) {
                Assert.fail("Failed after waiting for action to succeed for 10 seconds.")
            }
            suspendUntilSuccess(actionToSucceed, incrementedIteration)
        }
    }
}