package com.jtampakakis.bluemaster.savelogs.api;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jtampakakis.bluemaster.savelogs.Startup;
import com.jtampakakis.bluemaster.savelogs.Template;
import com.jtampakakis.bluemaster.savelogs.repos.TemplateRepo;
import com.jtampakakis.bluemaster.savelogs.security.config.Hardware;
import com.netflix.config.validation.ValidationException;


@Controller
@RequestMapping(path = "/templates")
public class TemplateAPI {
	
	@Autowired
	private TemplateRepo repo;
	
	private final static Logger LOGGER = Startup.LOGGER;
	
	
	@PreAuthorize("@preAuthorize.hasAccess('hardware',#id)")
	@GetMapping(path = "/hardware/{id}", produces = { "application/json" })
	public @ResponseBody List<Template> readTemplates(@PathVariable int id) {
		LOGGER.info("GET /template/templates/hardware/{id}: Requested templates hardware ID " + id);
		Hardware h =new Hardware(id);
		h.ValidateID();
		return repo.findByHardware(h);
	}
	
	@PreAuthorize("@preAuthorize.hasAccess('hardware',#o.hardware.id)")
	@PostMapping(path = "", produces = { "application/json" }, consumes = "application/json")
	public @ResponseBody Template create(@RequestBody Template t) throws JsonProcessingException {
		LOGGER.info("POST /templates/template: Requested to save Template");
		t.ValidateCreation(repo);
		return t.Create(repo);
	}
	
	@PreAuthorize("@preAuthorize.hasAccess('hardware',#hardwareId)")
	@PutMapping(path = "/{hardwareId}/{id}", produces = { "application/json" }, consumes = "application/json")
	public @ResponseBody Template update(@PathVariable int hardwareId,@PathVariable int id,@RequestBody Template t) throws JsonProcessingException {
		LOGGER.info("PUT /templates/template/{hardwareId}/{id}: Requested to update Template with id "+id);
		t.setId(id);
		t.ValidateUpdate(repo);
		return t.Update(repo);
	}
	
	@PreAuthorize("@preAuthorize.hasAccess('hardware',#hardwareId)")
	@DeleteMapping(path = "/{hardwareId}/{id}", produces = { "application/json" })
	public @ResponseBody Template delete(@PathVariable int hardwareId,@PathVariable int id) throws JsonProcessingException {
		LOGGER.info("DELETE /templates/template/{hardwareId}/{id}: Requested to delete Template with ID " + id);
		Template t = new Template(id);
		t.ValidateId();
		Template temp = repo.findById(id);
		if (temp==null) throw new ValidationException("Unable to find Template");
		return temp.Delete(repo);
	}

}
