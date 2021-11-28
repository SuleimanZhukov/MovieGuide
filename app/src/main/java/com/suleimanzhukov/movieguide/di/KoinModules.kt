package com.suleimanzhukov.movieguide.di

import com.suleimanzhukov.movieguide.framework.ui.details.DetailsViewModel
import com.suleimanzhukov.movieguide.framework.ui.main.MainViewModel
import com.suleimanzhukov.movieguide.framework.ui.wishlist.WishlistViewModel
import com.suleimanzhukov.movieguide.model.repository.Repository
import com.suleimanzhukov.movieguide.model.repository.RepositoryImpl
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }

    viewModel { MainViewModel(get()) }
    viewModel { DetailsViewModel(get()) }
    viewModel { WishlistViewModel(get()) }
}