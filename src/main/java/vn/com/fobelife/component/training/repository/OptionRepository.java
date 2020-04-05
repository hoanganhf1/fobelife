package vn.com.fobelife.component.training.repository;

import org.springframework.data.repository.CrudRepository;

import vn.com.fobelife.component.training.entity.Option;

public interface OptionRepository extends CrudRepository<Option, Long> {

    Option findByCode(String code);
}
