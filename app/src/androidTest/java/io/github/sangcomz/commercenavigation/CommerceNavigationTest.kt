package io.github.sangcomz.commercenavigation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommerceNavigationTest {
    @get:Rule val compose = createAndroidComposeRule<MainActivity>()

    private fun scrollClick(text: String) {
        compose.onNodeWithText(text).performScrollTo().performClick()
    }

    @Test fun productArgumentsAndInternalReviewNavigationSurviveRecreation() {
        scrollClick("캔버스 토트백")
        compose.onNodeWithText("상품 상세").assertIsDisplayed()
        compose.onNodeWithText("캔버스 토트백").assertIsDisplayed()
        compose.activityRule.scenario.recreate()
        compose.onNodeWithText("상품 상세").assertIsDisplayed()
        compose.onNodeWithText("캔버스 토트백").assertIsDisplayed()
        scrollClick("리뷰 보기")
        compose.onNodeWithText("상품 리뷰").assertIsDisplayed()
        compose.onNodeWithText("사진처럼 예쁘고 매일 쓰기 좋아요.").assertIsDisplayed()
        compose.onNodeWithContentDescription("뒤로").performClick()
        compose.onNodeWithText("캔버스 토트백").assertIsDisplayed()
    }

    @Test fun crossFeatureNavigationQuantityAndOrderCompletionWork() {
        scrollClick("데일리 머그")
        compose.onNodeWithText("장바구니 담기").performClick()
        compose.onNodeWithText("장바구니 보기").performClick()
        compose.onNodeWithContentDescription("데일리 머그 수량 늘리기").performScrollTo().performClick()
        compose.onNodeWithContentDescription("데일리 머그 수량 2개").assertIsDisplayed()
        scrollClick("데일리 머그")
        compose.onNodeWithText("상품 상세").assertIsDisplayed()
        compose.onNodeWithText("데일리 머그").assertIsDisplayed()
        compose.onNodeWithContentDescription("뒤로").performClick()
        compose.onNodeWithText("주문 확인하기").performClick()
        compose.onNodeWithText("주문 금액 36,000원").assertIsDisplayed()
        compose.onNodeWithText("샘플 주문하기").performClick()
        compose.onNodeWithText("주문 완료").assertIsDisplayed()
        compose.onNodeWithText("총 36,000원").assertIsDisplayed()
        compose.activityRule.scenario.recreate()
        compose.onNodeWithText("주문 완료").assertIsDisplayed()
        compose.onNodeWithText("총 36,000원").assertIsDisplayed()
        compose.onNodeWithContentDescription("뒤로").performClick()
        compose.onNodeWithText("상품 목록").assertIsDisplayed()
        compose.onNodeWithContentDescription("장바구니 보기").performClick()
        compose.onNodeWithText("장바구니가 비어 있어요.").assertIsDisplayed()
        compose.onNodeWithText("주문 확인하기").assertIsNotEnabled()
    }
}
