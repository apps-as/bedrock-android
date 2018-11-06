package no.apps.bedrock.ui.navigation

import com.bluelinelabs.conductor.Router
import no.apps.bedrock.domain.usecase.BlockingUseCase2

interface GetRouter : BlockingUseCase2<PageArgs?, PageArgs, Router>
