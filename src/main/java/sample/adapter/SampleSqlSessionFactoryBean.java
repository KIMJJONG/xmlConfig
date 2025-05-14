package sample.adapter;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.Resource;

import sample.controller.DoController;

public class SampleSqlSessionFactoryBean extends SqlSessionFactoryBean implements DisposableBean {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleSqlSessionFactoryBean.class);
	
	private SqlSessionFactory proxy;
	private int interval = 500;
	private Timer timer;
	private TimerTask task;
	private Resource[] mapperLocations;
	private boolean running = false;
	private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
	private final Lock r = rwl.readLock();
	private final Lock w = rwl.writeLock();
	
	public SampleSqlSessionFactoryBean() {
		LOGGER.info("SampleSqlSessionFactoryBean 생성자");
	}
	
	public void setMapperLocation(Resource[] mapperLocations) {
		LOGGER.info("setMapperLocation 실행");
		super.setMapperLocations(mapperLocations);
		this.mapperLocations = new Resource[mapperLocations.length];
		for (int i = 0; i < mapperLocations.length; i++) {
			this.mapperLocations[i] = mapperLocations[i];
		}
	}
	
	public void setInterval(int interval) {
		LOGGER.info("setInterval 실행");
		this.interval = interval;
	}
	
	public void refresh() {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("refreshing sqlMapClient.");
		}
		w.lock();
		try {
			LOGGER.info("refresh 실행");
			super.afterPropertiesSet();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		} finally {
			w.unlock();
		}
	}
	
	public void afterPropertiesSet() throws Exception {
		LOGGER.info("afterPropertiesSet 실행");
		super.afterPropertiesSet();
		setRefreshable();
	}
	
	private void setRefreshable() {
		LOGGER.info("setRefreshable 실행");
		
		proxy = (SqlSessionFactory) Proxy.newProxyInstance(SqlSessionFactory.class.getClassLoader(), new Class[] { SqlSessionFactory.class }, new InvocationHandler() {
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				LOGGER.info("invoke 실행");
				return method.invoke(getParentObject(), args);
			}
		});
		task = new TimerTask() {
			private Map<Resource, Long> map = new HashMap<Resource, Long>();
			
			public void run() {
				if (isModified()) {
					refresh();
				}
			}
			
			private boolean isModified() {
				boolean retVal = false;
				if (mapperLocations != null) {
					for (int i = 0; i < mapperLocations.length; i++) {
						Resource mappingLocation = mapperLocations[i];
						retVal |= findModifiedResource(mappingLocation);
					}
				}
				return retVal;
			}
			
			private boolean findModifiedResource(Resource resource) {
				boolean retVal = false;
				List<String> modifiedResources = new ArrayList<String>();
				try {
					long modified = resource.lastModified();
					if (map.containsKey(resource)) {
						long lastModified = ((Long) map.get(resource)).longValue();
						if (lastModified != modified) {
							map.put(resource, new Long(modified));
							modifiedResources.add(resource.getDescription());
							retVal = true;
						} 
					} else {
						map.put(resource, new Long(modified));
					}
				} catch (IOException e) {
					LOGGER.error("caught exception", e);
				}
				if (retVal) {
					if (LOGGER.isInfoEnabled()) {
						LOGGER.info("modified files: " + modifiedResources);
					}
				}
				return retVal;
			}
		};
		timer = new Timer(true);
		resetInterval();
	}
	
	private Object getParentObject() throws Exception {
		r.lock();
		try {
			return super.getObject();
		} finally {
			r.unlock();
		}
	}
	
	public SqlSessionFactory getObject() {
		return this.proxy;
	}
	
	public Class<? extends SqlSessionFactory> getObjectType() {
		return (this.proxy != null ? this.proxy.getClass() : SqlSessionFactory.class);
	}
	
	public boolean isSingleton() {
		return true;
	}
	
	public void setCheckInterval(int ms) {
		interval = ms;
		if (timer != null) {
			resetInterval();
		}
	}
	
	public void resetInterval() {
		if (running) {
			timer.cancel();
			running = false;
		}
		if (interval > 0) {
			timer.schedule(task, 0, interval);
			running = true;
		}
	}
	
	public void destory() throws Exception {
		timer.cancel();
	}

	@Override
	public void destroy() throws Exception {
		// TODO Auto-generated method stub
		
	}
	
}
