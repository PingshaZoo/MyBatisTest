package async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CompletableFutureSetComplete {

	public static void main(String[] args) throws Exception {
		setCompleteEx();
	}

	/***
	 * cf 任务执行开始 主动设置 cf 任务结果 get:程序通事 cf 任务执行结束 get:程序通事
	 */
	public static void setComplete() throws Exception {
		// 执行异步任务
		CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("cf 任务执行开始");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("cf 任务执行结束");
			return "楼下小黑哥";
		});
		//
		Executors.newSingleThreadScheduledExecutor().execute(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("主动设置 cf 任务结果");
			// 设置任务结果，由于 cf 任务未执行结束，结果返回 true
			cf.complete("程序通事");
		});
		// 由于 cf 未执行结束，将会被阻塞。5 秒后，另外一个线程主动设置任务结果
		System.out.println("get:" + cf.get());
		// 等待 cf 任务执行结束
		Thread.sleep(11000);
		// 由于已经设置任务结果，
		System.out.println("get:" + cf.get());
	}

	/***
	 * cf 任务执行开始 主动设置 cf 异常 java.util.concurrent.ExecutionException:
	 * java.lang.RuntimeException: 啊，挂了 ......
	 * 
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void setCompleteEx() throws Exception {
		// 执行异步任务
		CompletableFuture cf = CompletableFuture.supplyAsync(() -> {
			System.out.println("cf 任务执行开始");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("cf 任务执行结束");
			return "楼下小黑哥";
		});
		//
		Executors.newSingleThreadScheduledExecutor().execute(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("主动设置 cf 异常");
			// 设置任务结果，由于 cf 任务未执行结束，结果返回 true
			cf.completeExceptionally(new RuntimeException("啊，挂了"));
		});
		// 由于 cf 未执行结束，前 5 秒将会被阻塞。后续程序抛出异常，结束
		System.out.println("get:" + cf.get());

	}

}
