package com.example.okey101rules.ui.category;

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
public final class CategoryViewModel_Factory implements Factory<CategoryViewModel> {
  private final Provider<RuleRepository> repositoryProvider;

  public CategoryViewModel_Factory(Provider<RuleRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public CategoryViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static CategoryViewModel_Factory create(Provider<RuleRepository> repositoryProvider) {
    return new CategoryViewModel_Factory(repositoryProvider);
  }

  public static CategoryViewModel newInstance(RuleRepository repository) {
    return new CategoryViewModel(repository);
  }
}
