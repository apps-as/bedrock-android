package no.apps.bedrock.ui.navigation

import no.apps.bedrock.domain.usecase.BlockingUseCase2

interface GetNavigationContext : BlockingUseCase2<PageArgs, PageArgs, NavigationContext>