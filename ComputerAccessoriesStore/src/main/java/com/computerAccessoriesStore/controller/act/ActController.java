package com.computerAccessoriesStore.controller.act;

import com.computerAccessoriesStore.models.Act;
import com.computerAccessoriesStore.service.ActService;
import com.computerAccessoriesStore.transfer.ActDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/act")
public class ActController {

    @Autowired
    private ActService actService;

    @GetMapping(value = "/showAct")
    public String findAct(@RequestParam(value = "search", required = false, defaultValue = "") Long id, Model model) {
        List<Act> acts = actService.findAll();
        model.addAttribute("result", acts);
        System.out.println(acts.get(0).getId().toString());
        //model.addAttribute("search", name);
        return "admin/act/showAct";
    }

    @GetMapping("/addAct")
    public String addAct() {
        return "admin/act/addAct";
    }

    @PostMapping("/addAct")
    public String addAct(@ModelAttribute ActDTO dto, BindingResult errors, Model model) {
        dto.setCreated_at(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
        actService.add(dto);
        return "redirect:/act/showAct";
    }

    @GetMapping(value = "/deleteAct")
    public String deleteAct(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id != null) {
            actService.deleteById(id);
        }
        return "redirect:/act/showAct";
    }

    @GetMapping(value = "/editAct")
    public String editAct(@RequestParam(value = "id", required = true) Long id, Model model) {
        Optional<Act> dto = actService.getById(id);
        ActDTO actDTO = ActDTO.builder()
                .id(dto.get().getId())
                .count(dto.get().getCount())
                .created_at(dto.get().getCreated_at())
                .idBuyer(dto.get().getBuyer().getId())
                .idSeller(dto.get().getSeller().getId())
                .idProduct(dto.get().getProduct().getId()).build();
        model.addAttribute("act", actDTO);
        return "admin/act/editAct";
    }

    @RequestMapping(value = "/editAct", method = RequestMethod.POST)
    public String editAct(@ModelAttribute ActDTO dto, BindingResult errors, Model model) throws Exception {
        actService.edit(dto);
        return "redirect:/act/showAct";
    }
}
