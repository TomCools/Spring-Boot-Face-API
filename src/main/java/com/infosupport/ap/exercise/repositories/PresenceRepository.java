package com.infosupport.ap.exercise.repositories;

import com.infosupport.ap.exercise.models.Presence;
import org.springframework.data.repository.CrudRepository;

public interface PresenceRepository extends CrudRepository<Presence, Long> {
}
