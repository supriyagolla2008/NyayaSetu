package com.rightsflow.service;

import com.rightsflow.dto.UpdateAndSearchDto;
import com.rightsflow.entity.Right;
import com.rightsflow.entity.SearchHistory;
import com.rightsflow.entity.User;
import com.rightsflow.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SearchService.class);

    public SearchService(RightRepository rightRepository, LawRepository lawRepository, LegalServiceRepository serviceRepository, LegalUpdateRepository updateRepository, SearchHistoryRepository searchHistoryRepository) {
        this.rightRepository = rightRepository;
        this.lawRepository = lawRepository;
        this.serviceRepository = serviceRepository;
        this.updateRepository = updateRepository;
        this.searchHistoryRepository = searchHistoryRepository;
    }



    private final RightRepository rightRepository;
    private final LawRepository lawRepository;
    private final LegalServiceRepository serviceRepository;
    private final LegalUpdateRepository updateRepository;
    private final SearchHistoryRepository searchHistoryRepository;

    @Transactional
    public UpdateAndSearchDto.GlobalSearchResponse performSearch(String query, Long categoryId, String type, User user) {
        log.info("Executing global multi-entity search for: '{}'", query);

        List<UpdateAndSearchDto.SearchResultItem> results = new ArrayList<>();
        String cleanQuery = query != null ? query.trim() : "";

        // 1. Search Rights
        var rightsPage = !cleanQuery.isBlank()
                ? rightRepository.searchRights(cleanQuery, categoryId, "PUBLISHED", "VERIFIED", PageRequest.of(0, 5))
                : rightRepository.findFiltered(categoryId, "PUBLISHED", "VERIFIED", PageRequest.of(0, 5));
        rightsPage.forEach(r -> results.add(UpdateAndSearchDto.SearchResultItem.builder()
                .id(r.getId().toString())
                .type("RIGHT")
                .title(r.getTitle())
                .subtitle(r.getLaws() != null && !r.getLaws().isEmpty() ? r.getLaws().iterator().next().getTitle() : "Statutory Right")
                .snippet(r.getSimpleExplanation())
                .category(r.getCategory() != null ? r.getCategory().getName() : "")
                .categoryColor(r.getCategory() != null ? r.getCategory().getColorTheme() : "blue")
                .url("/rights/" + r.getSlug())
                .officialSource(r.getSourceAuthority() != null ? r.getSourceAuthority() : "India Code")
                .lastVerified(r.getLastVerified() != null ? r.getLastVerified().toString() : "Current")
                .relevanceScore(0.95)
                .build()));

        // 2. Search Laws
        var lawsPage = !cleanQuery.isBlank()
                ? lawRepository.searchLaws(cleanQuery, "ACTIVE", PageRequest.of(0, 3))
                : lawRepository.findFiltered("ACTIVE", PageRequest.of(0, 3));
        lawsPage.forEach(l -> results.add(UpdateAndSearchDto.SearchResultItem.builder()
                .id(l.getId().toString())
                .type("LAW")
                .title(l.getTitle())
                .subtitle(l.getShortCode() != null ? l.getShortCode() : "Statute")
                .snippet(l.getDescription())
                .category("Statutory Act")
                .categoryColor("indigo")
                .url("/laws/" + l.getId())
                .officialSource(l.getSource() != null ? l.getSource().getName() : "India Code")
                .lastVerified("Current")
                .relevanceScore(0.85)
                .build()));

        // 3. Search Services
        var servicesPage = !cleanQuery.isBlank()
                ? serviceRepository.searchServices(cleanQuery, categoryId, PageRequest.of(0, 3))
                : serviceRepository.findFiltered(categoryId, PageRequest.of(0, 3));
        servicesPage.forEach(s -> results.add(UpdateAndSearchDto.SearchResultItem.builder()
                .id(s.getId().toString())
                .type("SERVICE")
                .title(s.getName())
                .subtitle(s.getAuthority() != null ? s.getAuthority().getName() : "Citizen Service")
                .snippet(s.getDescription())
                .category(s.getCategory() != null ? s.getCategory().getName() : "Public Service")
                .categoryColor("teal")
                .url("/services/" + s.getId())
                .officialSource(s.getOnlinePortalUrl())
                .lastVerified("Current")
                .relevanceScore(0.80)
                .build()));

        // Record Search History if user is authenticated
        if (user != null && !cleanQuery.isBlank()) {
            SearchHistory sh = SearchHistory.builder()
                    .user(user)
                    .queryText(cleanQuery)
                    .resultsCount(results.size())
                    .build();
            searchHistoryRepository.save(sh);
        }

        List<String> suggestions = List.of(
                "My employer has not paid salary",
                "Landlord not returning deposit",
                "Defective product return refund",
                "Online financial cyber fraud 1930"
        );

        return UpdateAndSearchDto.GlobalSearchResponse.builder()
                .query(cleanQuery)
                .totalResults((long) results.size())
                .results(results)
                .suggestions(suggestions)
                .isSemanticMatch(true)
                .build();
    }
}
