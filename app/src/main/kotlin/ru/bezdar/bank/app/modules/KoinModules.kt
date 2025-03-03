package ru.bezdar.bank.app.modules

object KoinModules {

    val all = listOf(
        envVariablesModule,
        coroutineModule,
        databaseModule,
        datasourceModule,
        useCaseModule,
        controllerModule,
    )
}
