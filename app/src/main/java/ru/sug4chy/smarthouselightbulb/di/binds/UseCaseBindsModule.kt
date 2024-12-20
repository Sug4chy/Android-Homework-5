package ru.sug4chy.smarthouselightbulb.di.binds

import dagger.Binds
import dagger.Module
import ru.sug4chy.smarthouselightbulb.di.AppComponentScope
import ru.sug4chy.smarthouselightbulb.domain.brightness.GetCurrentBrightnessUseCase
import ru.sug4chy.smarthouselightbulb.domain.brightness.SetBrightnessUseCase
import ru.sug4chy.smarthouselightbulb.domain.brightness.implementation.GetCurrentBrightnessUseCaseImpl
import ru.sug4chy.smarthouselightbulb.domain.brightness.implementation.SetBrightnessUseCaseImpl
import ru.sug4chy.smarthouselightbulb.domain.color.GetAllColorsUseCase
import ru.sug4chy.smarthouselightbulb.domain.color.GetCurrentColorUseCase
import ru.sug4chy.smarthouselightbulb.domain.color.SetColorUseCase
import ru.sug4chy.smarthouselightbulb.domain.color.implementation.GetAllColorsUseCaseImpl
import ru.sug4chy.smarthouselightbulb.domain.color.implementation.GetCurrentColorUseCaseImpl
import ru.sug4chy.smarthouselightbulb.domain.color.implementation.SetColorUseCaseImpl
import ru.sug4chy.smarthouselightbulb.domain.state.GetCurrentStateUseCase
import ru.sug4chy.smarthouselightbulb.domain.state.SetStateUseCase
import ru.sug4chy.smarthouselightbulb.domain.state.implementation.GetCurrentStateUseCaseImpl
import ru.sug4chy.smarthouselightbulb.domain.state.implementation.SetStateUseCaseImpl

@Module
internal interface UseCaseBindsModule {

    @Binds
    @AppComponentScope
    fun bindGetCurrentStateUseCase(impl: GetCurrentStateUseCaseImpl): GetCurrentStateUseCase

    @Binds
    @AppComponentScope
    fun bindSetCurrentStateUseCase(impl: SetStateUseCaseImpl): SetStateUseCase

    @Binds
    @AppComponentScope
    fun bindGetCurrentBrightnessUseCase(impl: GetCurrentBrightnessUseCaseImpl): GetCurrentBrightnessUseCase

    @Binds
    @AppComponentScope
    fun bindSetBrightnessUseCase(impl: SetBrightnessUseCaseImpl): SetBrightnessUseCase

    @Binds
    @AppComponentScope
    fun bindGetAllColorsUseCase(impl: GetAllColorsUseCaseImpl): GetAllColorsUseCase

    @Binds
    @AppComponentScope
    fun bindGetCurrentColorUseCase(impl: GetCurrentColorUseCaseImpl): GetCurrentColorUseCase

    @Binds
    @AppComponentScope
    fun bindSetColorUseCase(impl: SetColorUseCaseImpl): SetColorUseCase
}