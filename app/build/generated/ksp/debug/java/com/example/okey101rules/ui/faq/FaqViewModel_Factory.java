package com.example.okey101rules.ui.faq;

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
public final class FaqViewModel_Factory implements Factory<FaqViewModel> {
  @Override
  public FaqViewModel get() {
    return newInstance();
  }

  public static FaqViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static FaqViewModel newInstance() {
    return new FaqViewModel();
  }

  private static final class InstanceHolder {
    private static final FaqViewModel_Factory INSTANCE = new FaqViewModel_Factory();
  }
}
