package com.std.smartcampus.basic.controller.pc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.std.smartcampus.basic.domain.Clas;
import com.std.smartcampus.basic.domain.ClassRoom;
import com.std.smartcampus.basic.domain.Course;
import com.std.smartcampus.basic.domain.Teacher;
import com.std.smartcampus.basic.domain.WeekCourse;
import com.std.smartcampus.basic.domain.WeekCourse.Week;
import com.std.smartcampus.basic.service.ClasService;
import com.std.smartcampus.basic.service.ClassRoomService;
import com.std.smartcampus.basic.service.CourseService;
import com.std.smartcampus.basic.service.TeacherService;
import com.std.smartcampus.basic.service.WeekCourseService;
import com.std.smartcampus.common.util.BeanUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 课程表控制层
 * @ClassName: PCWeekCourseController 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:24:30
 */
@Controller
@RequestMapping("/admin")
public class WeekCourseController {
	
	@Autowired
	private WeekCourseService weekCourseService;//课程表业务
	@Autowired
	private CourseService courseService;//课程业务
	@Autowired
	private ClassRoomService classRoomService;//班级业务
	@Autowired
	private ClasService clasService;//班级业务
	@Autowired
	private TeacherService teacherService;//班级业务
	

	@ApiOperation(value="保存或修改一条课程信息",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="map",value="向页面传递信息",required = true,dataType="String"),
			@ApiImplicitParam(name="clasid",value="班级编号",required = true,dataType="Integer"),
			@ApiImplicitParam(name="week",value="星期几",required = true,dataType="String"),
			@ApiImplicitParam(name="time",value="第几节课",required = true,dataType="String"),
			@ApiImplicitParam(name="cid",value="课程编号",required = true,dataType="Integer"),
			@ApiImplicitParam(name="tid",value="教师工号",required = true,dataType="String"),
			@ApiImplicitParam(name="lid",value="教室编号",required = true,dataType="Integer"),
			@ApiImplicitParam(name="args",value="从上一页面接收的参数",required = true,dataType="String"),
	})
	@PostMapping(value="/weekcourse-save")
	public String save(Model model,
			Integer clasid, //班级ID
			String week, //周几英文
			String time,//第几节课
			Integer cid,//课程ID
			String tid, //教师ID
			Integer lid,//教室ID
			String args) {//接收的参数
		weekCourseService.save(clasid, week, time, cid, tid, lid);
		return "redirect:weekcourse-find?"+getParam(args);
	}

	
	@ApiOperation(value="按条件查询课程信息",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="map",value="向HTML页面传递信息",required = true,dataType="String"),
			@ApiImplicitParam(name="cid",value="班级编号",required = true,dataType="Integer"),
			@ApiImplicitParam(name="tid",value="教师工号",required = true,dataType="String"),
			@ApiImplicitParam(name="lid",value="教室编号",required = true,dataType="Integer"),
	})
	@GetMapping(value="/weekcourse-find")
	public String find(Model model, Integer cid, String tid, Integer lid) {
		List<List<WeekCourse>> lists = weekCourseService.findByClassOrTeacherOrLocation(cid, tid, lid);
		putSelectCondition(model);
		model.addAttribute("page", lists);
		model.addAttribute("week_course_css", "active");
		return "campus/weekcourse/weekcourse_query_success";
	}
	
	@ApiOperation(value="进入修改课程信息界面",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="map",value="向HTML页面传递信息",required = true,dataType="String"),
			@ApiImplicitParam(name="cid",value="班级编号",required = true,dataType="String"),
			@ApiImplicitParam(name="week",value="星期几",required = true,dataType="Week"),
			@ApiImplicitParam(name="time",value="第几节课",required = true,dataType="Integer"),
			@ApiImplicitParam(name="args",value="携带参数向下一页面传递",required = true,dataType="String"),
	})
	@GetMapping(value="/weekcourse-modify")
	public String modify(Model model, Integer cid, Integer week, Integer time, String args) {//携带参数向下一页面传递
		cid="null".equals(cid)?1:cid;
		WeekCourse weekCourse = weekCourseService.findOne(cid, week, time);
		if (weekCourse == null) {
			weekCourse = (WeekCourse) BeanUtil.getBean(WeekCourse.class, true);
			weekCourse.setClas(cid);
			weekCourse.setWeek(Week.values()[week]);
			weekCourse.setTime(time);
		}
		weekCourse.setClassName(clasService.findOne(weekCourse.getClas()).getName());
		model.addAttribute("weekcourse", weekCourse);
		model.addAttribute("args", args);//向下一页面传递参数
		putSelectCondition(model);
		return "campus/weekcourse/weekcourse_modify";
	}
	
	@ApiOperation(value="删除一行",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="map",value="向HTML页面传递信息",required = true,dataType="String"),
			@ApiImplicitParam(name="cid",value="班级编号",required = true,dataType="String"),
			@ApiImplicitParam(name="week",value="星期几",required = true,dataType="Week"),
			@ApiImplicitParam(name="time",value="第几节课",required = true,dataType="Integer"),
			@ApiImplicitParam(name="args",value="携带参数向下一页面传递",required = true,dataType="String"),
	})
	@GetMapping(value="/weekcourse-delete")
	public String delete(Model model, Integer cid, Integer week, Integer time, String args) {
		weekCourseService.delete(cid, week, time);
		return "redirect:weekcourse-find?"+getParam(args);
	}
	
	@ApiOperation(value="删除所有课程表信息",notes="")
	@GetMapping(value="/weekcourse-deleteall")
	public String deleteAll() {
		weekCourseService.deleteAll();
		return "redirect:weekcourse-find?";
		
	}
	
	/**
	 * 拼接URL
	 * @Title: getParam 
	 *
	 * @param args
	 * @return
	 * @return: String
	 */
	private String getParam(String args) {
		String[] split = args.split("_");
		StringBuilder params = new StringBuilder();
		for (int i = 0; i < split.length; i+=2) {
			if (!"null".equals(split[i+1])) {
				params.append(split[i]).append("=").append(split[i+1]).append("&");
			}
		}
		if (0==params.length()) {
			params.append("cid=1?");
		}
		return params.toString().substring(0,params.length()-1);
	}
	/**
	 * 加入查询条件
	 * @param map
	 */
	private void putSelectCondition(Model model) {
		List<Clas> classList = clasService.findAll();
		model.addAttribute("class_list", classList);
		List<Teacher> teacherList = teacherService.findAll();
		model.addAttribute("teacher_list", teacherList);
		List<ClassRoom> locationList = classRoomService.findAll();
		model.addAttribute("location_list", locationList);
		List<Course> courseList = courseService.findAll();
		model.addAttribute("course_list", courseList);
	}
}
