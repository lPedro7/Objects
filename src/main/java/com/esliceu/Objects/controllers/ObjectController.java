package com.esliceu.Objects.controllers;

import com.esliceu.Objects.model.Obj;
import com.esliceu.Objects.services.ObjectService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ObjectController {

    @Autowired
    HttpSession session;

    @Autowired
    ObjectService objectService;

    @GetMapping("/private/objects/{uri}/newObject")
    public String newObject(){
        return "newObject";
    }

    @PostMapping("/private/objects/{uri}/newObject")
    public String newObject(@RequestParam String name, @RequestParam("file") MultipartFile file){

        objectService.newObject((String) session.getAttribute("uri"),name,file);

        return "newObject";
    }

    @GetMapping("/private/objects/{uri}/descarrega")
    public String download(Model m, @PathVariable String uri){


            Obj obj = objectService.getObject(uri);

            File f = new File(objectService.getObjName(uri));



        return "download";
    }

}
