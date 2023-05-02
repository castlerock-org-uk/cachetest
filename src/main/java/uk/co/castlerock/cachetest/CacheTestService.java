package uk.co.castlerock.cachetest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@CacheConfig(cacheNames={"test"})
public class CacheTestService {

    Logger logger = LoggerFactory.getLogger(CacheTestService.class);

    @Cacheable
    public String getCached() {
        logger.info("In cached method");
        return "old";
    }

    @CachePut
    public String refreshCache()  {
        logger.info("Start of refresh method");
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        logger.info("End of Refresh method");
        return "new";
    }


}
