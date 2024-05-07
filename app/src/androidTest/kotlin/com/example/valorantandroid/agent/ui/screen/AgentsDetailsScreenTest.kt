package com.example.valorantandroid.agent.ui.screen

import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.valorantandroid.agent.data.model.network.AgentDetailsNetworkModel
import com.example.valorantandroid.agent.domain.model.AgentDetailDomainModel
import com.example.valorantandroid.agent.ui.viewmodel.AgentDetailsUiState
import com.example.valorantandroid.core.ui.ValorantActivity
import com.example.valorantandroid.core.ui.theme.ValorantAndroidTheme
import com.example.valorantandroid.core.utils.constants.Constants
import org.junit.Rule
import org.junit.Test

class AgentsDetailsScreenTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ValorantActivity>()

    private val ability = AgentDetailsNetworkModel.Agent.Ability(
        description = PLACE_HOLDER_STRING,
        displayIcon = PLACE_HOLDER_STRING,
        displayName = PLACE_HOLDER_STRING,
        slot = PLACE_HOLDER_STRING
    )
    private val fakeDomainAgentDetailOne = AgentDetailDomainModel(
        uuid = AGENT_ONE_UUID,
        name = AGENT_ONE_NAME,
        description = AGENT_ONE_DESCRIPTION,
        abilities = listOf(ability),
        fullPortrait = AGENT_ONE_FULL_PORTRAIT
    )

    @Test
    fun given_agentDetailsUiState_is_loading_then_expect_a_loading_screen_to_be_displayed() {
        runWithComposeTestRuleForActivity {
            ValorantAndroidTheme {
                AgentDetailsScreen(agentDetailsUiState = AgentDetailsUiState.Loading)
            }
        }
        with(composeTestRule) {
            onNodeWithTag(Constants.TestTags.AGENTS_DETAILS_LOADING_SCREEN).assertExists()
            onNodeWithTag(Constants.TestTags.AGENT_DETAILS_SUCCESS_SCREEN).assertDoesNotExist()
            onNodeWithTag(Constants.TestTags.AGENT_DETAILS_ERROR_SCREEN).assertDoesNotExist()
        }
    }

    @Test
    fun given_agentDetailsUiState_is_success_then_expect_a_success_screen_to_be_displayed() {
        runWithComposeTestRuleForActivity {
            ValorantAndroidTheme {
                AgentDetailsScreen(
                    agentDetailsUiState = AgentDetailsUiState.Success(
                        agent = fakeDomainAgentDetailOne
                    )
                )
            }
        }
        with(composeTestRule) {
            onNodeWithTag(Constants.TestTags.AGENTS_DETAILS_LOADING_SCREEN).assertDoesNotExist()
            onNodeWithTag(Constants.TestTags.AGENT_DETAILS_SUCCESS_SCREEN).assertExists()
            onNodeWithTag(Constants.TestTags.AGENT_DETAILS_ERROR_SCREEN).assertExists()
        }
    }

    @Test
    fun given_agentDetailsUiState_is_error_then_expect_an_error_screen_to_be_displayed() {
        runWithComposeTestRuleForActivity {
            ValorantAndroidTheme {
                AgentDetailsScreen(
                    agentDetailsUiState = AgentDetailsUiState.Error(message = "Error!")
                )
            }
        }
        with(composeTestRule) {
            onNodeWithTag(Constants.TestTags.AGENTS_DETAILS_LOADING_SCREEN).assertDoesNotExist()
            onNodeWithTag(Constants.TestTags.AGENT_DETAILS_SUCCESS_SCREEN).assertDoesNotExist()
            onNodeWithTag(Constants.TestTags.AGENT_DETAILS_ERROR_SCREEN).assertExists()
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
        const val AGENT_ONE_FULL_PORTRAIT = "www.fakelinktofullportrait-one.com"
        const val PLACE_HOLDER_STRING = ""
    }
}