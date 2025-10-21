package com.example.okey101rules.database;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DatabasePopulator_Factory implements Factory<DatabasePopulator> {
  private final Provider<Context> contextProvider;

  private final Provider<RuleDao> ruleDaoProvider;

  public DatabasePopulator_Factory(Provider<Context> contextProvider,
      Provider<RuleDao> ruleDaoProvider) {
    this.contextProvider = contextProvider;
    this.ruleDaoProvider = ruleDaoProvider;
  }

  @Override
  public DatabasePopulator get() {
    return newInstance(contextProvider.get(), ruleDaoProvider.get());
  }

  public static DatabasePopulator_Factory create(Provider<Context> contextProvider,
      Provider<RuleDao> ruleDaoProvider) {
    return new DatabasePopulator_Factory(contextProvider, ruleDaoProvider);
  }

  public static DatabasePopulator newInstance(Context context, RuleDao ruleDao) {
    return new DatabasePopulator(context, ruleDao);
  }
}
