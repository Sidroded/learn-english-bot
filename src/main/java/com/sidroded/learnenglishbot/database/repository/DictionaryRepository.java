package com.sidroded.learnenglishbot.database.repository;

import com.sidroded.learnenglishbot.database.entity.Dictionary;
import org.springframework.data.repository.CrudRepository;

public interface DictionaryRepository extends CrudRepository<Dictionary, String> {
}
