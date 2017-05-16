package nl.bioinf.idatabase.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by dvandeveerdonk on 15-5-17.
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/{locale}/upload")
    public String uploadpage(){
    return "upload";
    }

    @PostMapping(value = "/{locale}/uploadfiles")
    public String fileUpload(@RequestParam("file") MultipartFile[] files){
        for(MultipartFile file:files){
            System.out.println(file.getOriginalFilename());
        }

        return "redirect:/";
    }
}
