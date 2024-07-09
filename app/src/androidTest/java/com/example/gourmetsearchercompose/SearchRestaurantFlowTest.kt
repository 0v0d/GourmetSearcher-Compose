package com.example.gourmetsearchercompose

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/** レストラン検索のフローをテストするクラス */
@RunWith(AndroidJUnit4::class)
@LargeTest
class SearchRestaurantFlowTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    /** テキストを入力し、範囲を選択して、レストランの画像が表示されることを確認する*/
    @Test
    fun testInputTextSelectRangeAndCheckImageDisplayed() {

    }
}
