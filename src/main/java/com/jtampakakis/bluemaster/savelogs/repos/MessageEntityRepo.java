package com.jtampakakis.bluemaster.savelogs.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jtampakakis.bluemaster.savelogs.entities.MessageEntity;
import java.util.Optional;

@Repository
public interface MessageEntityRepo extends JpaRepository<MessageEntity, Long>{
    Optional<MessageEntity> findById(Long id);
}
