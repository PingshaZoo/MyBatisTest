package async;

import java.util.concurrent.CompletableFuture;

public class CompletableSample {

	public static void main(String[] args) {
		runSample();
	}

	public static void runSample() {
		CompletableFuture<String> orderPlane = CompletableFuture.supplyAsync(() -> {
			System.out.println(System.currentTimeMillis() + " 开始预定航班");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis() + " 预定航班完成");
			System.out.println("预定航班的线程" + Thread.currentThread().toString());
			return "预定航班完成";
		});

		CompletableFuture<String> orderHotel = CompletableFuture.supplyAsync(() -> {
			System.out.println(System.currentTimeMillis() + " 开始预定酒店");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis() + " 预定酒店完成");
			System.out.println("预定酒店的线程" + Thread.currentThread().toString());
			return "预定酒店完成";
		});

		CompletableFuture<String> orderProstitute = orderHotel.thenCombine(orderPlane, (plane, hotel) -> {
			System.out.println(System.currentTimeMillis() + " 完成机票和酒店预定");
			System.out.println(plane + "     " + hotel);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("预定小姐的线程" + Thread.currentThread().toString());
			return "begin to fuck.";

		});

		System.out.println(System.currentTimeMillis() + " " + orderProstitute.join());

	}

}
