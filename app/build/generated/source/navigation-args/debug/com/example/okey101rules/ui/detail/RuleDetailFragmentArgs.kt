package com.example.okey101rules.ui.detail

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavArgs
import java.lang.IllegalArgumentException
import kotlin.Int
import kotlin.String
import kotlin.jvm.JvmStatic

public data class RuleDetailFragmentArgs(
  public val ruleId: Int,
  public val ruleTitle: String,
) : NavArgs {
  public fun toBundle(): Bundle {
    val result = Bundle()
    result.putInt("ruleId", this.ruleId)
    result.putString("ruleTitle", this.ruleTitle)
    return result
  }

  public fun toSavedStateHandle(): SavedStateHandle {
    val result = SavedStateHandle()
    result.set("ruleId", this.ruleId)
    result.set("ruleTitle", this.ruleTitle)
    return result
  }

  public companion object {
    @JvmStatic
    public fun fromBundle(bundle: Bundle): RuleDetailFragmentArgs {
      bundle.setClassLoader(RuleDetailFragmentArgs::class.java.classLoader)
      val __ruleId : Int
      if (bundle.containsKey("ruleId")) {
        __ruleId = bundle.getInt("ruleId")
      } else {
        throw IllegalArgumentException("Required argument \"ruleId\" is missing and does not have an android:defaultValue")
      }
      val __ruleTitle : String?
      if (bundle.containsKey("ruleTitle")) {
        __ruleTitle = bundle.getString("ruleTitle")
        if (__ruleTitle == null) {
          throw IllegalArgumentException("Argument \"ruleTitle\" is marked as non-null but was passed a null value.")
        }
      } else {
        throw IllegalArgumentException("Required argument \"ruleTitle\" is missing and does not have an android:defaultValue")
      }
      return RuleDetailFragmentArgs(__ruleId, __ruleTitle)
    }

    @JvmStatic
    public fun fromSavedStateHandle(savedStateHandle: SavedStateHandle): RuleDetailFragmentArgs {
      val __ruleId : Int?
      if (savedStateHandle.contains("ruleId")) {
        __ruleId = savedStateHandle["ruleId"]
        if (__ruleId == null) {
          throw IllegalArgumentException("Argument \"ruleId\" of type integer does not support null values")
        }
      } else {
        throw IllegalArgumentException("Required argument \"ruleId\" is missing and does not have an android:defaultValue")
      }
      val __ruleTitle : String?
      if (savedStateHandle.contains("ruleTitle")) {
        __ruleTitle = savedStateHandle["ruleTitle"]
        if (__ruleTitle == null) {
          throw IllegalArgumentException("Argument \"ruleTitle\" is marked as non-null but was passed a null value")
        }
      } else {
        throw IllegalArgumentException("Required argument \"ruleTitle\" is missing and does not have an android:defaultValue")
      }
      return RuleDetailFragmentArgs(__ruleId, __ruleTitle)
    }
  }
}
