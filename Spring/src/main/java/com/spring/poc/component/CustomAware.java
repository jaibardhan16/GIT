package com.spring.poc.component;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

public class CustomAware implements ApplicationContextAware, ApplicationEventPublisherAware, BeanClassLoaderAware,
		BeanFactoryAware, BeanNameAware, EnvironmentAware, ImportAware, ResourceLoaderAware {

	private Logger LOGGER = Logger.getLogger(CustomAware.class);

	ResourceLoader resourceLoader;

	@Override
	public void setResourceLoader(ResourceLoader arg0) {
		this.resourceLoader = arg0;

	}

	@Override
	public void setImportMetadata(AnnotationMetadata arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEnvironment(Environment arg0) {
		LOGGER.debug("Environment: " + arg0.toString());

	}

	@Override
	public void setBeanName(String arg0) {
		LOGGER.debug("Setting Bean Name: " + arg0.toString());

	}

	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		LOGGER.debug("Setting Bean factory: ");

	}

	@Override
	public void setBeanClassLoader(ClassLoader arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		LOGGER.debug("Setting ApplicationContext: " + arg0.getApplicationName());
	}

}
