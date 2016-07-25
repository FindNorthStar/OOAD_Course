package sse.bupt;

import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import sse.bupt.domain.*;
import sse.bupt.service.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Java on 2016/5/28.
 */
@Controller
public class HelloController {

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index.action")
    public String index(){
        return "index";
    }

    @RequestMapping(value = "/register.action",method = RequestMethod.POST)
    public String register(){
        return "register";
    }

    @RequestMapping(value = "/registerUser.action",method = RequestMethod.POST)
    public String registerUser(HttpServletRequest request){
        //get name of attribute from http request
        int userId = Integer.parseInt(request.getParameter("userId"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String identity = request.getParameter("identity");

        //create a new user object
        User user = new User(userId,username,password,identity);

        //insert into database
        userService.insert(user);

        //back to index
        return "index";
    }

    @RequestMapping(value = "/login.action",method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request){
        int userId = Integer.parseInt(request.getParameter("userId"));
        String password = request.getParameter("password");

        //fetch user
        User user = userService.getUser(userId);

        if (user!=null){
            if (user.getPassword().equals(password)){
                //password success
                final Map<String, Object> model = new LinkedHashMap<String, Object>();
                if (user.getIdentity().equals("student")){
                    Student student = studentService.getStudent(user.getId());
                    model.put("user", user);
                    model.put("student",student);

                    request.getSession().setAttribute("user",user);
                    request.getSession().setAttribute("student",student);

                    return new ModelAndView("student",model);
                }
                else if (user.getIdentity().equals("teacher")){

                    Teacher teacher = teacherService.getTeacher(user.getId());

                    model.put("user", user);
                    model.put("teachlesson",teacher.getLesson());
                    //put teacher object

                    request.getSession().setAttribute("user",user);
                    request.getSession().setAttribute("teachlesson",teacher.getLesson());

                    return new ModelAndView("teacher",model);
                }
                else if (user.getIdentity().equals("admin")){
                    //get userList

                    List<User> userList = userService.getAllUser();
                    List<Classroom> roomList = classroomService.getAllClassroom();
                    List<Lesson> lessonList = lessonService.getAllLesson();

                    model.put("adminNum", user.getId());
                    model.put("adminName", user.getUsername());
                    model.put("userList",userList);

                    request.getSession().setAttribute("adminNum",user.getId());
                    request.getSession().setAttribute("adminName",user.getUsername());
                    request.getSession().setAttribute("userList",userList);
                    request.getSession().setAttribute("roomList",roomList);
                    request.getSession().setAttribute("lessonList",lessonList);

                    return new ModelAndView("admin",model);
                }
            }
            else{
                //password fail
                return new ModelAndView("index");
            }
        }

        return new ModelAndView("index");

    }

    @RequestMapping(value = "/modifyuser.action",method = RequestMethod.POST)
    public ModelAndView modifyuser(HttpServletRequest request){
        //get userList
        List<User> existUserList = (List<User>) request.getSession().getAttribute("userList");

        String modifyUserNum[] = new String[100];
        String modifyUserName[] = new String[100];
        String modifyUserPassword[] = new String[100];
        String modifyUserIdentity[] = new String[100];

        for (int i=1;i<=existUserList.size();i++){
            //get parameter
            modifyUserNum[i] = request.getParameter("number"+i);
            if (modifyUserNum[i]==null){
                continue;
            }
            modifyUserName[i] = request.getParameter("name"+i);
            modifyUserPassword[i] = request.getParameter("password"+i);
            modifyUserIdentity[i] = request.getParameter("identity"+i);

            existUserList.get(i-1).setId(Integer.parseInt(modifyUserNum[i]));
            existUserList.get(i-1).setUsername(modifyUserName[i]);
            existUserList.get(i-1).setPassword(modifyUserPassword[i]);
            existUserList.get(i-1).setIdentity(modifyUserIdentity[i]);

            //update
            userService.insert(existUserList.get(i-1));
        }

        List<User> updateUserList = userService.getAllUser();
        request.getSession().setAttribute("userList",updateUserList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("userList",(List<User>) request.getSession().getAttribute("userList"));

        return new ModelAndView("admin",model);

    }

    @RequestMapping(value = "/deleteuser.action",method = RequestMethod.POST)
    public ModelAndView deleteuser(HttpServletRequest request){

        int toDeleteUserID = Integer.parseInt(request.getParameter("chooseUser"));

        User user = userService.getUser(toDeleteUserID);

        if (user.getIdentity().equals("student")){
            Student student = studentService.getStudent(toDeleteUserID);

            //System.out.println("待删除的学生编号是:"+user.getId());
            studentService.delete(student);
        }
        else if (user.getIdentity().equals("teacher")){
            Teacher teacher = teacherService.getTeacher(toDeleteUserID);

            teacherService.delete(teacher);
        }

        userService.delete(user);

        List<User> updateUserList = userService.getAllUser();
        request.getSession().setAttribute("userList",updateUserList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("userList",(List<User>) request.getSession().getAttribute("userList"));

        return new ModelAndView("admin",model);

    }

    @RequestMapping(value = "/adduser.action",method = RequestMethod.POST)
    public ModelAndView adduser(HttpServletRequest request){

        int newNumber = Integer.parseInt(request.getParameter("newNumber"));
        String newName = request.getParameter("newName");
        String newPassword = request.getParameter("newPassword");
        String newIdentity = request.getParameter("newIdentity");

        User user = new User(newNumber,newName,newPassword,newIdentity);

        if (newIdentity.equals("student")){
            Student student = new Student(newNumber,"","");
            studentService.insert(student);
        }
        else if (newIdentity.equals("teacher")){
            Teacher teacher = new Teacher(newNumber,"");
            teacherService.insert(teacher);
        }

        userService.insert(user);

        List<User> updateUserList = userService.getAllUser();
        request.getSession().setAttribute("userList",updateUserList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("userList",(List<User>) request.getSession().getAttribute("userList"));

        return new ModelAndView("admin",model);
    }

    @RequestMapping(value = "/adminpanel.action",method = RequestMethod.POST)
    public ModelAndView adminpanel(HttpServletRequest request){
        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("adminNum", request.getSession().getAttribute("adminNum"));
        model.put("adminName", request.getSession().getAttribute("adminName"));
        model.put("userList",(List<User>)request.getSession().getAttribute("userList"));

        return new ModelAndView("admin",model);
    }

    @RequestMapping(value = "/roompanel.action",method = RequestMethod.POST)
    public ModelAndView roompanel(HttpServletRequest request){
        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("adminNum", request.getSession().getAttribute("adminNum"));
        model.put("adminName", request.getSession().getAttribute("adminName"));
        model.put("roomList",(List<Classroom>)request.getSession().getAttribute("roomList"));

        return new ModelAndView("modifyroom",model);
    }

    @RequestMapping(value = "/modifyroom.action",method = RequestMethod.POST)
    public ModelAndView modifyroom(HttpServletRequest request){
        //get roomList
        List<Classroom> existRoomList = (List<Classroom>) request.getSession().getAttribute("roomList");

        String modifyRoomNum[] = new String[100];
        String modifyRoomName[] = new String[100];
        String modifyRoomCapacity[] = new String[100];

        for (int i=1;i<=existRoomList.size();i++){
            //get parameter
            modifyRoomNum[i] = request.getParameter("number"+i);
            if (modifyRoomNum[i]==null){
                continue;
            }
            modifyRoomName[i] = request.getParameter("name"+i);
            modifyRoomCapacity[i] = request.getParameter("capacity"+i);

            existRoomList.get(i-1).setId(Integer.parseInt(modifyRoomNum[i]));
            existRoomList.get(i-1).setName(modifyRoomName[i]);
            existRoomList.get(i-1).setCapacity(Integer.parseInt(modifyRoomCapacity[i]));

            //update
            classroomService.insert(existRoomList.get(i-1));
        }

        List<Classroom> updateRoomList = classroomService.getAllClassroom();
        request.getSession().setAttribute("roomList",updateRoomList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("roomList",(List<Classroom>)request.getSession().getAttribute("roomList"));

        return new ModelAndView("modifyroom",model);
    }

    @RequestMapping(value = "/deleteroom.action",method = RequestMethod.POST)
    public ModelAndView deleteroom(HttpServletRequest request){
        int toDeleteRoomID = Integer.parseInt(request.getParameter("chooseRoom"));
        Classroom classroom = classroomService.getClassroom(toDeleteRoomID);
        classroomService.delete(classroom);

        List<Classroom> updateRoomList = classroomService.getAllClassroom();
        request.getSession().setAttribute("roomList",updateRoomList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("roomList",(List<Classroom>)request.getSession().getAttribute("roomList"));

        return new ModelAndView("modifyroom",model);
    }

    @RequestMapping(value = "/addroom.action",method = RequestMethod.POST)
    public ModelAndView addroom(HttpServletRequest request){
        int newNumber = Integer.parseInt(request.getParameter("newNumber"));
        String newName = request.getParameter("newName");
        int newCapacity = Integer.parseInt(request.getParameter("newCapacity"));

        Classroom classroom = new Classroom(newNumber,newName,newCapacity);

        classroomService.insert(classroom);

        List<Classroom> updateRoomList = classroomService.getAllClassroom();
        request.getSession().setAttribute("roomList",updateRoomList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("roomList",(List<Classroom>)request.getSession().getAttribute("roomList"));

        return new ModelAndView("modifyroom",model);
    }

    @RequestMapping(value = "/lessonpanel.action",method = RequestMethod.POST)
    public ModelAndView lessonpanel(HttpServletRequest request){
        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("adminNum", request.getSession().getAttribute("adminNum"));
        model.put("adminName", request.getSession().getAttribute("adminName"));
        model.put("lessonList",(List<Lesson>)request.getSession().getAttribute("lessonList"));

        return new ModelAndView("modifylesson",model);
    }

    @RequestMapping(value = "/modifylesson.action",method = RequestMethod.POST)
    public ModelAndView modifylesson(HttpServletRequest request){
        //get lessonList
        List<Lesson> existLessonList = (List<Lesson>) request.getSession().getAttribute("lessonList");

        String modifyLessonNum[] = new String[100];
        String modifyLessonName[] = new String[100];
        String modifyLessonDay[] = new String[100];
        String modifyLessonOrder[] = new String[100];
        String modifyLessonRoom[] = new String[100];
        String modifyLessonTeacher[] = new String[100];

        for (int i=1;i<=existLessonList.size();i++){
            //get parameter
            modifyLessonNum[i] = request.getParameter("number"+i);
            if (modifyLessonNum[i]==null){
                continue;
            }
            modifyLessonName[i] = request.getParameter("name"+i);
            modifyLessonDay[i] = request.getParameter("occurday"+i);
            modifyLessonOrder[i] = request.getParameter("occurorder"+i);
            modifyLessonRoom[i] = request.getParameter("room"+i);
            modifyLessonTeacher[i] = request.getParameter("teachername"+i);

            existLessonList.get(i-1).setId(Integer.parseInt(modifyLessonNum[i]));
            existLessonList.get(i-1).setName(modifyLessonName[i]);
            existLessonList.get(i-1).setOccurday(Integer.parseInt(modifyLessonDay[i]));
            existLessonList.get(i-1).setOccurorder(Integer.parseInt(modifyLessonOrder[i]));
            existLessonList.get(i-1).setRoom(modifyLessonRoom[i]);
            existLessonList.get(i-1).setTeachername(modifyLessonTeacher[i]);

            //update
            lessonService.insert(existLessonList.get(i-1));
        }

        List<Lesson> updateLessonList = lessonService.getAllLesson();
        request.getSession().setAttribute("lessonList",updateLessonList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("lessonList",(List<Lesson>)request.getSession().getAttribute("lessonList"));

        return new ModelAndView("modifylesson",model);
    }

    @RequestMapping(value = "/deletelesson.action",method = RequestMethod.POST)
    public ModelAndView deletelesson(HttpServletRequest request){
        int toDeleteLessonID = Integer.parseInt(request.getParameter("chooseLesson"));
        Lesson lesson = lessonService.getLesson(toDeleteLessonID);
        lessonService.delete(lesson);

        List<Lesson> updateLessonList = lessonService.getAllLesson();
        request.getSession().setAttribute("lessonList",updateLessonList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("lessonList",(List<Lesson>)request.getSession().getAttribute("lessonList"));

        return new ModelAndView("modifylesson",model);
    }

    @RequestMapping(value = "/addlesson.action",method = RequestMethod.POST)
    public ModelAndView addlesson(HttpServletRequest request){
        int newNumber = Integer.parseInt(request.getParameter("newNumber"));
        String newName = request.getParameter("newName");
        int newDay = Integer.parseInt(request.getParameter("newDay"));
        int newOrder = Integer.parseInt(request.getParameter("newOrder"));
        String newClassroom = request.getParameter("newClassroom");
        String newTeacherName = request.getParameter("newTeacherName");

        Lesson lesson = new Lesson(newNumber,newName,newDay,newClassroom,newOrder,newTeacherName);

        lessonService.insert(lesson);

        List<Lesson> updateLessonList = lessonService.getAllLesson();
        request.getSession().setAttribute("lessonList",updateLessonList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();
        model.put("lessonList",(List<Lesson>)request.getSession().getAttribute("lessonList"));

        return new ModelAndView("modifylesson",model);
    }

    @RequestMapping(value = "/teacherpanel.action",method = RequestMethod.POST)
    public ModelAndView teacherpanel(HttpServletRequest request){
        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("teachlesson",request.getSession().getAttribute("teachlesson"));
        model.put("user",request.getSession().getAttribute("user"));
        //put teacher object

        return new ModelAndView("teacher",model);
    }

    @RequestMapping(value = "/updateteacher.action",method = RequestMethod.POST)
    public ModelAndView updateteacher(HttpServletRequest request){
        int newID = Integer.parseInt(request.getParameter("number"));
        String newName = request.getParameter("name");
        String newPassword = request.getParameter("password");
        String newIdentity = request.getParameter("identity");

        User user = new User(newID,newName,newPassword,newIdentity);

        userService.insert(user);

        User updateUser = userService.getUser(newID);
        request.getSession().setAttribute("user",updateUser);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("user", updateUser);
        model.put("teachlesson",request.getSession().getAttribute("teachlesson"));

        return new ModelAndView("teacher",model);
    }

    @RequestMapping(value = "/updateteachlesson.action",method = RequestMethod.POST)
    public ModelAndView updateteachlesson(HttpServletRequest request){
        String newLesson = request.getParameter("newLesson");
        User user = (User) request.getSession().getAttribute("user");
        Teacher teacher = new Teacher(user.getId(),newLesson);
        teacherService.insert(teacher);
        Teacher updateTeacher = teacherService.getTeacher(user.getId());

        request.getSession().setAttribute("teachlesson",updateTeacher.getLesson());

        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("user", request.getSession().getAttribute("user"));
        model.put("teachlesson",request.getSession().getAttribute("teachlesson"));

        return new ModelAndView("teacher",model);
    }

    @RequestMapping(value = "/studentpanel.action",method = RequestMethod.POST)
    public ModelAndView studentpanel(HttpServletRequest request){
        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("student",request.getSession().getAttribute("student"));
        model.put("user",request.getSession().getAttribute("user"));
        //put teacher object

        return new ModelAndView("student",model);
    }

    @RequestMapping(value = "/updatestudent.action",method = RequestMethod.POST)
    public ModelAndView updatestudent(HttpServletRequest request){
        int newID = Integer.parseInt(request.getParameter("number"));
        String newName = request.getParameter("name");
        String newPassword = request.getParameter("password");
        String newIdentity = request.getParameter("identity");

        User user = new User(newID,newName,newPassword,newIdentity);

        userService.insert(user);

        User updateUser = userService.getUser(newID);
        request.getSession().setAttribute("user",updateUser);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("user", updateUser);
        model.put("student",request.getSession().getAttribute("student"));

        return new ModelAndView("student",model);

    }

    @RequestMapping(value = "/updateclasses.action",method = RequestMethod.POST)
    public ModelAndView updateclasses(HttpServletRequest request){
        String newClasses = request.getParameter("newClasses");

        Student existStudent = (Student) request.getSession().getAttribute("student");
        existStudent.setClasses(newClasses);
        studentService.insert(existStudent);
        request.getSession().setAttribute("student",existStudent);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("user",request.getSession().getAttribute("user"));
        model.put("student",request.getSession().getAttribute("student"));

        return new ModelAndView("student",model);
    }

    @RequestMapping(value = "/timetable.action",method = RequestMethod.POST)
    public ModelAndView timetable(HttpServletRequest request){
        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        Student student = (Student) request.getSession().getAttribute("student");
        List<Integer> lessons = studentService.getLessonList(student.getId());

        if (lessons != null){
            List<Lesson> lessonList = new ArrayList<>();
            for (int l : lessons){
                lessonList.add(lessonService.getLesson(l));
            }

            List<Table> tableList = new ArrayList<>();

            //init table
            tableList.add(new Table("8:00 - 8:50","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("9:00 - 9:50","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("10:00 - 10:50","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("11:00 - 11:50","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("13:30 - 14:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("14:30 - 15:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("15:30 - 16:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("16:30 - 17:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("18:30 - 19:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("19:30 - 20:20","N/A","N/A","N/A","N/A","N/A"));

            for (Lesson le : lessonList){
                if (le.getOccurday()==1){
                    tableList.get(le.getOccurorder()-1).setDay1(le.getName()+" "+le.getRoom());
                }
                else if (le.getOccurday()==2){
                    tableList.get(le.getOccurorder()-1).setDay2(le.getName()+" "+le.getRoom());
                }
                else if (le.getOccurday()==3){
                    tableList.get(le.getOccurorder()-1).setDay3(le.getName()+" "+le.getRoom());
                }
                else if (le.getOccurday()==4){
                    tableList.get(le.getOccurorder()-1).setDay4(le.getName()+" "+le.getRoom());
                }
                else if (le.getOccurday()==5){
                    tableList.get(le.getOccurorder()-1).setDay5(le.getName()+" "+le.getRoom());
                }
            }

            model.put("tableList",tableList);
            request.getSession().setAttribute("tableList",tableList);
        }
        else{
            List<Table> tableList = new ArrayList<>();

            //init table
            tableList.add(new Table("8:00 - 8:50","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("9:00 - 9:50","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("10:00 - 10:50","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("11:00 - 11:50","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("13:30 - 14:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("14:30 - 15:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("15:30 - 16:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("16:30 - 17:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("18:30 - 19:20","N/A","N/A","N/A","N/A","N/A"));
            tableList.add(new Table("19:30 - 20:20","N/A","N/A","N/A","N/A","N/A"));

            model.put("tableList",tableList);
            request.getSession().setAttribute("tableList",tableList);
        }

        model.put("user",request.getSession().getAttribute("user"));
        model.put("student",request.getSession().getAttribute("student"));

        return new ModelAndView("timetable",model);
    }

    @RequestMapping(value = "/chooselesson.action",method = RequestMethod.POST)
    public ModelAndView chooselesson(HttpServletRequest request){
        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        Student student = (Student) request.getSession().getAttribute("student");
        List<Integer> lessons = studentService.getLessonList(student.getId());

        if (lessons != null){
            List<Lesson> lessonList = new ArrayList<>();
            for (int l : lessons){
                lessonList.add(lessonService.getLessonList(l).get(0));
            }

            List<Lesson> camdidateList = lessonService.getAllLesson();

            //select candidate lesson to choose
            for (Lesson l : lessonList){
                for (Lesson c : camdidateList){
                    if (l.getId() == c.getId()){
                        camdidateList.remove(c);
                        break;
                    }
                }
            }
            //now allLessonList is candidateList

            request.getSession().setAttribute("lessonList",lessonList);
            request.getSession().setAttribute("candidateList",camdidateList);

            model.put("lessonList",request.getSession().getAttribute("lessonList"));
            model.put("candidateList",request.getSession().getAttribute("candidateList"));
        }
        else{
            List<Lesson> lessonList = new ArrayList<>();
            List<Lesson> camdidateList = lessonService.getAllLesson();

            request.getSession().setAttribute("lessonList",lessonList);
            request.getSession().setAttribute("candidateList",camdidateList);

            model.put("lessonList",request.getSession().getAttribute("lessonList"));
            model.put("candidateList",request.getSession().getAttribute("candidateList"));
        }

        model.put("user",request.getSession().getAttribute("user"));
        model.put("student",request.getSession().getAttribute("student"));

        return new ModelAndView("chooselesson",model);
    }

    @RequestMapping(value = "/addstudylesson.action",method = RequestMethod.POST)
    public ModelAndView addstudylesson(HttpServletRequest request){
        int chooseStudyLesson = Integer.parseInt(request.getParameter("chooseStudyLesson"));

        Student student = (Student) request.getSession().getAttribute("student");

        String choosedLessons = student.getLesson();
        choosedLessons = choosedLessons + chooseStudyLesson + ";";
        student.setLesson(choosedLessons);
        studentService.insert(student);

        request.getSession().setAttribute("student",student);

        //update candidateList and lessonList

        Student newStudent = (Student) request.getSession().getAttribute("student");
        List<Integer> lessons = studentService.getLessonList(newStudent.getId());
        List<Lesson> lessonList = new ArrayList<>();
        for (int l : lessons){
            lessonList.add(lessonService.getLessonList(l).get(0));
        }

        List<Lesson> camdidateList = lessonService.getAllLesson();

        //select candidate lesson to choose

        for (Lesson l : lessonList){
            for (Lesson c : camdidateList){
                if (l.getId() == c.getId()){
                    camdidateList.remove(c);
                    break;
                }
            }
        }
        //now allLessonList is candidateList

        request.getSession().setAttribute("lessonList",lessonList);
        request.getSession().setAttribute("candidateList",camdidateList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("lessonList",request.getSession().getAttribute("lessonList"));
        model.put("candidateList",request.getSession().getAttribute("candidateList"));
        model.put("user",request.getSession().getAttribute("user"));
        model.put("student",request.getSession().getAttribute("student"));

        return new ModelAndView("chooselesson",model);

    }

    @RequestMapping(value = "/deletestudylesson.action",method = RequestMethod.POST)
    public ModelAndView deletestudylesson(HttpServletRequest request){
        int chooseDeleteLesson = Integer.parseInt(request.getParameter("chooseDeleteLesson"));

        Student student = (Student) request.getSession().getAttribute("student");

        List<Integer> choosedLessons = studentService.getLessonList(student.getId());
        for (int i=0;i<choosedLessons.size();i++){
            if (choosedLessons.get(i) == chooseDeleteLesson){
                choosedLessons.remove(i);
                break;
            }
        }

        String afterDeleteLesson = "";

        for (int i : choosedLessons){
            afterDeleteLesson = afterDeleteLesson + i + ";";
        }

        student.setLesson(afterDeleteLesson);
        studentService.insert(student);

        request.getSession().setAttribute("student",student);

        //update candidateList and lessonList

        Student newStudent = (Student) request.getSession().getAttribute("student");
        List<Integer> lessons = studentService.getLessonList(newStudent.getId());
        List<Lesson> lessonList = new ArrayList<>();
        for (int l : lessons){
            lessonList.add(lessonService.getLessonList(l).get(0));
        }

        List<Lesson> camdidateList = lessonService.getAllLesson();

        //select candidate lesson to choose

        for (Lesson l : lessonList){
            for (Lesson c : camdidateList){
                if (l.getId() == c.getId()){
                    camdidateList.remove(c);
                    break;
                }
            }
        }
        //now allLessonList is candidateList

        request.getSession().setAttribute("lessonList",lessonList);
        request.getSession().setAttribute("candidateList",camdidateList);

        final Map<String, Object> model = new LinkedHashMap<String, Object>();

        model.put("lessonList",request.getSession().getAttribute("lessonList"));
        model.put("candidateList",request.getSession().getAttribute("candidateList"));
        model.put("user",request.getSession().getAttribute("user"));
        model.put("student",request.getSession().getAttribute("student"));

        return new ModelAndView("chooselesson",model);

    }

    @RequestMapping(value = "/logout.action",method = RequestMethod.POST)
    public String logout(HttpServletRequest request){
        //clear the session content
        request.getSession().invalidate();

        return "index";
    }

}
