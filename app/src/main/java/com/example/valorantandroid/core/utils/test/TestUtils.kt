package com.example.valorantandroid.core.utils.test

import com.example.valorantandroid.agent.domain.model.AgentDomainModel

object TestUtils {
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

    val fakeAgentsList = listOf(
        AgentDomainModel(
            uuid = AGENT_ONE_UUID,
            name = AGENT_ONE_NAME,
            description = AGENT_ONE_DESCRIPTION,
            displayIcon = DISPLAY_ONE_ICON,
            fullPortrait = AGENT_ONE_FULL_PORTRAIT
        ),
        AgentDomainModel(
            uuid = AGENT_TWO_UUID,
            name = AGENT_TWO_NAME,
            description = AGENT_TWO_DESCRIPTION,
            displayIcon = DISPLAY_TWO_ICON,
            fullPortrait = AGENT_TWO_FULL_PORTRAIT
        )
    )

    val fakeAgent = AgentDomainModel(
        AGENT_THREE_UUID,
        AGENT_THREE_NAME,
        AGENT_THREE_DESCRIPTION,
        DISPLAY_THREE_ICON,
        AGENT_THREE_FULL_PORTRAIT
    )
}