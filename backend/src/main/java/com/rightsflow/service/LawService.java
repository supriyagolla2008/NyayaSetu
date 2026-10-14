package com.rightsflow.service;

import com.rightsflow.dto.LegalDto;
import com.rightsflow.entity.Law;
import com.rightsflow.entity.LawProvision;
import com.rightsflow.entity.LegalSource;
import com.rightsflow.exception.ResourceNotFoundException;
import com.rightsflow.repository.LawProvisionRepository;
import com.rightsflow.repository.LawRepository;
import com.rightsflow.repository.LegalSourceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LawService {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(LawService.class);

    public LawService(LawRepository lawRepository, LawProvisionRepository provisionRepository, LegalSourceRepository sourceRepository) {
        this.lawRepository = lawRepository;
        this.provisionRepository = provisionRepository;
        this.sourceRepository = sourceRepository;
    }



    private final LawRepository lawRepository;
    private final LawProvisionRepository provisionRepository;
    private final LegalSourceRepository sourceRepository;

    @Transactional(readOnly = true)
    public Page<LegalDto.LawDto> searchLaws(String query, String status, Pageable pageable) {
        if (query != null && !query.trim().isEmpty()) {
            return lawRepository.searchLaws(query.trim(), status, pageable).map(this::mapToDto);
        }
        return lawRepository.findFiltered(status, pageable).map(this::mapToDto);
    }

    @Transactional(readOnly = true)
    public LegalDto.LawDto getLawById(Long id) {
        Law law = lawRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Law", "id", id));
        return mapToDto(law);
    }

    @Transactional
    public LegalDto.LawDto createLaw(LegalDto.LawDto dto) {
        LegalSource source = null;
        if (dto.getSourceId() != null) {
            source = sourceRepository.findById(dto.getSourceId()).orElse(null);
        }

        Law law = Law.builder()
                .title(dto.getTitle())
                .shortCode(dto.getShortCode())
                .year(dto.getYear())
                .enactmentDate(dto.getEnactmentDate())
                .jurisdiction(dto.getJurisdiction() != null ? dto.getJurisdiction() : "India")
                .description(dto.getDescription())
                .source(source)
                .officialLink(dto.getOfficialLink())
                .status(dto.getStatus() != null ? dto.getStatus() : "ACTIVE")
                .build();

        Law saved = lawRepository.save(law);
        return mapToDto(saved);
    }

    private LegalDto.LawDto mapToDto(Law l) {
        List<LegalDto.ProvisionDto> provisions = l.getProvisions() != null ? l.getProvisions().stream()
                .map(p -> LegalDto.ProvisionDto.builder()
                        .id(p.getId())
                        .lawId(l.getId())
                        .lawTitle(l.getTitle())
                        .sectionNumber(p.getSectionNumber())
                        .title(p.getTitle())
                        .simpleSummary(p.getSimpleSummary())
                        .fullText(p.getFullText())
                        .penaltyOrRemedy(p.getPenaltyOrRemedy())
                        .build()).collect(Collectors.toList()) : List.of();

        return LegalDto.LawDto.builder()
                .id(l.getId())
                .title(l.getTitle())
                .shortCode(l.getShortCode())
                .year(l.getYear())
                .enactmentDate(l.getEnactmentDate())
                .jurisdiction(l.getJurisdiction())
                .description(l.getDescription())
                .sourceId(l.getSource() != null ? l.getSource().getId() : null)
                .sourceName(l.getSource() != null ? l.getSource().getName() : "")
                .officialLink(l.getOfficialLink())
                .status(l.getStatus())
                .provisions(provisions)
                .build();
    }
}
