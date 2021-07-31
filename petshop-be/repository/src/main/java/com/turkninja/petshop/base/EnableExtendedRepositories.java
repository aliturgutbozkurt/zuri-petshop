package com.turkninja.petshop.base;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to enable {@link ExtendedQueryDslJpaRepository} support.
 * mvn install -Dgpg.skip
 *
 * @see EnableJpaRepositories
 */
@EnableJpaRepositories(repositoryBaseClass = SimpleExtendedQueryDslJpaRepository.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableExtendedRepositories {
}
