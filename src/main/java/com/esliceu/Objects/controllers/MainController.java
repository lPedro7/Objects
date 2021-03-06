package com.esliceu.Objects.controllers;

import com.esliceu.Objects.model.Bucket;
import com.esliceu.Objects.model.Obj;
import com.esliceu.Objects.services.BucketService;
import com.esliceu.Objects.services.ObjectService;
import com.esliceu.Objects.utils.Utils;
import jdk.internal.joptsimple.util.RegexMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.RegEx;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class MainController {

    @Autowired
    BucketService bucketService;

    @Autowired
    HttpSession session;

    @Autowired
    ObjectService objectService;

    @Autowired
    Utils utils;

    @GetMapping("/objects")
    public String main() {
        String username = (String) session.getAttribute("username");
        List<Bucket> buckets = bucketService.bucketsForUser(username);
        session.setAttribute("buckets", buckets);
        return "objects";
    }

    @PostMapping("/objects")
    public String newBucket(Model m,HttpServletResponse response,@RequestParam String name) {

        if (name.length()>1){
            if(!bucketService.newBucket(m,Utils.unaccent(name.replaceAll("\\s+","")))){
                return "/objects";
            }
        }else {
            m.addAttribute("message","El bucket ha de tenir un nom");
            return "/objects";
        }
        try {
            response.sendRedirect("/objects");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/objects";
    }

    @GetMapping("/objects/{bucket}")
    public String seeBucket(@PathVariable String bucket, Model m) {
        List<Obj> objs = objectService.objectsFromBucket(bucket);
        List<String> nomObjs = new ArrayList<>();
        String pattern = "^[\\/][^\\/]+";

        for (int i = 0; i < objs.size(); i++) {

            if (objs.get(i).getUri().contains("/")) {
                Pattern p = Pattern.compile(pattern);
                Matcher matcher = p.matcher(objs.get(i).getUri());
                List<String> directory = new ArrayList<>();
                while (matcher.find()) {
                    directory.add(matcher.group());
                }
                if (!nomObjs.contains(directory.get(0)) && !nomObjs.contains(directory.get(0) + "/")) {
                    nomObjs.add(directory.get(0));
                }
            } else {
                nomObjs.add(objs.get(i).getUri());
            }
        }

        session.setAttribute("nom", nomObjs);
        session.setAttribute("objs", objs);
        session.setAttribute("bucket", bucket);
        m.addAttribute("bucket", bucket);
        return "/seeBucket";
    }

    @PostMapping("/objects/{bucket}")
    public RedirectView newObject(Model m,@RequestParam String name, @RequestParam("file") MultipartFile file) {
        if (name.length()<1){
            name = file.getOriginalFilename();
        }else if (name.charAt(name.length()-1)=='/'){
            name+=file.getOriginalFilename();
        }

        objectService.newObject(m,(String) session.getAttribute("bucket"), Utils.unaccent(name.replaceAll("\\s+","")), file);
        return new RedirectView("/objects/"+session.getAttribute("bucket"));
    }

    @GetMapping("/objects/{bucket}/**")
    public String seeObjects(@PathVariable String bucket, HttpServletRequest request) {
        String obj = request.getRequestURI().split("/objects/" + bucket)[1];
        String lastPath = request.getRequestURI();
        String pattern = "[\\/][^\\/]+$";
        lastPath = lastPath.split(pattern)[0];
        session.setAttribute("lastPath",lastPath);
        if (objectService.getObject(bucket, obj) != null) {
            if (obj.equals(objectService.getObject(bucket, obj).getUri())) {
                List<Obj> allVersions = objectService.getAllVersions(bucket, obj);
                session.setAttribute("versions", allVersions);
                Obj object = objectService.getObject(bucket, obj);
                session.setAttribute("object", object);
                return "seeObjectInfo";
            }
        }

        List<String> objectsPath = objectService.getFolderPath(bucket, obj);
        String url = "/objects/" + bucket + obj;
        for (int i = 0; i < objectsPath.size(); i++) {
            objectsPath.set(i, objectsPath.get(i).replace(obj, ""));
            if (objectsPath.get(i).contains("/")) objectsPath.set(i, objectService.firstPath(objectsPath.get(i)));
        }

        Set<String> objectsPathSet = new LinkedHashSet<>(objectsPath);

        String currentUrl = url.replaceAll("^/objects","");
        session.setAttribute("currentUrl",currentUrl);
        session.setAttribute("url", url);
        session.setAttribute("objectsPath", objectsPathSet);

        return "seeObject";
    }

    @PostMapping("/download/{id}")
    public String download(HttpServletResponse resp,@PathVariable int id){
        objectService.download(resp,id);
        return "seeObjectInfo";
    }

    @PostMapping("/delete/{id}")
    public RedirectView delete(@PathVariable int id){
        objectService.deleteObject(id);
        return new RedirectView("/objects");
    }

    @PostMapping("/deleteObj/{id}")
    public RedirectView deleteObj(@PathVariable int id){
        Obj obj = objectService.getObject(id);
        objectService.deleteObject(obj.getBucketUri(),obj.getUri());
        return new RedirectView("/objects");
    }

    @PostMapping("/deleteBucket/{bucket}")
    public RedirectView deleteBucket(@PathVariable String bucket){
        bucketService.deleteBucket(bucket);
        return new RedirectView("/objects");
    }

}
