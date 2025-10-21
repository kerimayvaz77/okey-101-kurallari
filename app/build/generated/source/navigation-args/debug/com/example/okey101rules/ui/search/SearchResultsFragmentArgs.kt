package com.example.okey101rules.ui.search

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.String
import kotlin.jvm.JvmStatic

public data class SearchResultsFragmentArgs(
  public val searchQuery: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putString("searchQuery", this.searchQuery)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("searchQuery", this.searchQuery)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): SearchResultsFragmentArgs {
      bundle.setClassLoader(SearchResultsFragmentArgs::class.java.classLoader)
      val __searchQuery : String?
      if (bundle.containsKey("searchQuery")) {
        __searchQuery = bundle.getString("searchQuery")
        if (__searchQuery == null) {
          throw IllegalArgumentException("Argument \"searchQuery\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"searchQuery\" is missing and does not have an android:defaultValue")
      }
      return SearchResultsFragmentArgs(__searchQuery)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): SearchResultsFragmentArgs {
      val __searchQuery : String?
      if (savedStateHandle.contains("searchQuery")) {
        __searchQuery = savedStateHandle["searchQuery"]
        if (__searchQuery == null) {
          throw IllegalArgumentException("Argument \"searchQuery\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"searchQuery\" is missing and does not have an android:defaultValue")
      }
      return SearchResultsFragmentArgs(__searchQuery)
    }
  }
}
