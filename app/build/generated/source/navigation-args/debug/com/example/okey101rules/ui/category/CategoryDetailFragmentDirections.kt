package com.example.okey101rules.ui.category

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.okey101rules.R
import kotlin.Int
import kotlin.String

public class CategoryDetailFragmentDirections private constructor() {
  private data class ActionCategoryDetailFragmentToRuleDetailFragment(
    public val ruleId: Int,
    public val ruleTitle: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_categoryDetailFragment_to_ruleDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("ruleId", this.ruleId)
        result.putString("ruleTitle", this.ruleTitle)
        return result
      }
  }

  public companion object {
    public fun actionCategoryDetailFragmentToRuleDetailFragment(ruleId: Int, ruleTitle: String):
        NavDirections = ActionCategoryDetailFragmentToRuleDetailFragment(ruleId, ruleTitle)
  }
}
