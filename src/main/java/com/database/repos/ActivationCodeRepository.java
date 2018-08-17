package com.database.repos;

import com.database.entites.ActivationCode;
import org.springframework.data.repository.CrudRepository;

public interface ActivationCodeRepository extends CrudRepository<ActivationCode, Long> {
    ActivationCode findByCode(String code);
}
