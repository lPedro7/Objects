package com.esliceu.Objects.controllers;

import com.esliceu.Objects.model.Bucket;
import com.esliceu.Objects.model.Obj;
import com.esliceu.Objects.services.BucketService;
import com.esliceu.Objects.services.ObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    BucketService bucketService;

    @Autowired
    HttpSession session;

    @Autowired
    ObjectService objectService;

    @GetMapping("/private/objects")
    public String main(){
        String username = (String) session.getAttribute("username");
        List<Bucket> buckets = bucketService.bucketsForUser(username);
        session.setAttribute("buckets",buckets);

        return "objects";
    }

    @GetMapping("/private/objects/newBucket")
    public String newBucket(){
        return "newBucket";
    }

    @GetMapping("/private/objects/{uri}")
    public String seeBucket(@PathVariable String uri,Model m){

        List<Obj> objs = objectService.getObjects();
        session.setAttribute("objs",objs);
        session.setAttribute("uri",uri);
        m.addAttribute("bucket",uri);
        return "seeBucket";
    }

    @PostMapping("/private/objects/newBucket")
    public RedirectView newBucket(@RequestParam String name){
        if (bucketService.newBucket(name)){
            return new RedirectView("/private/objects");
        }else{
            return new RedirectView("/private/newBucket");
        }
    }




}
