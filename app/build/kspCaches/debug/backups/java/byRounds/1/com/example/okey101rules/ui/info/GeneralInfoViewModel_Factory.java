package com.example.okey101rules.ui.info;

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
public final class GeneralInfoViewModel_Factory implements Factory<GeneralInfoViewModel> {
  @Override
  public GeneralInfoViewModel get() {
    return newInstance();
  }

  public static GeneralInfoViewModel_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static GeneralInfoViewModel newInstance() {
    return new GeneralInfoViewModel();
  }

  private static final class InstanceHolder {
    private static final GeneralInfoViewModel_Factory INSTANCE = new GeneralInfoViewModel_Factory();
  }
}
