package com.biopatternsg.domain.ports.out;

public interface PdbRepository {

    Object findPdbIds(String symbol);

    Object searchByPdbId(String pdbId);

}
