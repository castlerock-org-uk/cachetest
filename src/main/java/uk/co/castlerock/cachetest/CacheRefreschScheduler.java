package uk.co.castlerock.cachetest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CacheRefreschScheduler {

    private final CacheTestService cacheTestService;
    Logger logger = LoggerFactory.getLogger(CacheRefreschScheduler.class);

    public CacheRefreschScheduler(CacheTestService cacheTestService) {
        this.cacheTestService = cacheTestService;
    }

    @Async
    public void instigateCacheRefresh() {
        var updatedValue = cacheTestService.refreshCache();
        logger.info("Cache updated to: " + updatedValue);
    }


}
