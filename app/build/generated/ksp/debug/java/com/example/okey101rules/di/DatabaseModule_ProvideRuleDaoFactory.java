package com.example.okey101rules.di;

import com.example.okey101rules.database.AppDatabase;
import com.example.okey101rules.database.RuleDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideRuleDaoFactory implements Factory<RuleDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideRuleDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public RuleDao get() {
    return provideRuleDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideRuleDaoFactory create(
      Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideRuleDaoFactory(databaseProvider);
  }

  public static RuleDao provideRuleDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideRuleDao(database));
  }
}
