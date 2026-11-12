package io.github.sangcomz.commercenavigation.core.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

typealias FeatureEntryPoint = EntryProviderScope<NavKey>.(NavBackStack<NavKey>) -> Unit
