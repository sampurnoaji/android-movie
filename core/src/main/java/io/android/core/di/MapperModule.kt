package io.android.core.di

import io.android.core.data.mapper.NowPlayingMapper
import org.koin.dsl.module

val mapperModule = module {
    factory { NowPlayingMapper() }
}
