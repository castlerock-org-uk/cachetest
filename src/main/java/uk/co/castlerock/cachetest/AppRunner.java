package uk.co.castlerock.cachetest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    private final CacheTestService cacheTestService;
    private final CacheRefreschScheduler cacheRefreschScheduler;

    public AppRunner(CacheTestService cacheTestService, CacheRefreschScheduler cacheRefreschScheduler) {
        this.cacheTestService = cacheTestService;
        this.cacheRefreschScheduler = cacheRefreschScheduler;
    }


    @Override
    public void run(String... args) throws InterruptedException {
        logger.info("starting run");
        var response = "nothing";
        var count = 1;
        while (!response.equals("new")) {
            response = cacheTestService.getCached();
            logger.info("Response: " + response);
            if (count == 2) {
                cacheRefreschScheduler.instigateCacheRefresh();
            }
            count ++;
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
