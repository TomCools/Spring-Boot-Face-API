package com.infosupport.ap.exercise.resources;

import com.infosupport.ap.exercise.models.ClassRegistration;
import com.infosupport.ap.exercise.models.Presence;
import com.infosupport.ap.exercise.repositories.ClassRegistrationRepository;
import com.infosupport.ap.exercise.repositories.PresenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class ClassesResource {

    private ClassRegistrationRepository classRepository;
    private PresenceRepository presenceRepository;

    @Autowired
    public ClassesResource(ClassRegistrationRepository classRepository, PresenceRepository presenceRepository) {
        this.classRepository = classRepository;
        this.presenceRepository = presenceRepository;
    }

    @RequestMapping(value = "/api/classes", method = RequestMethod.POST, consumes = "application/json")
    public void addClass(@RequestBody @Valid ClassRegistration body) {
        classRepository.save(body);
    }

    @RequestMapping(value = "/api/classes/{id}", method = RequestMethod.PUT, consumes = "application/json")
    public void update(@PathVariable Long id, @RequestBody @Valid ClassRegistration body) {
        body.setId(id);
        classRepository.save(body);
    }

    @RequestMapping(value = "/api/classes", method = RequestMethod.GET)
    public Iterable<ClassRegistration> getAllClasses() {
        return classRepository.findAll();
    }

    @RequestMapping(value = "/api/classes/{id}", method = RequestMethod.GET)
    public ClassRegistration getClassDetails(@PathVariable Long id) {
        return classRepository.findById(id).orElseThrow(() -> new RuntimeException("Couldn't find entity with id."));
    }

    //Note: This one can also be in a seperate PresenceResource.class
    @RequestMapping(value = "/api/classes/{id}/presence", method = RequestMethod.GET)
    public Iterable<Presence> getPresences(@PathVariable("id") Long classId) {
        //Note: Instead of using Java streams, you could also create a findByClassId method on the repository
        return StreamSupport.stream(presenceRepository.findAll().spliterator(), false)
                .filter(p -> classId.equals(p.getClassId()))
                .collect(Collectors.toList());
    }
}
