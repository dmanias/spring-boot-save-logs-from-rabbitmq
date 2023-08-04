package com.jtampakakis.bluemaster.savelogs.repos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jtampakakis.bluemaster.savelogs.Template;
import com.jtampakakis.bluemaster.savelogs.security.config.Hardware;


@Repository
public interface TemplateRepo extends CrudRepository<Template, Integer>{
	Template findById(int id);
	List<Template> findByHardware(Hardware hardware);

}
