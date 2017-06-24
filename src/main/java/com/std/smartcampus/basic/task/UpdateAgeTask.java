package com.std.smartcampus.basic.task;

import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 * 异步定时任务更新用户年龄
 * @ClassName: UpdateAgeTask 
 * @author: 李志鹏
 * @date: 2017年3月27日 上午12:26:17
 */

import com.std.smartcampus.basic.service.StudentService;
import com.std.smartcampus.basic.service.TeacherService;
@Component
@EnableAsync
@EnableScheduling
@SuppressWarnings({ "rawtypes", "unchecked" })
public class UpdateAgeTask {
	
	@Autowired
	private StudentService studentService;
	@Autowired
	private TeacherService teacherService;
    //SS-MM-HH
	@Async
    @Scheduled(cron = "00 16 18 * * ?")//秒 分 时 日 月 周
    public Future updateTeacherAge() {
		teacherService.updateTeacherAge();
		System.out.println("教师年龄更新完成");
		return new AsyncResult("教师年龄更新完成");
    }
	@Async
	@Scheduled(cron = "00 17 18 * * ?")//秒 分 时 日 月 周
	public Future updateStudentAge() {
		studentService.updateStudentAge();
		System.out.println("学生年龄更新完成");
		return new AsyncResult("学生年龄更新完成");
	}
}