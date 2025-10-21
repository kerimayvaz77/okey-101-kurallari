package com.example.okey101rules.ui;

import com.example.okey101rules.database.DatabasePopulator;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class MainActivity_MembersInjector implements MembersInjector<MainActivity> {
  private final Provider<DatabasePopulator> databasePopulatorProvider;

  public MainActivity_MembersInjector(Provider<DatabasePopulator> databasePopulatorProvider) {
    this.databasePopulatorProvider = databasePopulatorProvider;
  }

  public static MembersInjector<MainActivity> create(
      Provider<DatabasePopulator> databasePopulatorProvider) {
    return new MainActivity_MembersInjector(databasePopulatorProvider);
  }

  @Override
  public void injectMembers(MainActivity instance) {
    injectDatabasePopulator(instance, databasePopulatorProvider.get());
  }

  @InjectedFieldSignature("com.example.okey101rules.ui.MainActivity.databasePopulator")
  public static void injectDatabasePopulator(MainActivity instance,
      DatabasePopulator databasePopulator) {
    instance.databasePopulator = databasePopulator;
  }
}
