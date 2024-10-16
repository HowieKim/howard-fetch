package com.howie.fetch.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

@OptIn(ExperimentalCoroutinesApi::class)
class TestCoroutineExtension : BeforeAllCallback, AfterAllCallback {
    override fun beforeAll(context: ExtensionContext?) {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    override fun afterAll(context: ExtensionContext?) {
        Dispatchers.resetMain()
    }
}