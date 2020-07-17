/*
 * Copyright (C) 2017 The Android Open Source Project
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


import com.smb.copticmatch.ui.chat.ChatFragment
import com.smb.copticmatch.ui.discover.DiscoverFragment
import com.smb.copticmatch.ui.home.HomeFragment
import com.smb.copticmatch.ui.home.TabHostFragment
import com.smb.copticmatch.ui.like.LikeFragment
import com.smb.copticmatch.ui.login.*
import com.smb.copticmatch.ui.matches.MatchesFragment
import com.smb.copticmatch.ui.matches.MatchesHomeFragment
import com.smb.copticmatch.ui.matches.MatchesHostFragment
import com.smb.copticmatch.ui.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector
/**
 * Created by Shijil Kadambath on 03/08/2018
 * for NewAgeSMB
 * Email : shijil@newagesmb.com
 */

@Suppress("unused")
@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun contributeTabHostFragment(): TabHostFragment

    @ContributesAndroidInjector
    abstract fun contributeDiscoverFragment(): DiscoverFragment

    @ContributesAndroidInjector
    abstract fun contributeLikeFragmentt(): LikeFragment

    @ContributesAndroidInjector
    abstract fun contributeMatchesFragment(): MatchesFragment

    @ContributesAndroidInjector
    abstract fun contributeMatchesHomeFragment(): MatchesHomeFragment

    @ContributesAndroidInjector
    abstract fun contributeMatchesHostFragment(): MatchesHostFragment

    @ContributesAndroidInjector
    abstract fun contributeChatFragment(): ChatFragment

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributeSignupFragment(): SignupFragment
    @ContributesAndroidInjector
    abstract fun contributeForgotPasswordFragment(): ForgotPasswordFragment
    @ContributesAndroidInjector
    abstract fun contributeResetPasswordFragment(): ResetPasswordFragment
    @ContributesAndroidInjector
    abstract fun contributeOtpVerificationFragment(): OtpVerificationFragment





}
