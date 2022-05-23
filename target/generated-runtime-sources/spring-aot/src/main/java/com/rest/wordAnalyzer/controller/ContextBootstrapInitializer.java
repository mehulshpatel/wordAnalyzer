package com.rest.wordAnalyzer.controller;

import com.rest.wordAnalyzer.service.WordFrequencyAnalyzer;
import java.lang.reflect.Field;
import org.springframework.aot.beans.factory.BeanDefinitionRegistrar;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.util.ReflectionUtils;

public final class ContextBootstrapInitializer {
  public static void registerWordAnalyzerController(DefaultListableBeanFactory beanFactory) {
    BeanDefinitionRegistrar.of("wordAnalyzerController", WordAnalyzerController.class)
        .instanceSupplier((instanceContext) -> {
          WordAnalyzerController bean = new WordAnalyzerController();
          instanceContext.field("wordFrequencyAnalyzer", WordFrequencyAnalyzer.class)
              .invoke(beanFactory, (attributes) -> {
                Field wordFrequencyAnalyzerField = ReflectionUtils.findField(WordAnalyzerController.class, "wordFrequencyAnalyzer", WordFrequencyAnalyzer.class);
                ReflectionUtils.makeAccessible(wordFrequencyAnalyzerField);
                ReflectionUtils.setField(wordFrequencyAnalyzerField, bean, attributes.get(0));
              });
                  return bean;
                }).register(beanFactory);
          }
        }
