package com.example.okey101rules.ui.strategy;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava"
})
public final class StrategyViewModel_Factory implements Factory<StrategyViewModel> {
  @Override
  public StrategyViewModel get() {
    return newInstance();
  }

  public static StrategyViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static StrategyViewModel newInstance() {
    return new StrategyViewModel();
  }

  private static final class InstanceHolder {
    private static final StrategyViewModel_Factory INSTANCE = new StrategyViewModel_Factory();
  }
}
