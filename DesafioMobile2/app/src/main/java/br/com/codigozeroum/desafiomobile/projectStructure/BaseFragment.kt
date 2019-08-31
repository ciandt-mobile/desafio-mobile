/*
 * BaseFragment.kt
 * DesafioMobile
 *
 * Created by Flavio Campos on 31/08/19 01:55
 * Copyright © 2019 Codigo ZeroUm. All rights reserved.
 */

package br.com.codigozeroum.desafiomobile.projectStructure

import android.app.Activity
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import br.com.codigozeroum.desafiomobile.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment() {

    protected abstract fun configureView(view: View)
    protected abstract fun configureViewModel()
    protected abstract fun bindView()


    private val snackBar: Snackbar by lazy {
        Snackbar.make(activity!!.findViewById(android.R.id.content), "", Snackbar.LENGTH_INDEFINITE)
            .apply {
                val color = ContextCompat.getColor(
                    this.context,
                    R.color.colorPrimary
                )
                view.setBackgroundColor(color)
                setActionTextColor(Color.WHITE)
            }
    }

    protected fun showSnackBarWith(message: String, completion: (() -> Unit)? = null) {
        snackBar.setText(message)
        snackBar.setAction(getString(R.string.ok)) {
            completion?.invoke()
            snackBar.dismiss()
        }
        snackBar.show()
    }

    protected fun showSnackBarWithoutAction(activity: Activity, message: String) {
        val view: View = view?.findViewById<View>(android.R.id.content)
            ?: activity.window?.decorView?.findViewById(android.R.id.content) ?: return

        val snackBar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val color = ContextCompat.getColor(activity.applicationContext, R.color.colorPrimary)
        snackBar.view.setBackgroundColor(color)
        snackBar.show()
    }
}