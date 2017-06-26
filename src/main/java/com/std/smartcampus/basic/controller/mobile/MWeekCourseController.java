package com.std.smartcampus.basic.controller.mobile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.std.smartcampus.basic.domain.WeekCourse;
import com.std.smartcampus.basic.domain.WeekCourse.Week;
import com.std.smartcampus.basic.service.ClasService;
import com.std.smartcampus.basic.service.ClassRoomService;
import com.std.smartcampus.basic.service.CourseService;
import com.std.smartcampus.basic.service.TeacherService;
import com.std.smartcampus.basic.service.WeekCourseService;
import com.std.smartcampus.common.result.Result;
import com.std.smartcampus.common.util.ResultUtil;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @ClassName: MobileWeekCourseController 
 *
 * @author: Administrator
 * @date: 2017年3月26日 上午1:55:30
 */
@RestController
@SuppressWarnings("unused")
@RequestMapping("/weekcourse")
public class MWeekCourseController {
	
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
	
	@ApiOperation(value="按条件查询课程信息",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="cid",value="班级编号",required = true,dataType="String"),
			@ApiImplicitParam(name="tid",value="教师工号",required = true,dataType="String"),
			@ApiImplicitParam(name="lid",value="教室编号",required = true,dataType="String"),
	})
	@GetMapping(value="/find")
	public Result<List<List<WeekCourse>>> find(Integer cid, String tid, Integer lid) {
		List<List<WeekCourse>> lists = weekCourseService.findByClassOrTeacherOrLocation(cid,tid,lid);
		return ResultUtil.success(lists);
	}


	@ApiOperation(value="保存或修改一条课程信息",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="clasid",value="班级编号",required = true,dataType="String"),
			@ApiImplicitParam(name="week",value="星期几",required = true,dataType="Week"),
			@ApiImplicitParam(name="time",value="第几节课",required = true,dataType="Integer"),
			@ApiImplicitParam(name="cid",value="课程编号",required = true,dataType="String"),
			@ApiImplicitParam(name="tid",value="教师工号",required = true,dataType="String"),
			@ApiImplicitParam(name="lid",value="教室编号",required = true,dataType="String"),
	})
	@PostMapping(value="/save")
	public Result<WeekCourse> save(Integer clasid, String week, String time,
			Integer cid, String tid, Integer lid) {
		WeekCourse weekCourse=weekCourseService.save(clasid, week,time,cid,tid,lid);
		return ResultUtil.success(weekCourse);
	}


	@ApiOperation(value="删除一行",notes="")
	@ApiImplicitParams(value={
			@ApiImplicitParam(name="cid",value="班级编号",required = true,dataType="String"),
			@ApiImplicitParam(name="week",value="星期几",required = true,dataType="Week"),
			@ApiImplicitParam(name="time",value="第几节课",required = true,dataType="Integer"),
	})
	@GetMapping("/delete")
	public Result<WeekCourse> delete( Integer cid, Integer week, Integer time) {
		WeekCourse weekCourse = weekCourseService.delete(cid,week,time);
		if (weekCourse != null) {
			return ResultUtil.success(weekCourse);
		}
		return ResultUtil.error(-1, "从数据库删除数据错误");
	}


	/**
	 * 拼接URL
	 * @param args
	 * @return
	 */
//	private String getParam(String args) {
//		String[] split = args.split("_");
//		Map<String, String> aMap = new ConcurrentHashMap<String, String>();
//		for (int i = 0; i < split.length; i+=2) {
//			amodel.addAttribute(split[i], split[i+1]);
//		}
//		for (Map.Entry<String, String> entry : aMap.entrySet()) {
//			if ("null".equals(entry.getValue())) {
//				aMap.remove(entry.getKey(),entry.getValue());
//			}
//		}
//		StringBuilder params = new StringBuilder();
//		for (Map.Entry<String, String> entry : aMap.entrySet()) {
//			params.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
//		}
//		return params.toString().substring(0,params.length()-1);
//	}
}
