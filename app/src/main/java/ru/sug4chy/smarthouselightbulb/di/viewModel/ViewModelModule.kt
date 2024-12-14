package ru.sug4chy.smarthouselightbulb.di.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import ru.sug4chy.smarthouselightbulb.presenter.main.MainViewModel

@Module
interface ViewModelModule {

    @Binds
    fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelMapKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}