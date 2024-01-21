package com.example.valorantandroid.utils

import com.example.valorantandroid.agent.data.model.network.AgentDetailsNetworkModel
import com.example.valorantandroid.agent.data.model.network.AgentsNetworkModel
import com.example.valorantandroid.agent.domain.model.AgentDomainModel

object TestUtils {

    private const val PLACE_HOLDER_STRING = "place-holder-string"
    private const val PLACE_HOLDER_DOUBLE = 22.5
    private const val PLACE_HOLDER_INT = 7
    private const val PLACE_HOLDER_STATUS_CODE = 200

    private const val AGENT_ONE_UUID = "fake-uuid-one"
    private const val AGENT_ONE_NAME = "fakey-one"
    private const val AGENT_ONE_DESCRIPTION = "description-one"
    private const val DISPLAY_ONE_ICON = "www.fakelinktodisplayicon-one.com"
    private const val AGENT_ONE_FULL_PORTRAIT = "www.fakelinktofullportrait-one.com"

    private const val AGENT_TWO_UUID = "fake-uuid-two"
    private const val AGENT_TWO_NAME = "fakey-two"
    private const val AGENT_TWO_DESCRIPTION = "description-two"
    private const val DISPLAY_TWO_ICON = "www.fakelinktodisplayicon-two.com"
    private const val AGENT_TWO_FULL_PORTRAIT = "www.fakelinktofullportrait-two.com"

    private const val AGENT_THREE_UUID = "fake-uuid-three"
    private const val AGENT_THREE_NAME = "fakey-three"
    private const val AGENT_THREE_DESCRIPTION = "description-three"
    private const val DISPLAY_THREE_ICON = "www.fakelinktodisplayicon-three.com"
    private const val AGENT_THREE_FULL_PORTRAIT = "www.fakelinktofullportrait-three.com"

    const val NETWORK_AGENT_DESCRIPTION = "Flying agent"
    const val NETWORK_AGENT_DISPLAY_ICON = "flying agent display icon"
    const val NETWORK_AGENT_FULL_PORTRAIT = "full flying agent portrait"
    const val NETWORK_AGENT_DISPLAY_NAME = "Flyguy"
    const val NETWORK_AGENT_UUID = "fly-guy-uuid"

    val fakeDomainAgentOne = AgentDomainModel(
        uuid = AGENT_ONE_UUID,
        name = AGENT_ONE_NAME,
        description = AGENT_ONE_DESCRIPTION,
        displayIcon = DISPLAY_ONE_ICON,
        fullPortrait = AGENT_ONE_FULL_PORTRAIT
    )

    private val fakeDomainAgentTwo = AgentDomainModel(
        uuid = AGENT_TWO_UUID,
        name = AGENT_TWO_NAME,
        description = AGENT_TWO_DESCRIPTION,
        displayIcon = DISPLAY_TWO_ICON,
        fullPortrait = AGENT_TWO_FULL_PORTRAIT
    )

    private val fakeDomainAgentThree = AgentDomainModel(
        uuid = AGENT_THREE_UUID,
        name = AGENT_THREE_NAME,
        description = AGENT_THREE_DESCRIPTION,
        displayIcon = DISPLAY_THREE_ICON,
        fullPortrait = AGENT_THREE_FULL_PORTRAIT
    )

    val fakeDomainAgentsList = listOf(
        fakeDomainAgentOne,
        fakeDomainAgentTwo,
        fakeDomainAgentThree
    )

    val networkAgentDetail = AgentDetailsNetworkModel.Agent(
        abilities = listOf(
            AgentDetailsNetworkModel.Agent.Ability(
                description = PLACE_HOLDER_STRING,
                displayIcon = PLACE_HOLDER_STRING,
                displayName = PLACE_HOLDER_STRING,
                slot = PLACE_HOLDER_STRING
            )
        ),
        assetPath = PLACE_HOLDER_STRING,
        background = PLACE_HOLDER_STRING,
        backgroundGradientColors = listOf(PLACE_HOLDER_STRING),
        bustPortrait = PLACE_HOLDER_STRING,
        characterTags = listOf(PLACE_HOLDER_STRING),
        description = NETWORK_AGENT_DESCRIPTION,
        developerName = PLACE_HOLDER_STRING,
        displayIcon = NETWORK_AGENT_DISPLAY_ICON,
        displayIconSmall = PLACE_HOLDER_STRING,
        fullPortrait = NETWORK_AGENT_FULL_PORTRAIT,
        displayName = NETWORK_AGENT_DISPLAY_NAME,
        fullPortraitV2 = PLACE_HOLDER_STRING,
        isAvailableForTest = true,
        isBaseContent = true,
        isFullPortraitRightFacing = true,
        isPlayableCharacter = true,
        killFeedPortrait = PLACE_HOLDER_STRING,
        recruitmentData = PLACE_HOLDER_STRING,
        role = AgentDetailsNetworkModel.Agent.Role(
            assetPath = PLACE_HOLDER_STRING,
            description = PLACE_HOLDER_STRING,
            displayIcon = PLACE_HOLDER_STRING,
            displayName = PLACE_HOLDER_STRING,
            uuid = PLACE_HOLDER_STRING
        ),
        uuid = NETWORK_AGENT_UUID,
        voiceLine = AgentDetailsNetworkModel.Agent.VoiceLine(
            maxDuration = PLACE_HOLDER_DOUBLE,
            mediaList = listOf(
                AgentDetailsNetworkModel.Agent.VoiceLine.Media(
                    id = PLACE_HOLDER_INT,
                    wave = PLACE_HOLDER_STRING,
                    wise = PLACE_HOLDER_STRING
                )
            ),
            minDuration = PLACE_HOLDER_DOUBLE
        )
    )

    private val networkAgent = AgentsNetworkModel.Agent(
        abilities = listOf(
            AgentsNetworkModel.Agent.Ability(
                description = PLACE_HOLDER_STRING,
                displayIcon = PLACE_HOLDER_STRING,
                displayName = PLACE_HOLDER_STRING,
                slot = PLACE_HOLDER_STRING
            )
        ),
        assetPath = PLACE_HOLDER_STRING,
        background = PLACE_HOLDER_STRING,
        backgroundGradientColors = listOf(PLACE_HOLDER_STRING),
        bustPortrait = PLACE_HOLDER_STRING,
        characterTags = listOf(PLACE_HOLDER_STRING),
        description = NETWORK_AGENT_DESCRIPTION,
        developerName = PLACE_HOLDER_STRING,
        displayIcon = NETWORK_AGENT_DISPLAY_ICON,
        displayIconSmall = PLACE_HOLDER_STRING,
        fullPortrait = NETWORK_AGENT_FULL_PORTRAIT,
        displayName = NETWORK_AGENT_DISPLAY_NAME,
        fullPortraitV2 = PLACE_HOLDER_STRING,
        isAvailableForTest = true,
        isBaseContent = true,
        isFullPortraitRightFacing = true,
        isPlayableCharacter = true,
        killFeedPortrait = PLACE_HOLDER_STRING,
        role = AgentsNetworkModel.Agent.Role(
            assetPath = PLACE_HOLDER_STRING,
            description = PLACE_HOLDER_STRING,
            displayIcon = PLACE_HOLDER_STRING,
            displayName = PLACE_HOLDER_STRING,
            uuid = PLACE_HOLDER_STRING
        ),
        uuid = NETWORK_AGENT_UUID,
        voiceLine = AgentsNetworkModel.Agent.VoiceLine(
            maxDuration = PLACE_HOLDER_DOUBLE,
            mediaList = listOf(
                AgentsNetworkModel.Agent.VoiceLine.Media(
                    id = PLACE_HOLDER_INT,
                    wave = PLACE_HOLDER_STRING,
                    wise = PLACE_HOLDER_STRING
                )
            ),
            minDuration = PLACE_HOLDER_DOUBLE
        )
    )

    val netWorkAgentDetailWithStatus = AgentDetailsNetworkModel(
        agent = networkAgentDetail,
        status = PLACE_HOLDER_STATUS_CODE
    )

    val networkAgents = AgentsNetworkModel(
        agents = listOf(networkAgent),
        status = PLACE_HOLDER_STATUS_CODE
    )
}
