package no.apps.bedrock.ui.navigation

import com.bluelinelabs.conductor.ControllerChangeHandler
import no.apps.bedrock.domain.usecase.BlockingUseCase2

interface GetChangeHandler : BlockingUseCase2<PageArgs, PageArgs, ControllerChangeHandler>