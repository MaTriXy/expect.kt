package com.nhaarman.expect

import org.junit.Test

class ListMatcherTest {

    @Test
    fun example_toBeEmpty() {
        val actual = emptyList<String>()

        expect(actual).toBeEmpty()
    }

    @Test
    fun example_toContain() {
        val actual = listOf("one", "two")
        val expected = "one"

        expect(actual).toContain(expected)
    }

    @Test
    fun example_toHaveSize() {
        val actual = listOf("one", "two")
        val expected = 2

        expect(actual).toHaveSize(expected)
    }

    @Test
    fun toBeEmpty_withEmptyList_accepts() {
        /* Given */
        val actual = emptyList<String>()
        val matcher = ListMatcher(actual)

        /* When */
        matcher.toBeEmpty()

        /* Then */
        awesome()
    }

    @Test
    fun toBeEmpty_withNonEmptyList_fails() {
        /* Given */
        val actual = listOf("one")
        val matcher = ListMatcher(actual)

        /* Then */
        expectErrorWithMessage("to be empty") when_ {
            matcher.toBeEmpty()
        }
    }

    @Test
    fun toBeEmpty_withNullList_fails() {
        /* Given */
        val actual: List<String>? = null
        val matcher = ListMatcher(actual)

        /* Then */
        expectErrorWithMessage("to be empty") when_ {
            matcher.toBeEmpty()
        }
    }

    @Test
    fun toContain_withContaining_accepts() {
        /* Given */
        val actual = listOf("one", "two")
        val expected = "two"
        val matcher = ListMatcher(actual)

        /* When */
        matcher.toContain(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toContain_withNotContaining_fails() {
        /* Given */
        val actual = listOf("one", "two")
        val expected = "three"
        val matcher = ListMatcher(actual)

        /* Then */
        expectErrorWithMessage("to contain") when_ {

            matcher.toContain(expected)
        }
    }

    @Test
    fun toContain_withNullValue_fails() {
        /* Given */
        val expected = "one"
        val matcher = ListMatcher<String>(null)

        /* Then */
        expectErrorWithMessage("but the actual value was null") when_ {

            matcher.toContain(expected)
        }
    }

    @Test
    fun toHaveSize_withProperSize_accepts() {
        /* Given */
        val actual = listOf("one", "two")
        val expected = 2
        val matcher = ListMatcher(actual)

        /* When */
        matcher.toHaveSize(expected)

        /* Then */
        awesome()
    }

    @Test
    fun toHaveSize_withNotProperSize_fails() {
        /* Given */
        val actual = listOf("one", "two")
        val expected = 3
        val matcher = ListMatcher(actual)

        /* Then */
        expectErrorWithMessage("have size 3") when_ {
            matcher.toHaveSize(expected)
        }
    }

    @Test
    fun toHaveSize_withNullList_fails() {
        /* Given */
        val actual: List<String>? = null
        val expected = 3
        val matcher = ListMatcher(actual)

        /* Then */
        expectErrorWithMessage("have size 3") when_ {
            matcher.toHaveSize(expected)
        }
    }
}

