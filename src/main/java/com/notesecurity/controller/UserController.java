package com.notesecurity.controller;

import com.notesecurity.database.entity.Note;
import com.notesecurity.service.NoteService;
import com.notesecurity.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RequestMapping("/user")
@Controller
@Validated
public class UserController {
//    private UserService userService;
//    @Autowired
//    public UserController(UserService userService){
//        this.userService = userService;
//    }
//    @GetMapping("/list")
//    public ModelAndView get(){
//        ModelAndView result = new ModelAndView("user/list");
//        result.addObject("users", userService.listAll());
//        return result;
//    }
    private NoteService noteService;
    @Autowired
    public UserController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("/list")
    public ModelAndView noteList() {
        ModelAndView result = new ModelAndView("user/list");
        result.addObject("notes", noteService.listAll());
        return result;
    }
    @PostMapping("/delete")
    public String deleteNoteById(
            @Valid
            @NotNull @RequestParam("id") UUID id) {
        noteService.deleteById(id);
        return "redirect:/user/list";
    }
    @GetMapping("/edit")
    public ModelAndView editNoteById(
            @Valid
            @NotNull @RequestParam("id") UUID id) throws Exception {
        ModelAndView result = new ModelAndView("user/edit");
        result.addObject("note", noteService.getById(id));
        return result;
    }
    @PostMapping("/edit")
    public ModelAndView updateNote(
            @Valid
            @NotNull @RequestParam(value="id") UUID id,
            @NotNull @RequestParam(value="title") String title,
            @RequestParam(value="content") String content) {
        Note note = Note.builder()
                .id(id)
                .title(title)
                .content(content)
                .build();
        noteService.update(note);
        return noteList();
    }
}
