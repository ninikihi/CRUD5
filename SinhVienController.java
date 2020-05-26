package com.dts.studentManager.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dts.studentManager.dto.SinhVienDTO;
import com.dts.studentManager.entity.SinhVienEntity;
import com.dts.studentManager.form.SearchForm;
import com.dts.studentManager.service.SinhVienService;

@Controller
@RequestMapping("/sinhvien")
public class SinhVienController {

    @Autowired
    private SinhVienService sinhVienService;

    // init search
    @RequestMapping(value="/list" , method = RequestMethod.GET)
    public String listSinhVien(Model theModel) {
		List<SinhVienDTO> SinhViens = sinhVienService.getSinhViens(new SearchForm());
        theModel.addAttribute("sinhviens", SinhViens);
        return "listSinhVien";
    }

    // do search
    @RequestMapping(value = "/searchSinhVien" , method = RequestMethod.POST)
    public String searchSinhVien(SearchForm searchForm, Model theModel ,HttpServletRequest request) {
    	List<SinhVienDTO> SinhViens = sinhVienService.getSinhViens(searchForm);
        theModel.addAttribute("sinhviens", SinhViens);
        return "listSinhVien";
    }

    // init add
    @RequestMapping(value = "/add" , method = RequestMethod.GET)
    public String addSinhVien(Model theModel) {
        SinhVienEntity SinhVien = new SinhVienEntity();
        theModel.addAttribute("sinhvien", SinhVien);
        return "addSinhVien";
    }

    @RequestMapping(value = "/saveSinhVien" , method = RequestMethod.POST)
    public String saveSinhVien(@ModelAttribute("sinhvien") SinhVienEntity SinhVien) {
    	sinhVienService.saveSinhVien(SinhVien);
        return "redirect:/sinhvien/list";
    }

    @RequestMapping(value = "/updateSinhVien" , method = RequestMethod.GET)
    public String updateSinhVien(@RequestParam("sinhvienid") int theId,
        Model theModel) {
        SinhVienEntity SinhVien = sinhVienService.getSinhVien(theId);
        theModel.addAttribute("sinhvien", SinhVien);
        return "updateSinhVien";
    }

    @RequestMapping(value = "/deleteSinhVien" , method = RequestMethod.GET)
    public String deleteSinhVien(@RequestParam("sinhvienid") int theId) {
    	sinhVienService.deleteSinhVien(theId);
        return "redirect:/sinhvien/list";
    }
}