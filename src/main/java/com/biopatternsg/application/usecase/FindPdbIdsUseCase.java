package com.biopatternsg.application.usecase;

import com.biopatternsg.domain.ports.in.FindPdbIds;
import com.biopatternsg.domain.ports.out.PdbRepository;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@ApplicationScoped
public class FindPdbIdsUseCase implements FindPdbIds {

    private final PdbRepository pdbRepository;

    @Override
    public Object execute(String symbol) {
        return pdbRepository.findPdbIds(symbol);
    }
}
