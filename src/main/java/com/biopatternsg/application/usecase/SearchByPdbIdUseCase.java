package com.biopatternsg.application.usecase;

import com.biopatternsg.domain.ports.in.SearchByPdbId;
import com.biopatternsg.domain.ports.out.PdbRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ApplicationScoped
public class SearchByPdbIdUseCase implements SearchByPdbId {

    private final PdbRepository pdbRepository;

    @Override
    public Object execute(String pdbId) {
        return pdbRepository.searchByPdbId(pdbId);
    }
}
