/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unu.jogja.project.ktp.coba;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author ASUS
 */
@Controller
public class DummyController {
    
    DummyJpaController dummyController = new DummyJpaController();
    List<Dummy> data = new ArrayList<>();
    
    
    @RequestMapping("/read")
    public String getDummy (Model model) {
    int record = dummyController.getDummyCount();
//        String result = "";
        try{
            data = dummyController.findDummyEntities().subList(0, record);
        }
        catch (Exception e){
//            result=e.getMessage();
        }
        
        model.addAttribute("goDummy", data);
         model.addAttribute("record", record);
         
        return "dummy";    
    }
    
    @RequestMapping("/create")
    public String createDummy(){
        
        return "dummy/create";
    }
     @PostMapping(value="/newdata", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
    public String newDummy(HttpServletRequest data,@RequestParam("foto") MultipartFile file) throws ParseException, Exception{
        Dummy dumdata = new Dummy();
        
        String id = data.getParameter("id");
        int iid = Integer.parseInt(id);
        String nama = data.getParameter("nama");
        String tanggal = data.getParameter("tanggal");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal);
        byte[] image = file.getBytes();
        dumdata.setId(iid);
        dumdata.setNama(nama);
        dumdata.setTanggal(date);
        dumdata.setFoto(image);
        dummyController.create(dumdata);
        
        return "redirect:/read";
    }
     @RequestMapping(value = "/image", method = RequestMethod.GET, produces = {
       MediaType.IMAGE_JPEG_VALUE})
    public ResponseEntity<byte[]> getImg(@RequestParam("id") int id) throws Exception {
        Dummy dum = dummyController.findDummy(id);
        byte[] foto = dum.getFoto();
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(foto);
    }
    
    @GetMapping("/delete/{id}")
    public String deletedummy(@PathVariable("id") int id) throws Exception {
        dummyController.destroy(id);
        return "redirect:/read";
    }
    @GetMapping("/edit/{id}")
    public String editDummy(Model model, @PathVariable("id") int id) throws Exception{
        Dummy dum = dummyController.findDummy(id);
        model.addAttribute("godummy", dum);
        return "dummy/edit";
    }
    @PostMapping(value="/newedit", consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
     public String updateDummy(HttpServletRequest data,@RequestParam("foto") MultipartFile file) throws ParseException, Exception{
        Dummy dumdata = new Dummy();
        
        String id = data.getParameter("id");
        int iid = Integer.parseInt(id);
        String nama = data.getParameter("nama");
        String tanggal = data.getParameter("tanggal");
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal);
        byte[] image = file.getBytes();
        dumdata.setId(iid);
        dumdata.setNama(nama);
        dumdata.setTanggal(date);
        dumdata.setFoto(image);
        dummyController.edit(dumdata);
        
        return "redirect:/read";
    }
}
