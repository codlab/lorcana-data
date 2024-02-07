package eu.codlab.lorcana

import eu.codlab.lorcana.buildconfig.BuildKonfig
import eu.codlab.platform.Platform
import eu.codlab.platform.currentPlatform
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class TestLoadingSet {

    @Test
    fun testSets() = runTest {
        if (null != listOf(Platform.ANDROID, Platform.JS).find { currentPlatform == it }) {
            println("this test is disabled on android or js")
            return@runTest
        }

        val lorcana = Lorcana().loadFromResources()

        SetDescription.entries.forEach {
            try {
                val content = lorcana.set(it)

                assertNotNull(content)
                assertTrue(content.cards.isNotEmpty())
                assertTrue(content.virtualCards.isNotEmpty())
            } catch (@Suppress("SwallowedException") err: Throwable) {
                println("$currentPlatform")
                throw NullPointerException("$currentPlatform")
            }
        }
    }

    @Test
    fun testLoadingSetsFromResources() = runTest {
        if (null != listOf(Platform.ANDROID, Platform.JS).find { currentPlatform == it }) {
            println("it's not possible to test against android or js with files at that time")
            return@runTest
        }

        runTestList {
            val lorcana = Lorcana().loadFromResources()

            lorcana.set(it)!!
        }
    }

    @Test
    fun testLoadingSetsFromGithub() = runTest {
        runTestList {
            val lorcana = Lorcana().loadFromGithub(BuildKonfig.commit)

            lorcana.set(it)!!
        }
    }

    private suspend fun runTestList(provider: suspend (set: SetDescription) -> Set) {
        listOf(
            SetDescription.Promos to 28,
            SetDescription.TFC to 216,
            SetDescription.RotF to 216
        ).forEach { pair ->
            val (set, count) = pair
            val cards = provider(set)

            assertEquals(count, cards.cards.size)
        }
    }
}
