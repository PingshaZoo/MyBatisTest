package activiti;

import java.util.List;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;

public class Activiti {

	public static ProcessEngine processEngine;

	public static void main(String[] args) throws Exception {
		createActivitiEngine();
		depolyBPMN();
		startProcess();
		List<Task> tasks = queryTask();
		compileTask(tasks);
		queryNextTask();

	}

	public static void createActivitiEngine() throws Exception {
		// 通过ProcessEngines 来获取默认的流程引擎
		// 默认会加载类路径下的 activiti.cfg.xml
		ProcessEngineConfiguration pec = ProcessEngineConfiguration
				.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
		Activiti.processEngine = pec.buildProcessEngine();
		System.out.println("通过ProcessEngines 来获取流程引擎");
	}

	public static void depolyBPMN() throws Exception {
		// 获取仓库服务 ：管理流程定义
		RepositoryService repositoryService = Activiti.processEngine.getRepositoryService();
		Deployment deploy = repositoryService.createDeployment()// 创建一个部署的构建器
				.addClasspathResource("LeaveActiviti.bpmn")// 从类路径中添加资源,一次只能添加一个资源
				.name("请求单流程")// 设置部署的名称
				.category("办公类别")// 设置部署的类别
				.deploy();

		System.out.println("部署的id:" + deploy.getId());
		System.out.println("部署的名称:" + deploy.getName());
	}

	public static void startProcess() throws Exception {

		// 指定执行我们刚才部署的工作流程
		String processDefiKey = "leaveBill";
		// 取运行时服务
		RuntimeService runtimeService = processEngine.getRuntimeService();
		// 取得流程实例
		ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefiKey);// 通过流程定义的key 来执行流程
		System.out.println("流程Executionid:" + pi.getId());// 流程Executionid
		System.out.println("流程ProcessInstanceid：" + pi.getProcessInstanceId());// ProcessInstance 对象
		System.out.println("流程定义ProcessDefinitionId:" + pi.getProcessDefinitionId());// 输出流程定义的id

	}

	// 查询任务
	public static List<Task> queryTask() {
		// 任务的办理人
		String assignee = "张三";
		// 取得任务服务
		TaskService taskService = processEngine.getTaskService();
		// 创建一个任务查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 办理人的任务列表
		List<Task> taskList = taskQuery.taskAssignee(assignee).list();// 指定办理人
		// 遍历任务列表
		taskList.forEach(task -> {
			System.out.println("任务的办理人：" + task.getAssignee());
			System.out.println("任务的id：" + task.getId());
			System.out.println("任务的名称：" + task.getName());
		});

		return taskList;
	}

	// 完成任务
	public static void compileTask(List<Task> taskList) {
		taskList.forEach(task -> {
			processEngine.getTaskService().complete(task.getId());
			System.out.println("任务执行完毕id= " + task.getId());
		});
	}

	// 设置任务的办理人
	public static void setTasksAssignee(List<Task> taskList) {
		taskList.forEach(task -> {
			processEngine.getTaskService().complete(task.getId());
			System.out.println("任务执行完毕id= " + task.getId());
		});
	}

	// 查看班主任的任务
	public static List<Task> queryNextTask() {
		// 任务的办理人
		String assignee = "班主任";
		// 取得任务服务
		TaskService taskService = processEngine.getTaskService();
		// 创建一个任务查询对象
		TaskQuery taskQuery = taskService.createTaskQuery();
		// 办理人的任务列表
		List<Task> taskList = taskQuery.taskAssignee(assignee).list();// 指定办理人
		// 遍历任务列表
		taskList.forEach(task -> {
			System.out.println("任务的办理人：" + task.getAssignee());
			System.out.println("任务的id：" + task.getId());
			System.out.println("任务的名称：" + task.getName());
		});

		return taskList;
	}

	// 查看历史执行流程实例信息
	public void queryHistoryProcInst() {
		List<HistoricProcessInstance> list = processEngine.getHistoryService().createHistoricProcessInstanceQuery()
				.list();
		if (list != null && list.size() > 0) {
			for (HistoricProcessInstance temp : list) {
				System.out.println("历史流程实例id:" + temp.getId());
				System.out.println("历史流程定义的id:" + temp.getProcessDefinitionId());
				System.out.println("历史流程实例开始时间--结束时间:" + temp.getStartTime() + "-->" + temp.getEndTime());
			}
		}
	}

}
