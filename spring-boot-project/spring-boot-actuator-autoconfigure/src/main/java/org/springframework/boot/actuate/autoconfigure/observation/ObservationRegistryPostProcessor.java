/*
 * Copyright 2012-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.autoconfigure.observation;

import io.micrometer.observation.Observation.GlobalKeyValuesProvider;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.ObservationPredicate;
import io.micrometer.observation.ObservationRegistry;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * {@link BeanPostProcessor} that delegates to a lazily created
 * {@link ObservationRegistryConfigurer} to post-process {@link ObservationRegistry}
 * beans.
 *
 * @author Moritz Halbritter
 */
class ObservationRegistryPostProcessor implements BeanPostProcessor {

	private final ObjectProvider<ObservationRegistryCustomizer<?>> observationRegistryCustomizers;

	private final ObjectProvider<ObservationPredicate> observationPredicates;

	private final ObjectProvider<GlobalKeyValuesProvider<?>> tagProviders;

	private final ObjectProvider<ObservationHandler<?>> observationHandlers;

	private final ObjectProvider<ObservationHandlerGrouping> observationHandlerGrouping;

	private volatile ObservationRegistryConfigurer configurer;

	ObservationRegistryPostProcessor(ObjectProvider<ObservationRegistryCustomizer<?>> observationRegistryCustomizers,
			ObjectProvider<ObservationPredicate> observationPredicates,
			ObjectProvider<GlobalKeyValuesProvider<?>> tagProviders,
			ObjectProvider<ObservationHandler<?>> observationHandlers,
			ObjectProvider<ObservationHandlerGrouping> observationHandlerGrouping) {
		this.observationRegistryCustomizers = observationRegistryCustomizers;
		this.observationPredicates = observationPredicates;
		this.tagProviders = tagProviders;
		this.observationHandlers = observationHandlers;
		this.observationHandlerGrouping = observationHandlerGrouping;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof ObservationRegistry) {
			getConfigurer().configure((ObservationRegistry) bean);
		}
		return bean;
	}

	private ObservationRegistryConfigurer getConfigurer() {
		if (this.configurer == null) {
			this.configurer = new ObservationRegistryConfigurer(this.observationRegistryCustomizers,
					this.observationPredicates, this.tagProviders, this.observationHandlers,
					this.observationHandlerGrouping);
		}
		return this.configurer;
	}

}
