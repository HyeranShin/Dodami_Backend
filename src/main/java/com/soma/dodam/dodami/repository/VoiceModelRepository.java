package com.soma.dodam.dodami.repository;

import com.soma.dodam.dodami.domain.VoiceModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VoiceModelRepository extends JpaRepository<VoiceModel, Long> {

    VoiceModel findByIdx(Long idx);

    List<VoiceModel> findByUserIdx(Long idx);

    Optional<VoiceModel> findByUserIdxAndName(Long idx, String name);

    Optional<VoiceModel> findByUserIdxAndIdx(Long userIdx, Long idx);
}
