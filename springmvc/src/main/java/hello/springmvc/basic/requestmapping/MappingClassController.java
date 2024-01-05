package hello.springmvc.basic.requestmapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

    @GetMapping
    public String users(){
        return "get users";
    }

    @PostMapping
    public String postUsers(){
        return "post users";
    }

    @GetMapping("/{userId}")
    public String user(@PathVariable String userId){
        return "get userId=" + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String id){
        return "update userId= " + id;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete userId=" + userId;
    }



}
