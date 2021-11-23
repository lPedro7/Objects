package com.esliceu.Objects.controllers;

import com.esliceu.Objects.model.Obj;
import com.esliceu.Objects.services.ObjectService;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

@Controller
public class ObjectController {

    @Autowired
    HttpSession session;

    @Autowired
    ObjectService objectService;

    @GetMapping("/private/objects/{bucket}/newObject")
    public String newObject() {
        return "newObject";
    }

    @PostMapping("/private/objects/{bucket}/newObject")
    public String newObject(@RequestParam String name, @RequestParam("file") MultipartFile file) {

        objectService.newObject((String) session.getAttribute("bucket"), name, file);

        return "newObject";
    }

    @GetMapping("/private/objects/{bucket}/{obj}/descarrega")
    public String download(HttpServletRequest req, HttpServletResponse resp, @PathVariable String bucket, @PathVariable String obj) {

        System.out.println("Download");
        System.out.println(obj);
        System.out.println(bucket);

        Obj object = objectService.getObject(bucket, obj);

        File f = new File(objectService.getObjName(bucket, obj));

        try {
            Files.write(object.getContent(), f);
        } catch (IOException e) {
            e.printStackTrace();
        }

        resp.setContentType("application/octet-stream");
        resp.setHeader("Content-disposition", "attachment; filename="+objectService.getObjName(bucket,obj));


        try(InputStream in = new FileInputStream(f);
            OutputStream out = resp.getOutputStream()) {

            byte[] buffer = new byte[object.getContent().length];

            System.out.println(object.getContent());
            System.out.println(object.getContent().length);
            System.out.println(object.getContentLength());
            System.out.println(buffer.length);

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return "descarrega";
    }
}
