package az.innakhchivan.controller;

import az.innakhchivan.service.GlobalSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class GlobalSearchController {
    private final GlobalSearchService globalSearchService;

    @GetMapping
    public Map<String, Object> search(@RequestParam String keyword, @RequestParam(required = false, defaultValue = "az") String lang,
                                      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        if (size > 50) {
            size = 50;
        }
        return globalSearchService.search(keyword, lang, page, size);
    }
}
