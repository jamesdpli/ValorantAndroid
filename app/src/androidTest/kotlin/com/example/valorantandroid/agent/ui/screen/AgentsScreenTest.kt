package com.example.valorantandroid.agent.ui.screen

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.valorantandroid.agent.domain.model.AgentDomainModel
import com.example.valorantandroid.agent.ui.viewmodel.AgentUiState
import com.example.valorantandroid.core.ui.ValorantActivity
import com.example.valorantandroid.core.ui.theme.ValorantAndroidTheme
import com.example.valorantandroid.core.utils.constants.Constants
import org.junit.Rule
import org.junit.Test

class AgentsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ValorantActivity>()

    private val fakeDomainAgentOne = AgentDomainModel(
        uuid = AGENT_ONE_UUID,
        name = AGENT_ONE_NAME,
        description = AGENT_ONE_DESCRIPTION,
        displayIcon = DISPLAY_ONE_ICON,
        fullPortrait = AGENT_ONE_FULL_PORTRAIT
    )

    @Test
    fun given_agentUiState_is_loading_then_expect_a_loading_screen_to_be_displayed() {
        runWithComposeTestRuleForActivity {
            ValorantAndroidTheme {
                AgentsScreen(agentsUiState = AgentUiState.Loading, onAgentClicked = { _, _ -> })
            }
        }
        with(composeTestRule) {
            onNodeWithTag(Constants.TestTags.AGENTS_LIST_LOADING_SCREEN).assertExists()
            onNodeWithTag(Constants.TestTags.AGENT_LIST_SUCCESS_SCREEN).assertDoesNotExist()
            onNodeWithTag(Constants.TestTags.AGENT_LIST_ERROR_SCREEN).assertDoesNotExist()
        }
    }

    @Test
    fun given_agentUiState_is_success_then_a_list_of_agents_will_be_displayed() {
        runWithComposeTestRuleForActivity {
            ValorantAndroidTheme {
                AgentsScreen(
                    agentsUiState = AgentUiState.Success(agents = listOf(fakeDomainAgentOne)),
                    onAgentClicked = { _, _ -> }
                )
            }
        }
        with(composeTestRule) {
            onNodeWithTag(Constants.TestTags.AGENT_LIST_SUCCESS_SCREEN).assertExists()
            onNodeWithTag(Constants.TestTags.AGENTS_LIST_LOADING_SCREEN).assertDoesNotExist()
            onNodeWithTag(Constants.TestTags.AGENT_LIST_ERROR_SCREEN).assertDoesNotExist()
        }
    }

    @Test
    fun given_agentUiState_is_error_then_an_error_screen_is_displayed() {
        runWithComposeTestRuleForActivity {
            ValorantAndroidTheme {
                AgentsScreen(
                    agentsUiState = AgentUiState.Error(message = "Test Error!"),
                    onAgentClicked = { _, _ -> }
                )
            }
        }
        with(composeTestRule) {
            onNodeWithTag(Constants.TestTags.AGENT_LIST_ERROR_SCREEN).assertExists()
            onNodeWithTag(Constants.TestTags.AGENT_LIST_SUCCESS_SCREEN).assertDoesNotExist()
            onNodeWithTag(Constants.TestTags.AGENTS_LIST_LOADING_SCREEN).assertDoesNotExist()
        }
    }

    private fun runWithComposeTestRuleForActivity(
        parent: CompositionContext? = null,
        content: @Composable () -> Unit
    ) = composeTestRule.activity.setContent(parent = parent, content = content)

    private companion object {
        const val AGENT_ONE_UUID = "fake-uuid-one"
        const val AGENT_ONE_NAME = "fakey-one"
        const val AGENT_ONE_DESCRIPTION = "description-one"
        const val DISPLAY_ONE_ICON = "www.fakelinktodisplayicon-one.com"
        const val AGENT_ONE_FULL_PORTRAIT = "www.fakelinktofullportrait-one.com"
    }
}