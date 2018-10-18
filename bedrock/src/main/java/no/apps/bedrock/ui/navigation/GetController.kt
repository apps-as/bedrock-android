package no.apps.bedrock.ui.navigation

import com.bluelinelabs.conductor.Controller
import no.apps.bedrock.domain.usecase.BlockingUseCase1

interface GetController : BlockingUseCase1<PageArgs, Controller>