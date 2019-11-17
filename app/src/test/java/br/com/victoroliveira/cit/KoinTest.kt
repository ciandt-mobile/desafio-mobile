package br.com.victoroliveira.cit

import android.app.Application
import br.com.victoroliveira.cit.di.AppModule
import br.com.victoroliveira.cit.di.NetworkModule
import org.junit.Before
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class KoinTest : KoinTest {

    @Mock
    private lateinit var context: Application

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun checkModules() {
        startKoin {
            androidContext(context)
            modules(listOf(AppModule, NetworkModule))
        }.checkModules()
    }

}