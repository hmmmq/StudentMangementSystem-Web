package com.niit.controller;

import com.niit.pojo.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author mayang
 */
@Controller
public class exampleController {

    @RequestMapping("administrator")
    public ModelAndView administrator(ModelAndView mv){
        mv.setViewName("administrator");
        return  mv;
    }

    @RequestMapping("administratoradd")
    public ModelAndView administratoradd( ModelAndView mv){

         mv.setViewName("administratoradd");
        return  mv;
    }
    @RequestMapping("administratorchange")
    public ModelAndView administratorchange(Student student){
         ModelAndView mv=new ModelAndView();
         mv.addObject(student);
         mv.setViewName("administratorchange");
        return  mv;
    }
    @RequestMapping("administratorcheck")
    public ModelAndView administratorcheck(ModelAndView mv){
        mv.setViewName("students");
        return  mv;
    }
    @RequestMapping("index")
    public ModelAndView showindex(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("index");
        return  mv;
    }
   @RequestMapping("students")
    public ModelAndView students(ModelAndView mv){
      mv.setViewName("students");
        return  mv;
    }
    @RequestMapping("studentsmodify")
    public ModelAndView studentsmodify(ModelAndView mv){

        mv.setViewName("studentsmodify");
        return  mv;
    }
}
