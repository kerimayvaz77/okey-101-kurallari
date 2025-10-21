package com.example.okey101rules.ui.detail;

import com.example.okey101rules.data.RuleRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class RuleDetailViewModel_Factory implements Factory<RuleDetailViewModel> {
  private final Provider<RuleRepository> repositoryProvider;

  public RuleDetailViewModel_Factory(Provider<RuleRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public RuleDetailViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static RuleDetailViewModel_Factory create(Provider<RuleRepository> repositoryProvider) {
    return new RuleDetailViewModel_Factory(repositoryProvider);
  }

  public static RuleDetailViewModel newInstance(RuleRepository repository) {
    return new RuleDetailViewModel(repository);
  }
}
