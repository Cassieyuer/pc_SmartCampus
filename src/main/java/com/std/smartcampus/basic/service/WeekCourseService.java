package com.std.smartcampus.basic.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.std.smartcampus.basic.domain.Clas;
import com.std.smartcampus.basic.domain.ClassRoom;
import com.std.smartcampus.basic.domain.Course;
import com.std.smartcampus.basic.domain.Teacher;
import com.std.smartcampus.basic.domain.WeekCourse;
import com.std.smartcampus.basic.domain.WeekCourse.Time;
import com.std.smartcampus.basic.domain.WeekCourse.Week;
import com.std.smartcampus.basic.domain.pk.WeekCoursePK;
import com.std.smartcampus.basic.model.DayCourse;
import com.std.smartcampus.basic.repository.ClasRepository;
import com.std.smartcampus.basic.repository.ClassRoomRepository;
import com.std.smartcampus.basic.repository.CourseRepository;
import com.std.smartcampus.basic.repository.TeacherRepository;
import com.std.smartcampus.basic.repository.WeekCourseRepository;
import com.std.smartcampus.basic.service.base.BaseService;

/**
 * 课程表业务
 * @author Administrator
 *
 */
@Service
@Transactional
public class WeekCourseService extends BaseService<WeekCourse, WeekCoursePK>{
	
	private WeekCourseRepository weekCourseRepository;//课程表业务
	@Autowired
	public void setRepository(WeekCourseRepository repository) {
		this.repository = repository;
		this.weekCourseRepository = repository;
	}
	@Autowired
	private CourseRepository courseRepository;//课程业务
	@Autowired
	private ClassRoomRepository classRoomRepository;//班级业务
	@Autowired
	private ClasRepository clasRepository;//班级业务
	@Autowired
	private TeacherRepository teacherRepository;//班级业务
	
	/**
	 * 删除所有数据
	 */
	public void deleteAll() {
		weekCourseRepository.deleteAll();
	}

	/**
	 * 根据主键查询课程信息
	 * @param cid
	 * @param week
	 * @param time
	 */
	public WeekCourse findOne(Integer cid, Integer week, Integer time) {
		WeekCoursePK pk = new WeekCoursePK(Integer.valueOf(cid), Week.values()[Integer.valueOf(week)], Integer.valueOf(time));
		return weekCourseRepository.findOne(pk);
	}

	/** 
	 * 
	 */
	public List<List<WeekCourse>> findByClassOrTeacherOrLocation(Integer cid, String tid, Integer lid) {
		List<WeekCourse> list = new ArrayList<>();
		if (!"".equals(cid) && null != cid) {
			list = weekCourseRepository.findByClas(Integer.valueOf(cid));
		} else if (!"".equals(tid) && null != tid) {
			list = weekCourseRepository.findByTeacher_Tid(tid);
		} else if (!"".equals(lid) && null != lid) {
			list = weekCourseRepository.findBylocation_Id(Integer.valueOf(lid));
		} 
		List<List<WeekCourse>> lists = new ArrayList<>();
		//用java8,设置班级名字
		list.stream().forEach(p -> p.setClassName(clasRepository.findOne(p.getClas()).getName()));
		//一天8节课
		for (int i = Time.I; i <= Time.VIII; i++) {
			final int now = i;
			final DayCourse dayCourse = new DayCourse();
			list.stream()
			 .filter(p -> p.getTime() == now)//提取第n节课的信息 ,对应HTML界面一行
			 .forEach(dayCourse::add);//将一行信息封装为一个对象
			lists.add(dayCourse.list);
		}
		return lists;
	}


	/**
	 * 
	 */
	public WeekCourse save(Integer clasid, String week, String time, Integer cid, String tid, Integer lid) {
		Clas clas = clasRepository.findOne(clasid);
		Course course = courseRepository.findOne(cid);
		Teacher teacher = teacherRepository.findOne(tid);
		ClassRoom location = classRoomRepository.findOne(Integer.valueOf(lid));
		int _time = Integer.valueOf(time.charAt(1))-49;//char转int 根据ASCII码要-48,HTML页面与 WeekCourse.Time 类不一样又-1
		WeekCourse weekCourse = new WeekCourse(teacher, clas.getCid(), course, location, Week.valueOf(week), _time, clas.getName());
		return weekCourseRepository.save(weekCourse);
	}

	/** 
	 * 
	 */
	public WeekCourse delete(Integer cid, Integer week, Integer time) {
		System.out.println("cid="+cid+"week="+week+"time="+time);
		WeekCourse weekCourse = findOne(cid,week, time);
		if (weekCourse != null) {
			weekCourseRepository.delete(weekCourse);
		}
		return weekCourse;
	}




	
}
