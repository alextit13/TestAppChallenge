package com.testapp.challenge.ui.ext

import android.app.Activity
import com.testapp.challenge.App
import com.testapp.challenge.di.ApplicationComponent

/**
 * @author aliakseicherniakovich
 */
fun Activity.appComponent(): ApplicationComponent = (applicationContext as App).appComponent
