package az.innakhchivan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableScheduling
public class SchedulingConfig {

//    @Bean
//    public ThreadPoolTaskScheduler taskScheduler() {
//        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
//        scheduler.setPoolSize(5); // Eyni anda 5 thread işlədə bilərik
//        scheduler.setThreadNamePrefix("ScheduledTask-");
//        scheduler.setWaitForTasksToCompleteOnShutdown(true); // Shutdown zamanı tapşırıqları tamamlamağa icazə veririk
//        scheduler.setAwaitTerminationSeconds(30); // Shutdown-da gözləmə müddəti
//        return scheduler;
//    }

}
