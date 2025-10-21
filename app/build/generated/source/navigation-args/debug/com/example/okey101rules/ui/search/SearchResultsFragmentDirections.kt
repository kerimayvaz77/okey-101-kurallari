package com.example.okey101rules.ui.search

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.okey101rules.R
import kotlin.Int
import kotlin.String

public class SearchResultsFragmentDirections private constructor() {
  private data class ActionSearchResultsFragmentToRuleDetailFragment(
    public val ruleId: Int,
    public val ruleTitle: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_searchResultsFragment_to_ruleDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("ruleId", this.ruleId)
        result.putString("ruleTitle", this.ruleTitle)
        return result
      }
  }

  public companion object {
    public fun actionSearchResultsFragmentToRuleDetailFragment(ruleId: Int, ruleTitle: String):
        NavDirections = ActionSearchResultsFragmentToRuleDetailFragment(ruleId, ruleTitle)
  }
}
