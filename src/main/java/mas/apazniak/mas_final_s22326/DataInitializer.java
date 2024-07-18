package mas.apazniak.mas_final_s22326;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class DataInitializer
{
    private final Main main;


    @EventListener
    public void atStart(ContextRefreshedEvent ev) {
        System.out.println(LocalDate.now());
        System.out.println("Context refreshed");
        main.createSampleData();
    }
}
