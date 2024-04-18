package kr.ac.kopo.webapplication.controller;

import kr.ac.kopo.webapplication.dto.SampleDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Controller
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/ex1")
    public void ex(){

    }

    @GetMapping({"/ex2", "/exBlock", "/exLink"})
    public void exModel(Model model){
        List<SampleDTO> list = IntStream.rangeClosed(1,20).asLongStream().mapToObj(i -> {
            SampleDTO dto = SampleDTO.builder()
                    .sno(i)
                    .first("First..."+i)
                    .last("Last..."+i)
                    .regTime(LocalDateTime.now())
                    .build();
            return dto;
        }).collect(Collectors.toList());

        model.addAttribute("list", list);
    }

    @GetMapping("/exInline")
    public String exInline(RedirectAttributes redirectAttributes){
        SampleDTO dto = SampleDTO.builder()
                .sno(100L)
                .first("first...100")
                .last("last...100")
                .regTime(LocalDateTime.now())
                .build();

        redirectAttributes.addFlashAttribute("result", "Success");
        redirectAttributes.addFlashAttribute("dto", dto);

        return "redirect:/sample/ex3";
    }

    @GetMapping({"/ex3", "/exLayout1", "/exLayout2"})
    public void ex3(){
        log.info("ex3");
    }

}
