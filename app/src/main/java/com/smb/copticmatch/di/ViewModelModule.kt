/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.smb.copticmatch.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.smb.copticmatch.common.SmbViewModelFactory
import com.smb.copticmatch.ui.chat.ChatViewModel
import com.smb.copticmatch.ui.discover.DiscoverViewModel
import com.smb.copticmatch.ui.like.LikeViewModel
import com.smb.copticmatch.ui.login.LoginViewModel
import com.smb.copticmatch.ui.matches.MatchesViewModel
import com.smb.copticmatch.ui.profile.ProfileViewModel


import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: SmbViewModelFactory): ViewModelProvider.Factory

    @Binds
   @IntoMap
   @ViewModelKey(LoginViewModel::class)
   abstract fun bindRepoViewModel(viewModel: LoginViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(DiscoverViewModel::class)
    abstract fun bindDiscoverViewModel(viewModel: DiscoverViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LikeViewModel::class)
    abstract fun bindLikeViewModel(viewModel: LikeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MatchesViewModel::class)
    abstract fun bindMatchesViewModel(viewModel: MatchesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ChatViewModel::class)
    abstract fun bindChatViewModel(viewModel: ChatViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(viewModel: ProfileViewModel): ViewModel
}
