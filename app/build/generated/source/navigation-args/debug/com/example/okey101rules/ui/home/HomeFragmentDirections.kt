package com.example.okey101rules.ui.home

import android.os.Bundle
import androidx.navigation.NavDirections
import com.example.okey101rules.R
import kotlin.Int
import kotlin.String

public class HomeFragmentDirections private constructor() {
  private data class ActionHomeFragmentToCategoryDetailFragment(
    public val category: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_categoryDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("category", this.category)
        return result
      }
  }

  private data class ActionHomeFragmentToSearchResultsFragment(
    public val searchQuery: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_searchResultsFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putString("searchQuery", this.searchQuery)
        return result
      }
  }

  private data class ActionHomeFragmentToRuleDetailFragment(
    public val ruleId: Int,
    public val ruleTitle: String,
  ) : NavDirections {
    public override val actionId: Int = R.id.action_homeFragment_to_ruleDetailFragment

    public override val arguments: Bundle
      get() {
        val result = Bundle()
        result.putInt("ruleId", this.ruleId)
        result.putString("ruleTitle", this.ruleTitle)
        return result
      }
  }

  public companion object {
    public fun actionHomeFragmentToCategoryDetailFragment(category: String): NavDirections =
        ActionHomeFragmentToCategoryDetailFragment(category)

    public fun actionHomeFragmentToSearchResultsFragment(searchQuery: String): NavDirections =
        ActionHomeFragmentToSearchResultsFragment(searchQuery)

    public fun actionHomeFragmentToRuleDetailFragment(ruleId: Int, ruleTitle: String): NavDirections
        = ActionHomeFragmentToRuleDetailFragment(ruleId, ruleTitle)
  }
}
