package com.example.okey101rules.ui.home;

import androidx.lifecycle.SavedStateHandle;
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
public final class HomeViewModel_Factory implements Factory<HomeViewModel> {
  private final Provider<RuleRepository> repositoryProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  public HomeViewModel_Factory(Provider<RuleRepository> repositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    this.repositoryProvider = repositoryProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
  }

  @Override
  public HomeViewModel get() {
    return newInstance(repositoryProvider.get(), savedStateHandleProvider.get());
  }

  public static HomeViewModel_Factory create(Provider<RuleRepository> repositoryProvider,
      Provider<SavedStateHandle> savedStateHandleProvider) {
    return new HomeViewModel_Factory(repositoryProvider, savedStateHandleProvider);
  }

  public static HomeViewModel newInstance(RuleRepository repository,
      SavedStateHandle savedStateHandle) {
    return new HomeViewModel(repository, savedStateHandle);
  }
}
