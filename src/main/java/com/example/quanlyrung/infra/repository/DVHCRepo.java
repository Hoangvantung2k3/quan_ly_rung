package com.example.quanlyrung.infra.repository;

import com.example.quanlyrung.infra.entity.DVHC;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DVHCRepo extends JpaRepository<DVHC, Long> {

    public List<DVHC> findAllByLevelEqualsIgnoreCaseAndParentId(String level, Long parentId);

    public List<DVHC> findAllByLevelEqualsIgnoreCase(String level);
    public DVHC getDVHCById(Long id);

    public void deleteDVHCById(Long id);

    public List<DVHC> getDVHCSByParentId(Long id);

//    @Query("select h from DVHC h where h.name LIKE %?1% and h.level = ?2 and h.parent.id = ?3")
//    public List<DVHC> findAllWithKeyword(String keyword, String level, Long id);

    public List<DVHC> findAllByNameContainingIgnoreCaseAndLevelAndParentId(String keyword, String level, Long id);

}
