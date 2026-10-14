package com.rightsflow.service;

import com.rightsflow.dto.LegalDto;
import com.rightsflow.entity.Authority;
import com.rightsflow.entity.LegalService;
import com.rightsflow.exception.ResourceNotFoundException;
import com.rightsflow.repository.AuthorityRepository;
import com.rightsflow.repository.LegalServiceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorityService {
    public AuthorityService(AuthorityRepository authorityRepository, LegalServiceRepository serviceRepository) {
        this.authorityRepository = authorityRepository;
        this.serviceRepository = serviceRepository;
    }



    private final AuthorityRepository authorityRepository;
    private final LegalServiceRepository serviceRepository;

    @Transactional(readOnly = true)
    public Page<LegalDto.AuthorityDto> searchAuthorities(String query, Pageable pageable) {
        if (query != null && !query.trim().isEmpty()) {
            return authorityRepository.searchAuthorities(query.trim(), pageable).map(this::mapToDto);
        }
        return authorityRepository.findFiltered(pageable).map(this::mapToDto);
    }

    @Transactional(readOnly = true)
    public Page<LegalDto.ServiceDto> searchServices(String query, Long categoryId, Pageable pageable) {
        if (query != null && !query.trim().isEmpty()) {
            return serviceRepository.searchServices(query.trim(), categoryId, pageable).map(this::mapServiceToDto);
        }
        return serviceRepository.findFiltered(categoryId, pageable).map(this::mapServiceToDto);
    }

    @Transactional(readOnly = true)
    public LegalDto.AuthorityDto getAuthorityById(Long id) {
        Authority auth = authorityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Authority", "id", id));
        return mapToDto(auth);
    }

    @Transactional(readOnly = true)
    public LegalDto.ServiceDto getServiceById(Long id) {
        LegalService s = serviceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("LegalService", "id", id));
        return mapServiceToDto(s);
    }

    private LegalDto.AuthorityDto mapToDto(Authority a) {
        List<LegalDto.ServiceDto> services = a.getServices() != null ? a.getServices().stream()
                .map(this::mapServiceToDto).collect(Collectors.toList()) : Collections.emptyList();

        return LegalDto.AuthorityDto.builder()
                .id(a.getId())
                .name(a.getName())
                .jurisdictionLevel(a.getJurisdictionLevel())
                .contactPhone(a.getContactPhone())
                .contactEmail(a.getContactEmail())
                .officialWebsite(a.getOfficialWebsite())
                .physicalAddress(a.getPhysicalAddress())
                .description(a.getDescription())
                .services(services)
                .build();
    }

    private LegalDto.ServiceDto mapServiceToDto(LegalService s) {
        List<LegalDto.StepDto> steps = s.getSteps() != null ? s.getSteps().stream()
                .map(st -> LegalDto.StepDto.builder()
                        .stepNumber(st.getStepNumber())
                        .title(st.getTitle())
                        .instruction(st.getInstruction())
                        .estimatedDuration(st.getEstimatedDuration())
                        .build()).collect(Collectors.toList()) : Collections.emptyList();

        List<LegalDto.DocRequirementDto> docs = s.getRequiredDocuments() != null ? s.getRequiredDocuments().stream()
                .map(d -> LegalDto.DocRequirementDto.builder()
                        .documentName(d.getDocumentName())
                        .isMandatory(d.getIsMandatory())
                        .description(d.getDescription())
                        .build()).collect(Collectors.toList()) : Collections.emptyList();

        return LegalDto.ServiceDto.builder()
                .id(s.getId())
                .authorityId(s.getAuthority() != null ? s.getAuthority().getId() : null)
                .authorityName(s.getAuthority() != null ? s.getAuthority().getName() : "")
                .categoryId(s.getCategory() != null ? s.getCategory().getId() : null)
                .categoryName(s.getCategory() != null ? s.getCategory().getName() : "")
                .name(s.getName())
                .description(s.getDescription())
                .eligibilityCriteria(s.getEligibilityCriteria())
                .onlinePortalUrl(s.getOnlinePortalUrl())
                .feeDetails(s.getFeeDetails())
                .processingTimeEstimate(s.getProcessingTimeEstimate())
                .steps(steps)
                .requiredDocuments(docs)
                .build();
    }
}
